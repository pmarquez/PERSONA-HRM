
package org.pmh.heimdall.process;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * AuthenticationResponseRec.java<br><br>
 * Creation Date 2016-05-01 20:37<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2016-05-01 20:37<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-05-01 20:37
 */
public class AuthenticationResponseRec {
   private String                message;
   private int                   code;
   private AuthenticationDataRec data;

    public AuthenticationResponseRec ( ) {
        this.message = "";
        this.code    = 0;
        this.data    = new AuthenticationDataRec ( );
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public AuthenticationDataRec getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(AuthenticationDataRec data) {
        this.data = data;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
   
   
}
