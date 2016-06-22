package io.nordstar.heimdallmobile.application;

import android.app.Application;
import android.util.Log;
import com.kontakt.sdk.android.common.KontaktSDK;

/**
 * Created by pmarquez on 22/06/2016 - 11:07.
 */
public class HeimdallMobile extends Application {

    @Override
    public void onCreate ( ) {
        super.onCreate ( );
        KontaktSDK.initialize ( this );
        Log.d ( "HeimdallMobile", "KontaktSDK Inicializado." );
    }
}
