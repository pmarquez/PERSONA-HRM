
package org.pmh.heimdall.process;


public class TempLoginData {
    private int    codStatus;
    private String message;
    private String authToken;

    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String username;

    public TempLoginData ( ) {
        this.codStatus     = 0;
        this.message       = "";
        this.authToken     = "";
        this.userFirstName = "";
        this.userLastName  = "";
        this.userEmail     = "";
        this.username      = "";
    }

    /**
     * @return the codStatus
     */
    public int getCodStatus() {
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
     * @return the authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken the authToken to set
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * @return the userFirstName
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * @param userFirstName the userFirstName to set
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * @return the userLastName
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * @param userLastName the userLastName to set
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    
}
