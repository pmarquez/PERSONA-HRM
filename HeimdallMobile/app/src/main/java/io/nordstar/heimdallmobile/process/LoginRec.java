
package io.nordstar.heimdallmobile.process;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * LoginRec.java<br><br>
 * Creation Date 2016-05-03 21:51<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2016-05-03 21:51<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-05-03 21:51
 */
public class LoginRec {
//public class LoginRec extends UserRec {

    public static final int    NO_ERROR_CODE = 0;
    public static final String NO_ERROR_MESSAGE = "No error.";

    public static final int    AUTHENTICATION_SUCCESSFUL_CODE = 1;
    public static final String AUTHENTICATION_SUCCESSFUL_MESSAGE = "Login successful";

    public static final int    AUTHENTICATION_FAILED_CODE = 2;
    public static final String AUTHENTICATION_FAILED_MESSAGE = "Login failed";

    public static final int    LDAP_AUTHENTICATION_FAILED_CODE = 99;
    public static final String LDAP_AUTHENTICATION_FAILED_MESSAGE = "LDAP Login Failed";


    private int     statusCode;
    private String  message;
    private boolean userOK;
    private String  name;
    private String  lastName;
    private String  authorizationToken;
    private String  userName;
    private String  passwd;

    public LoginRec ( ) {
        super ( );

        this.statusCode           = LoginRec.NO_ERROR_CODE;
        this.message              = LoginRec.NO_ERROR_MESSAGE;
        this.userOK               = false;
        this.name                 = "";
        this.lastName             = "";
        this.authorizationToken   = "";

    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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
     * @return the userOK
     */
    public boolean isUserOK() {
        return userOK;
    }

    /**
     * @param userOK the userOK to set
     */
    public void setUserOK(boolean userOK) {
        this.userOK = userOK;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the authorizationToken
     */
    public String getAuthorizationToken() {
        return authorizationToken;
    }

    /**
     * @param authorizationToken the authorizationToken to set
     */
    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd the passwd to set
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}