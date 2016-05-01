
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
 * <p></p>
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
public class EventShortRec {
    private int           eventCode;
    private int           companyCode;
    private String        companyName;
    private int           personCode;
    private String        sensorTagCode;
    private LocalDateTime timestamp;

    public EventShortRec ( ) {
        this.eventCode     = 0;
        this.companyCode   = 0;
        this.companyName   = "";
        this.personCode    = 0;
        this.sensorTagCode = "";
        this.timestamp     = LocalDateTime.MAX;
    }

    /**
     * @return the eventCode
     */
    public int getEventCode() {
        return eventCode;
    }

    /**
     * @param eventCode the eventCode to set
     */
    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * @return the companyCode
     */
    public int getCompanyCode() {
        return companyCode;
    }

    /**
     * @param companyCode the companyCode to set
     */
    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
    @JsonIgnore
    public String getTimestampDate ( ) {
        return timestamp.format ( DateTimeFormatter.ofPattern ( "dd-MM-yyyy" ) );
    }

    @JsonIgnore
    public String getTimestampTime ( ) {
        return timestamp.format ( DateTimeFormatter.ofPattern ( "HH:mm:ss" ) );
    }

    public String getTimestamp ( ) {
        return timestamp.format ( DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" ) );
    }

    @JsonIgnore
    public LocalDateTime getTimestampLDT ( ) {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
//    @JsonIgnore
    public void setTimestamp ( String timestamp ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" );
        try {
            this.timestamp = LocalDateTime.parse ( timestamp, dtf );
        } catch ( DateTimeParseException dtpe ) {            
            this.timestamp = LocalDateTime.MIN;
        }
    }

    @JsonIgnore
    public void setTimestamp ( LocalDateTime timestamp ) {
        this.timestamp = timestamp;
    }

}
