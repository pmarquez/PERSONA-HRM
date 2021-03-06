package io.nordstar.heimdallmobile.activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerContract;
import com.kontakt.sdk.android.ble.manager.listeners.EddystoneListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleEddystoneListener;
import com.kontakt.sdk.android.common.profile.IEddystoneDevice;
import com.kontakt.sdk.android.common.profile.IEddystoneNamespace;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.nordstar.heimdallmobile.R;
import io.nordstar.heimdallmobile.nfc.NdefMessageParser;
import io.nordstar.heimdallmobile.nfc.ParsedNdefRecord;
import io.nordstar.heimdallmobile.prefs.HeimdallPrefs;
import io.nordstar.heimdallmobile.process.LoginRec;
import io.nordstar.heimdallmobile.process.ResponseEventRec;

public class AccessControlActivity extends AppCompatActivity {

    Button btnLogout;

    //   Eddystone
    private ProximityManagerContract proximityManager;

    //   NFC
    private NfcAdapter    nfcAdapter;
    private PendingIntent nfcPendingIntent;
    private NdefMessage   nfcNdefPushMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_control);

        btnLogout = ( Button ) findViewById ( R.id.btnLogout );
        btnLogout.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                doLogout ( );
            }
        } );

        //   NFC
        nfcInitialSetup ( );

        //   Eddystone
        proximityManager = new ProximityManager ( this );
        proximityManager.setEddystoneListener ( createEddystoneListener ( ) );

    }

    @Override
    protected void onStart ( ) {
        super.onStart ( );
        startScanning ( );
    }

    @Override
    protected void onPause ( ) {
        super.onPause ( );

        if ( nfcAdapter != null ) {
            nfcAdapter.disableForegroundDispatch ( this );
            nfcAdapter.disableForegroundNdefPush ( this );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled()) {
                showWirelessSettingsDialog();
            }
            nfcAdapter.enableForegroundDispatch ( this, nfcPendingIntent, null, null );
            nfcAdapter.enableForegroundNdefPush ( this, nfcNdefPushMessage );
        }
    }

    @Override
    protected void onStop ( ) {
        proximityManager.stopScanning ( );
        super.onStop ( );
    }

    @Override
    protected void onDestroy ( ) {
        proximityManager.disconnect ( );
        proximityManager = null;
        super.onDestroy ( );
    }

    private void doLogout ( ) {

        SharedPreferences sharedPreferences = getSharedPreferences ( HeimdallPrefs.heimdallPrefs, Context.MODE_PRIVATE );

        SharedPreferences.Editor editor = sharedPreferences.edit ( );

        editor.remove ( HeimdallPrefs.authorizationToken );
        editor.remove ( HeimdallPrefs.userFirstName      );
        editor.remove ( HeimdallPrefs.userLastName       );

        editor.commit ( );

        Intent intent = new Intent ( AccessControlActivity.this, LoginActivity.class );

        startActivity ( intent );

        this.finish ( );

    }

