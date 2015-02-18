
package com.pmh.hrm.persona.auth;

import com.pmh.hrm.persona.user.UserRec;

/**
 *
 * @author pmarquez - 2014-09-27 15:55
 */
public class LoginRec extends UserRec {
    
    private String statusCode;
    private String statusMessage;
    
    public LoginRec ( ) {
        super ( );

        this.statusCode    = "";
        this.statusMessage = "";

    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
