
package com.fxt.auth;

//   Standard Libraries Imports
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//   Third Party Libraries Imports
import com.fasterxml.jackson.annotation.JsonIgnore;

//   FENIX Framework Imports


//   Domain Imports


/**
 * PwdRegenerationRec.java<br/><br/>
 * Creation Date 2015-09-15 13:07<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-09-15 13:07<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-09-15 13:07
 */
public class PwdRegenerationRec extends PwdRecoveryRec {
    protected String        pwd;
    protected String        pwdRpt;
    protected String        bcryptedPwd;

    public PwdRegenerationRec ( ) {
        super ( );

        this.pwd    = "";
        this.pwdRpt = "";
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the pwdRpt
     */
    public String getPwdRpt() {
        return pwdRpt;
    }

    /**
     * @param pwdRpt the pwdRpt to set
     */
    public void setPwdRpt(String pwdRpt) {
        this.pwdRpt = pwdRpt;
    }

    /**
     * @return the bcryptedPwd
     */
    public String getBcryptedPwd() {
        return bcryptedPwd;
    }

    /**
     * @param bcryptedPwd the bcryptedPwd to set
     */
    public void setBcryptedPwd(String bcryptedPwd) {
        this.bcryptedPwd = bcryptedPwd;
    }

}
