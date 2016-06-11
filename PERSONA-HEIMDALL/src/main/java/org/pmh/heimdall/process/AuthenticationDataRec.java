
package org.pmh.heimdall.process;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * AuthenticationDataRec.java<br><br>
 * Creation Date 2016-05-01 20:39<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2016-05-01 20:39<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-05-01 20:39
 */
public class AuthenticationDataRec {
    private String userToken;
    private String username;
    private String password;
    private int    enabled;
    private int    personCode;
    private String documentType;
    private String documentNumber;
    private String email;
    private String name;
    private String surename;
    private String phone;

    public AuthenticationDataRec ( ) {
        this.userToken      = "";
        this.personCode     = 0;
        this.username       = "";
        this.password       = "";
        this.enabled        = 0;
        this.documentType   = "";
        this.documentNumber = "";
        this.email          = "";
        this.name           = "";
        this.surename       = "";
        this.phone          = "";
    }

    /**
     * @return the userToken
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * @param userToken the userToken to set
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    public int getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the personCode
     */
    public int getPersonCode() {
        return personCode;
    }

    /**
     * @param personCode the personCode to set
     */
    public void setPersonCode(int personCode) {
        this.personCode = personCode;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * @param documentNumber the documentNumber to set
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the surename
     */
    public String getSurename() {
        return surename;
    }

    /**
     * @param surename the surename to set
     */
    public void setSurename(String surename) {
        this.surename = surename;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
