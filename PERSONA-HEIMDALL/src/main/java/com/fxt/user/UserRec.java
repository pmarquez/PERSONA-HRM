
package com.fxt.user;

//   Standard Libraries Imports
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//   Third Party Libraries Imports
import com.fasterxml.jackson.annotation.JsonIgnore;

//   FENIX Framework Imports

//   Application Domain Imports


/**
 * UserRec.java<br><br>
 * Creation Date 2015-05-15 17:50<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-05-15 17:50<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-05-15 17:50
 */
public class UserRec {
    public final static String MASKED_PASSWORD         = "********";
    public final static int    DEFAULT_PASSWORD_LENGTH = 10;

    private int           userCode;
    private String        firstName;
    private String        middleName;
    private String        lastName;
    
    private String        email;
    private String        passwd;

    private int           roleCode;
    private String        role;
    
    private LocalDateTime creationTimestamp;
    private boolean       active;
    
    public UserRec ( ) {
        this.userCode          = 0;
        this.firstName         = "";
        this.middleName        = "";
        this.lastName          = "";
        this.email             = "";
        this.passwd            = "";
        this.roleCode          = 0;
        this.role              = "";
        this.creationTimestamp = LocalDateTime.MIN;
        this.active            = false;

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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    @JsonIgnore
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    @JsonIgnore
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
     * @return the userName
     */
    public String getUserName ( ) {
        return email;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName ( String userName ) {
        this.email = userName;
    }

    /**
     * @return the passwd
     */
    //@JsonIgnore
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd the passwd to set
     */
    public void setPasswd ( String passwd ) {
        this.passwd = passwd;
    }

    /**
     * @return the codRole
     */
    public int getRoleCode ( ) {
        return roleCode;
    }

    /**
     * @param roleCode the roleCode to set
     */
    public void setRoleCode ( int roleCode ) {
        this.roleCode = roleCode;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive ( boolean active ) {
        this.active = active;
    }

    /**
     * @return the creationTimestamp
     */
    public String getCreationTimestamp ( ) {
        return ( creationTimestamp.equals ( LocalDateTime.MIN ) ) ? "" : ( creationTimestamp.getDayOfMonth ( ) + "-" + creationTimestamp.getMonthValue ( ) + "-" + creationTimestamp.getYear ( ) + " " + 
                 creationTimestamp.getHour ( ) + ":" + creationTimestamp.getMinute ( ) + ":" + creationTimestamp.getSecond ( ) );
    }

    @JsonIgnore
    public String getCreationTimestampTS ( ) {
        return ( creationTimestamp.equals ( LocalDateTime.MIN ) ) ? "" : ( creationTimestamp.getYear ( ) + "-" + creationTimestamp.getMonthValue ( ) + "-" + creationTimestamp.getDayOfMonth ( ) + " " + 
                 creationTimestamp.getHour ( ) + ":" + creationTimestamp.getMinute ( ) + ":" + creationTimestamp.getSecond ( ) );
    }

    @JsonIgnore
    public LocalDateTime getCreationTimestampLDT ( ) {
        return creationTimestamp;
    }

    /**
     * @param creationTimestamp the creationTimestamp to set
     */
    @JsonIgnore
    public void setCreationTimestamp ( String creationTimestamp ) {
//        System.out.println ( "CreationDate: " + creationTimestamp );
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" );
        try {
            this.creationTimestamp = LocalDateTime.parse ( creationTimestamp, dtf );
        } catch ( DateTimeParseException dtpe ) {            
            this.creationTimestamp = LocalDateTime.MIN;
        }
    }

    @JsonIgnore
    public void setCreationTimestamp ( LocalDateTime creationTimestamp ) {
        this.creationTimestamp = creationTimestamp;
    }

}
