
package org.pmh.heimdall.process;

//   Standard Libraries Imports
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//   Third Party Libraries Imports
import com.fasterxml.jackson.annotation.JsonIgnore;

//   FENIX Framework Imports


//   Application Domain Imports


/**
 * EventShortRec.java<br><br>
 * Creation Date 2016-04-24 11:46<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p>Minimal expression of what an event is. Carries the WHO (authToken), WHERE (sensorTagCode) and WHEN (timestamp) of an event.</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2016-04-24 11:46<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-04-24 11:46
 */

/*
{ "sensorTagCode":"380016cb-c629-4080-8653-17146dd3622d",
  "timestamp":"2016-05-12 16:13:51.451",
  "authToken":"[B@218cf80d" }
*/

public class EventShortRec {
    private String authToken;
    private String sensorTagCode;
    private String timestamp;

    public EventShortRec ( ) {
        this.authToken     = "";
        this.sensorTagCode = "";
        this.timestamp     = "";
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
     * @return the sensorTagCode
     */
    public String getSensorTagCode() {
        return sensorTagCode;
    }

    /**
     * @param sensorTagCode the sensorTagCode to set
     */
    public void setSensorTagCode(String sensorTagCode) {
        this.sensorTagCode = sensorTagCode;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
