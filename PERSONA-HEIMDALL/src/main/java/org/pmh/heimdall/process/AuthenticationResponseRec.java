/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pmh.heimdall.process;

/**
 *
 * @author pmarquez - 2016-05-01 20:37
 */
public class AuthenticationResponseRec {
   private String                message;
   private AuthenticationDataRec data;

    public AuthenticationResponseRec ( ) {
        this.message = "";
        this.data    = new AuthenticationDataRec ( );
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
     * @return the data
     */
    public AuthenticationDataRec getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(AuthenticationDataRec data) {
        this.data = data;
    }
   
   
}
