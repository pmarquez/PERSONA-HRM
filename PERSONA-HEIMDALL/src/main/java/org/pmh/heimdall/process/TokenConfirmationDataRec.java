/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pmh.heimdall.process;

/**
 *
 * @author pmarquez
 */
public class TokenConfirmationDataRec {
    private String message;
    private int   code;
    private Object data;

    public TokenConfirmationDataRec ( ) {
        this.message = "";
        this.code    = 0;
        this.data    = null;
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
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setObject(Object data) {
        this.data = data;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
    
    
}
