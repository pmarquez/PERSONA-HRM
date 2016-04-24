
package com.fxt.util;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * StringUtil.java<br/><br/>
 * Creation Date 2015-08-08 20:14<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-08-08 20:14<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-08-08 20:14
 */
public class StringUtil {
    
    /**
     * Transform a String into its hexadecimal representation
     * @param theStr
     * @return The hexadecimal representation of the String
     */
    static public String string2hex ( String theStr ) {

        String hexString = "";

        for( int i = 0; i < theStr.length ( ); i++ ) {
            String tempString = Integer.toHexString ( theStr.charAt ( i ) );
            hexString += ( tempString.length ( ) == 2 ) ? tempString : "0" + tempString;
        }

        return hexString;
    }

    /**
     * Transform a hexadecimal String into its String representation
     * @param theHexStr
     * @return The String representation of the hexadecimal String
     */
    static public String hex2string ( String theHexStr ) {
        
        String theString = "";
        int i = 0;

        while ( i < theHexStr.length ( ) ) {
            String theSubStr = theHexStr.substring ( i, i + 2 );
                   theSubStr = ( ( theSubStr.substring ( 0, 1 ) ).equals ( "0" ) ) ? theSubStr.substring ( 1, 2 ) : theSubStr;
            char tempChar = ( char ) Integer.parseInt ( theSubStr, 16 ); // "abc".substring(2,3);
//            char tempChar = ( char ) Integer.parseInt ( theHexStr.substring ( i, i + 2 ), 16 ); // "abc".substring(2,3);
            theString += tempChar;
            i += 2;
        }

        return theString;
    }

    /**
     * Transforms a hex String into byte [ ]
     * Created by StackOverflow user: Dave L. (http://stackoverflow.com/users/3093/dave-l)
     * on StackOverflow entry: http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
     * @param s
     * @return 
     */
    public static byte [ ] hexStringToByteArray ( String s ) {
        int len = s.length ( );
        byte[ ] data = new byte [ len / 2 ];
        for ( int i = 0; i < len; i += 2 ) {
            data [ i / 2 ] = ( byte ) ( ( Character.digit ( s.charAt ( i ), 16 ) << 4 )
                                 + Character.digit ( s.charAt ( i + 1 ), 16 ) );
        }
        return data;
    }

}
