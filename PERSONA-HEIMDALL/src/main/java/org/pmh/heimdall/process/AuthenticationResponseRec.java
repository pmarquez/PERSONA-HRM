
package org.pmh.heimdall.process;

/**
 *
 * @author pmarquez - 2016-05-01 20:37
 */
public class AuthenticationResponseRec {
   private String                message;
   private int                   code;
   private AuthenticationDataRec data;

    public AuthenticationResponseRec ( ) {
        this.message = "";
        this.code    = 0;
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
