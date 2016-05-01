
package org.pmh.heimdall.process;

/**
 *
 * @author pmarquez - 2016-05-01 20:39
 */
public class AuthenticationDataRec {
    private String token;
    private int    personCode;

    public AuthenticationDataRec ( ) {
        this.token      = "";
        this.personCode = 0;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
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

}
