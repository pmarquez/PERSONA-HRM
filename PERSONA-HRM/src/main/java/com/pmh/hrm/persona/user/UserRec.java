
package com.pmh.hrm.persona.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author pmarquez - 2014-09-18 01:05
 */
public class UserRec {
    public final static String MASKED_PASSWORD         = "********";
    public final static int    DEFAULT_PASSWORD_LENGTH = 10;

    private int     userCode;
    private String  firstName;
    private String  middleName;
    private String  lastName;
    
    private String  email;
    private String  passwd;

    private int     roleCode;
    private String  role;
    private boolean active;
    
    public UserRec ( ) {
        this.userCode   = 0;
        this.firstName  = "";
        this.middleName = "";
        this.lastName   = "";
        this.email      = "";
        this.passwd     = "";
        this.roleCode   = 0;
        this.role       = "";
        this.active     = false;

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
     * @return the passwd
     */
//    @JsonIgnore
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd the passwd to set
     */
//    @JsonIgnore
    public void setPasswd(String passwd) {
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
    public void setActive(boolean active) {
        this.active = active;
    }
    
}
