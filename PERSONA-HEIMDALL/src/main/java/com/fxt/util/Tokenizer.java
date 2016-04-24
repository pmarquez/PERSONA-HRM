
package com.fxt.util;

//   Standard Libraries Imports
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * Tokenizer.java<br/><br/>
 * Creation Date 2015-08-10 07:33<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-08-10 07:33<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-08-10 07:33
 */
public class Tokenizer {

    public static List<String> tokenizeString ( String tokensLine, String tokenSep ) {

        List<String> tokens = new ArrayList<> ( );
        
        StringTokenizer st = new StringTokenizer ( tokensLine, tokenSep );

        while ( st.hasMoreTokens ( ) ) {
            tokens.add ( ( String ) st.nextToken ( ) );
        }
        
        return tokens;
    }
}
