
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
 * PwdRecoveryRec.java<br><br>
 * Creation Date 2015-09-01 17:50<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-09-01 17:50<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-09-01 17:50
 */
public class PwdRecoveryRec {
    protected int           lineCode;
    protected String        email;
    protected int           userCode;
    protected int           messageType;
    protected String        message;
    protected String        uuid;
    protected LocalDateTime dateRequested;
    protected int           expiresIn;
    protected LocalDateTime dateRegen;

    private   String        password;
    private   String        rptPassword;

    public PwdRecoveryRec ( ) {
        this.lineCode      = 0;
        this.email         = "";
        this.userCode      = 0;
        this.messageType   = 0;
        this.message       = "";
        this.uuid          = "";
        this.dateRequested = LocalDateTime.MIN;
        this.expiresIn     = 0;
        this.dateRegen     = LocalDateTime.MIN;

        this.password      = "";
        this.rptPassword   = "";

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

    /**
     * @return the userCode
     */
    public int getUserCode() {
        return userCode;
    }

    /**
     * @param userCode the userCode to set
     */
    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    /**
     * @return the messageType
     */
    public int getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(int messageType) {
        this.messageType = messageType;
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
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the applicationDate
     */
    public String getDateRequested ( ) {
        return dateRequested.format ( DateTimeFormatter.ofPattern ( "dd-MM-yyyy" ) );
    }

    public String getDateRequestedTime ( ) {
        return dateRequested.format ( DateTimeFormatter.ofPattern ( "dd-MM-yyyy HH:mm:ss" ) );
    }

    @JsonIgnore
    public String getDateRequestedTS ( ) {
        return dateRequested.format ( DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" ) );
    }

    @JsonIgnore
    public LocalDateTime getDateRequestedLDT ( ) {
        return dateRequested;
    }

    /**
     * @param dateRequested the dateRequested to set
     */
//    @JsonIgnore
    public void setDateRequested ( String dateRequested ) {
//        System.out.println ( "CreationDate: " + creationTimestamp );
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" );
        try {
            this.dateRequested = LocalDateTime.parse ( dateRequested, dtf );
        } catch ( DateTimeParseException dtpe ) {            
            this.dateRequested = LocalDateTime.MIN;
        }
    }

    @JsonIgnore
    public void setDateRequested ( LocalDateTime dateRequested ) {
        this.dateRequested = dateRequested;
    }
    
    /**
     * @return the expiresIn
     */
    public int getExpiresIn() {
        return expiresIn;
    }

    /**
     * @param expiresIn the expiresIn to set
     */
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * @return the applicationDate
     */
    public String getDateRegen ( ) {
        return dateRegen.format ( DateTimeFormatter.ofPattern ( "dd-MM-yyyy" ) );
    }

    public String getDateRegenTime ( ) {
        return dateRegen.format ( DateTimeFormatter.ofPattern ( "dd-MM-yyyy HH:mm:ss" ) );
    }

    @JsonIgnore
    public String getgetDateRegenTS ( ) {
        return dateRegen.format ( DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" ) );
    }

    @JsonIgnore
    public LocalDateTime getDateRegenLDT ( ) {
        return dateRequested;
    }

    /**
     * @param dateRegen the dateRegen to set
     */
//    @JsonIgnore
    public void setDateRegen ( String dateRegen ) {
//        System.out.println ( "CreationDate: " + creationTimestamp );
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" );
        try {
            this.dateRegen = LocalDateTime.parse ( dateRegen, dtf );
        } catch ( DateTimeParseException dtpe ) {            
            this.dateRegen = LocalDateTime.MIN;
        }
    }

    @JsonIgnore
    public void setDateRegen ( LocalDateTime dateRegen ) {
        this.dateRegen = dateRegen;
    }

    /**
     * @return the lineCode
     */
    public int getLineCode() {
        return lineCode;
    }

    /**
     * @param lineCode the lineCode to set
     */
    public void setLineCode(int lineCode) {
        this.lineCode = lineCode;
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
     * @return the rptPassword
     */
    public String getRptPassword() {
        return rptPassword;
    }

    /**
     * @param rptPassword the rptPassword to set
     */
    public void setRptPassword(String rptPassword) {
        this.rptPassword = rptPassword;
    }
}