//  *********************************************************************
//  ***** BEGIN *********************************************************
//  *********************************************************************
//  **** NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC ****
//  *********************************************************************
//  *********************************************************************

    private void nfcInitialSetup ( ) {

        resolveIntent ( getIntent ( ) );

        nfcAdapter = NfcAdapter.getDefaultAdapter ( this );

        if ( nfcAdapter == null ) {
            showAlert ( "NFC Error", "No NFC found on this device" );
            return;
        } else {
            Log.v ( "NFC", "Yay, yes we have NFC!!!" );
        }

        nfcPendingIntent = PendingIntent.getActivity ( this, 0, new Intent( this,
                        getClass ( ) ).addFlags ( Intent.FLAG_ACTIVITY_SINGLE_TOP ), 0 );

        nfcNdefPushMessage = new NdefMessage ( new NdefRecord[ ] { newTextRecord ( "Message from NFC Reader...",
                Locale.ENGLISH, true )
        } );

    }

    @Override
    public void onNewIntent ( Intent intent ) {
        setIntent     ( intent );
        resolveIntent ( intent );
    }

    public void nfcActivityLauncher ( ) {
		Log.v ( "nfcActivityLauncher", "ENTRANDO" );
    }

    private NdefRecord newTextRecord ( String text, Locale locale, boolean encodeInUtf8 ) {

        byte [ ] langBytes = locale.getLanguage ( ).getBytes ( Charset.forName ( "US-ASCII" ) );

        Charset utfEncoding = encodeInUtf8 ? Charset.forName ( "UTF-8" ) : Charset.forName ( "UTF-16" );
        byte [ ] textBytes = text.getBytes ( utfEncoding );

        int utfBit = encodeInUtf8 ? 0 : ( 1 << 7 );
        char status = ( char ) ( utfBit + langBytes.length );

        byte [ ] data = new byte [ 1 + langBytes.length + textBytes.length ];
        data [ 0 ] = ( byte ) status;
        System.arraycopy ( langBytes, 0, data, 1, langBytes.length );
        System.arraycopy ( textBytes, 0, data, 1 + langBytes.length, textBytes.length );

        return new NdefRecord ( NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte [ 0 ], data );
    }

    //   JACK SPARROW - INTEGRAR ESTO...
    private void showWirelessSettingsDialog ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setMessage(R.string.nfc_disabled);
        builder.setPositiveButton ( android.R.string.ok, new DialogInterface.OnClickListener ( ) {
            public void onClick ( DialogInterface dialogInterface, int i ) {
                Intent intent = new Intent ( Settings.ACTION_WIRELESS_SETTINGS );
                startActivity ( intent );
            }
        } );
        builder.setNegativeButton ( android.R.string.cancel, new DialogInterface.OnClickListener ( ) {
            public void onClick ( DialogInterface dialogInterface, int i ) {
                //               finish ( );
            }
        } );

        builder.create ( ).show ( );

        return;
    }

    private void resolveIntent ( Intent intent ) {

    	Log.v ( "resolveIntent", "ENTRANDO" );

        String action = intent.getAction ( );

        if (    NfcAdapter.ACTION_TAG_DISCOVERED.equals  ( action )
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals ( action )
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals ( action )    ) {

    		Log.v ( "resolveIntent: ", action );

//   SOUND PLAY - BEGIN
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            final MediaPlayer mMediaPlayer = new MediaPlayer();

            try {
                mMediaPlayer.setDataSource(getApplicationContext ( ), notification);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.prepare();
                mMediaPlayer.setLooping(false);
                mMediaPlayer.start();
                mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    public void onSeekComplete(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
//   SOUND PLAY - END

            Parcelable[ ] rawMsgs = intent.getParcelableArrayExtra ( NfcAdapter.EXTRA_NDEF_MESSAGES );
            NdefMessage [ ] msgs;

            if ( rawMsgs != null ) {
                msgs = new NdefMessage [ rawMsgs.length ];

                for ( int i = 0; i < rawMsgs.length; i++ ) {
                    msgs [ i ] = ( NdefMessage ) rawMsgs [ i ];
                }

            } else {
                // Unknown tag type
                byte [ ]    empty   = new byte [ 0 ];
                byte [ ]    id      = intent.getByteArrayExtra ( NfcAdapter.EXTRA_ID );
                Parcelable  tag     = intent.getParcelableExtra ( NfcAdapter.EXTRA_TAG );
                byte [ ]    payload = dumpTagData ( tag ).getBytes ( );
                NdefRecord  record  = new NdefRecord ( NdefRecord.TNF_UNKNOWN, empty, id, payload );
                NdefMessage msg     = new NdefMessage ( new NdefRecord [ ] { record } );

                msgs = new NdefMessage [ ] { msg };

            }
            // Setup the views
            buildTagViews ( msgs );
        }
    }

    private String dumpTagData ( Parcelable p ) {

        StringBuilder sb = new StringBuilder ( );
        Tag tag = ( Tag ) p;
        byte [ ] id = tag.getId ( );

        sb.append ( "Tag ID (hex): "  ).append ( getHex      ( id ) ).append ( "\n" );
        sb.append ( "Tag ID (dec): "  ).append ( getDec      ( id ) ).append ( "\n" );
        sb.append ( "ID (reversed): " ).append ( getReversed ( id ) ).append ( "\n" );

        String prefix = "android.nfc.tech.";
        sb.append("Technologies: ");
        for ( String tech : tag.getTechList ( ) ) {
            sb.append ( tech.substring ( prefix.length ( ) ) );
            sb.append ( ", " );
        }
        sb.delete ( sb.length ( ) - 2, sb.length ( ) );

        for ( String tech : tag.getTechList ( ) ) {

            if (tech.equals ( MifareClassic.class.getName ( ) ) ) {

                sb.append ( '\n' );
                MifareClassic mifareTag = MifareClassic.get ( tag );

                String type = "Unknown";

                switch ( mifareTag.getType ( ) ) {

                    case MifareClassic.TYPE_CLASSIC:
                        type = "Classic";
                        break;

                    case MifareClassic.TYPE_PLUS:
                        type = "Plus";
                        break;

                    case MifareClassic.TYPE_PRO:
                        type = "Pro";
                        break;
                }
                sb.append ( "Mifare Classic type: " );
                sb.append ( type );
                sb.append ( '\n' );

                sb.append ( "Mifare size: " );
                sb.append ( mifareTag.getSize ( ) + " bytes" );
                sb.append ( '\n' );

                sb.append ( "Mifare sectors: " );
                sb.append ( mifareTag.getSectorCount ( ) );
                sb.append ( '\n' );

                sb.append ( "Mifare blocks: " );
                sb.append ( mifareTag.getBlockCount ( ) );
            }

            if ( tech.equals ( MifareUltralight.class.getName ( ) ) ) {
                sb.append ( '\n' );
                MifareUltralight mifareUlTag = MifareUltralight.get ( tag );
                String type = "Unknown";

                switch ( mifareUlTag.getType ( ) ) {

                    case MifareUltralight.TYPE_ULTRALIGHT:
                        type = "Ultralight";
                        break;

                    case MifareUltralight.TYPE_ULTRALIGHT_C:
                        type = "Ultralight C";
                        break;
                }

                sb.append ( "Mifare Ultralight type: " );
                sb.append ( type );
            }
        }

        return sb.toString ( );
    }

    private String getHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private long getDec ( byte [ ] bytes ) {
        long result = 0;
        long factor = 1;
        for (int i = 0; i < bytes.length; ++i) {
            long value = bytes[i] & 0xffl;
            result += value * factor;
            factor *= 256l;
        }
        return result;
    }

    private long getReversed ( byte [ ] bytes ) {
        long result = 0;
        long factor = 1;
        for ( int i = bytes.length - 1; i >= 0; --i ) {
            long value = bytes [ i ] & 0xffl;
            result += value * factor;
            factor *= 256l;
        }
        return result;
    }

    void buildTagViews ( NdefMessage [ ] msgs ) {

        if ( msgs == null || msgs.length == 0 ) {
            return;
        }

        List<ParsedNdefRecord> records = NdefMessageParser.parse ( msgs [ 0 ] );

        final int size = records.size ( );

        for ( int i = 0; i < size; i++ ) {
            Log.v ( "NFC Content: ", records.get ( i ).getText ( ) ) ;

            triggerEvent ( records.get ( i ).getText ( ) );

//            EditText fv = ( EditText ) getWindow ( ).getCurrentFocus ( );
//            fv.setText ( records.get ( i ).getText ( ) );

        }
    }

//  *********************************************************************
//  *********************************************************************
//  **** NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC NFC ****
//  *********************************************************************
//  ***** END ***********************************************************
//  *********************************************************************

    public void showAlert ( String title, String message ) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    /**
     *
     * @param sensorTagCode
     */
    private void triggerEvent ( String sensorTagCode ) {

        //   AuthorizationToken
        SharedPreferences sharedPreferences = getSharedPreferences ( HeimdallPrefs.heimdallPrefs, Context.MODE_PRIVATE );
        String authorizationToken = sharedPreferences.getString( HeimdallPrefs.authorizationToken, "" );

        //   timestamp
        long time = ( System.currentTimeMillis ( ) );
        Timestamp tsTemp = new Timestamp ( time );

        RequestQueue queue = Volley.newRequestQueue ( this );
        String url = HeimdallPrefs.BASE_URI + HeimdallPrefs.USER_EVENT;

        Log.v ( "triggerEvent", url );

        HashMap<String, String> params = new HashMap<> ( );
                                params.put ( "authToken",     authorizationToken  );
                                params.put ( "sensorTagCode", sensorTagCode       );
                                params.put ( "timestamp",     tsTemp.toString ( ) );

        JSONObject jobj = new JSONObject ( params );
        Log.v ( "triggerEvent", jobj.toString ( ) );

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest ( Request.Method.POST, url, jobj, new Response.Listener<JSONObject> ( ) {

            @Override
            public void onResponse ( JSONObject response ) {
                Log.v ( "triggerEvent", response.toString ( ) );
                try {
                    ResponseEventRec rer = new ResponseEventRec( );
                    rer.setResultCode    ( response.getString ( "resultCode"            ) );
                    rer.setResultMessage ( response.getString ( "resultMessage"         ) );
//                    rer.setOpTimestamp   ( response.getString ( "opTimestamp"           ) );
                    rer.setAuthToken     ( response.getJSONObject ( "payload" ).getString ( "authToken"     ) );
                    rer.setAuthToken     ( response.getJSONObject ( "payload" ).getString ( "sensorTagCode" ) );
                    rer.setAuthToken     ( response.getJSONObject ( "payload" ).getString ( "timestamp"     ) );

//                    lr.setStatusCode         ( Integer.parseInt ( response.getString ( "statusCode"    ) ) );
//                    lr.setMessage            ( response.getString ( "message"                            ) );
//                    lr.setName               ( response.getString ( "name"                               ) );
//                    lr.setLastName           ( response.getString ( "lastName"                           ) );
//                    lr.setAuthorizationToken ( response.getString ( "authorizationToken"                 ) );

//                    processLR ( lr );

                } catch ( JSONException e ) {
                    e.printStackTrace ( );
                }
            }
        }, new Response.ErrorListener ( ) {

            @Override
            public void onErrorResponse ( VolleyError error ) {
                Log.v ( "triggerEvent", "That did not work." );

            }
        } );

        queue.add ( jsonObjectRequest );

    }

    //    Eddystone
    private EddystoneListener createEddystoneListener ( ) {
        return new SimpleEddystoneListener ( ) {
            @Override
            public void onEddystoneDiscovered(IEddystoneDevice eddystone, IEddystoneNamespace namespace) {
                Log.i("HeimdallMobile", "Eddystone discovered: " + eddystone.getUniqueId());
            }
        };
    }

    //    Eddystone
    private void startScanning ( ) {
        proximityManager.connect ( new OnServiceReadyListener( ) {
            @Override
            public void onServiceReady ( ) {
                proximityManager.startScanning ( );
            }
        });
    }

}
