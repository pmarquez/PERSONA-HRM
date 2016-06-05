package com.fxt.cryptography;

//   Standard Libraries Imports
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

//   Third Party Libraries Imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//   FENIX Framework Imports
import com.fxt.util.StringUtil;

//   Domain Imports


/**
 * StrongAES.java<br><br>
 * Creation Date 2015-08-10 06:57<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-08-10 06:57<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-08-10 06:57
 */
public class StrongAES {
    
    String keyString = "H31md4llK3y20160";   //   16 Bytes - 128 bits (Strongest possible for us)

    byte [ ] key = StringUtil.hexStringToByteArray ( StringUtil.string2hex ( keyString ) );

    // Objects required for encryption/decryption
    private final SecretKey      secretKey;
    private final Logger         logger;
    private final Base64.Encoder encoder;
    private final Base64.Decoder decoder;

    public StrongAES ( ) {
        this.secretKey = new SecretKeySpec ( key, "AES" );
        this.logger    = LoggerFactory.getLogger ( getClass ( ) );
        this.encoder   = Base64.getUrlEncoder ( );
        this.decoder   = Base64.getUrlDecoder ( );        
    }
    
    public String encrypt ( String plainText ) {

        try {
            // Get byte array which has to be encrypted.
            byte[] plainTextByte = plainText.getBytes ( );

            // Encrypt the bytes using the secret key
            Cipher cipher = Cipher.getInstance ( "AES" );
            cipher.init ( Cipher.ENCRYPT_MODE, secretKey );
            byte [ ] encryptedByte = cipher.doFinal ( plainTextByte );

            // Use Base64 encoder to encode the byte array
            // into Base 64 representation. Requires Java 8.
            return encoder.encodeToString ( encryptedByte );

        } catch ( Exception e ) {
            logger.error ( "Failed to encrypt", e );
        }

        return null;
    }
    
    public String decrypt ( String encrypted ) {
        try {
            // Decode Base 64 String into bytes array.
            byte[] encryptedByte = decoder.decode ( encrypted );

            //Do the decryption
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedByte = cipher.doFinal(encryptedByte);

            // Get hexadecimal string from the byte array.
            return new String ( decryptedByte );

        } catch (Exception e) {
            logger.error("Failed to decrypt {}", encrypted, e);
        }
        return null;
    }

//    public static void main(String[] args) {
//
//        String querystring = "|b32da051-9651-4c3c-9a76-e8650b662942|es-ES|1|";
//        
//        StrongAES app = new StrongAES ( );
//        
//        System.out.println ( "QUERYSTRING: " + querystring );
//        String encryptedString = app.encrypt ( querystring );
//        System.out.println ( "ENCRYPTED:   " + encryptedString );
//        
//        String hexenc = StringUtil.string2hex ( encryptedString );
//        System.out.println ( "ENCODED:     " + hexenc );
//        
//        String hexdec = StringUtil.hex2string ( hexenc );
//        System.out.println ( "DECODED:     " + hexdec );
//        
//        String decryptedString = app.decrypt ( hexdec );
//        System.out.println ( "DECRYPTED:   " + decryptedString );
//        
//    }

}
