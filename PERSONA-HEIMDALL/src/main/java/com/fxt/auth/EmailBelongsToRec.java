
package com.fxt.auth;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * EmailBelongsToRec.java<br/><br/>
 * Creation Date 2015-09-23 11:47<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-09-23 11:47<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-09-23 11:47
 */
public class EmailBelongsToRec {
    protected int    userCode;
    protected String email;

    public EmailBelongsToRec ( ) {
        this.userCode = userCode;
        this.email    = email;
    }

    /**
     * @return the userCode
     */
    public int getUserCode ( ) {
        return userCode;
    }

    /**
     * @param userCode the userCode to set
     */
    public void setUserCode ( int userCode ) {
        this.userCode = userCode;
    }

    /**
     * @return the email
     */
    public String getEmail ( ) {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail ( String email ) {
        this.email = email;
    }
    
    
    
}
