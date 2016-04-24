
package com.fxt.auth;


//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports
import com.fxt.user.UserRec;


/**
 * LoginRec.java<br/><br/>
 * Creation Date 2015-08-18 19:37<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-08-18 19:37<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-08-18 19:37
 */
public class LoginRec extends UserRec {
    
    public static final int NO_ERROR                   =  0;
    public static final int AUTHENTICATION_FAILED      =  1;
    public static final int LDAP_AUTHENTICATION_FAILED = 99;

    private int              codStatus;
    private String           message;
    private boolean          userOK;
        
    public LoginRec ( ) {
        super ( );

        this.codStatus            = LoginRec.NO_ERROR;
        this.userOK               = false;
        this.message              = "";

    }

    /**
     * @return the codStatus
     */
    public int getCodStatus ( ) {
        return codStatus;
    }

    /**
     * @param codStatus the codStatus to set
     */
    public void setCodStatus(int codStatus) {
        this.codStatus = codStatus;
    }

    /**
     * @return the message
     */
    public String getMessage ( ) {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage ( String message ) {
        this.message = message;
    }

    /**
     * @return the userOK
     */
    public boolean isUserOK ( ) {
        return userOK;
    }

    /**
     * @param userOK the userOK to set
     */
    public void setUserOK ( boolean userOK ) {
        this.userOK = userOK;
    }

}
