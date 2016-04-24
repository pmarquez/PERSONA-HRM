
package org.pmh.heimdall.process;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Application Domain Imports


/**
 * EventRec.java<br><br>
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
public class EventRec extends EventShortRec {
    private String        personFirstName;
    private String        personLastName;
    private int           sensorCode;
    private String        sensorName;
    private int           sensorTypeCode;
    private String        sensorTypeName;

    public EventRec ( ) {
        super ( );
        
        this.personFirstName = "";
        this.personLastName  = "";
        this.sensorCode      = 0;
        this.sensorName      = "";
        this.sensorTypeCode  = 0;
        this.sensorTypeName  = "";
    }

    /**
     * @return the personFirstName
     */
    public String getPersonFirstName() {
        return personFirstName;
    }

    /**
     * @param personFirstName the personFirstName to set
     */
    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    /**
     * @return the personLastName
     */
    public String getPersonLastName() {
        return personLastName;
    }

    /**
     * @param personLastName the personLastName to set
     */
    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    /**
     * @return the sensorCode
     */
    public int getSensorCode() {
        return sensorCode;
    }

    /**
     * @param sensorCode the sensorCode to set
     */
    public void setSensorCode(int sensorCode) {
        this.sensorCode = sensorCode;
    }

    /**
     * @return the sensorName
     */
    public String getSensorName() {
        return sensorName;
    }

    /**
     * @param sensorName the sensorName to set
     */
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    /**
     * Utility method that dumps the contents of a DocumentRec for trace and debugging purposes.
     */
    public void spillYourGuts ( ) {
        System.out.println ( "************************************************************************" );
        System.out.println ( "**** EventRec *******************************************************" );
        System.out.println ( "************************************************************************" );

        System.out.println ( "**** EventCode       : " + this.getEventCode       ( ) );
        System.out.println ( "**** Timestamp       : " + this.getTimestamp       ( ) );
        System.out.println ( "**** SensorCode      : " + this.getSensorCode      ( ) );
        System.out.println ( "**** SensorName      : " + this.getSensorName      ( ) );
        System.out.println ( "**** SensorTagCode   : " + this.getSensorTagCode   ( ) );
        System.out.println ( "**** CompanyCode     : " + this.getCompanyCode     ( ) );
        System.out.println ( "**** CompanyName     : " + this.getCompanyName     ( ) );
        System.out.println ( "**** PersonLastName  : " + this.getPersonLastName  ( ) );
        System.out.println ( "**** PersonCode      : " + this.getPersonCode      ( ) );
        System.out.println ( "**** PersonFirstName : " + this.getPersonFirstName ( ) );
        System.out.println ( "**** PersonLastName  : " + this.getPersonLastName  ( ) );

        System.out.println ( "************************************************************************" );
        System.out.println ( "************************************************************************" );
        System.out.println ( "************************************************************************" );
    }

    /**
     * @return the sensorTypeCode
     */
    public int getSensorTypeCode() {
        return sensorTypeCode;
    }

    /**
     * @param sensorTypeCode the sensorTypeCode to set
     */
    public void setSensorTypeCode(int sensorTypeCode) {
        this.sensorTypeCode = sensorTypeCode;
    }

    /**
     * @return the sensorTypeName
     */
    public String getSensorTypeName() {
        return sensorTypeName;
    }

    /**
     * @param sensorTypeName the sensorTypeName to set
     */
    public void setSensorTypeName(String sensorTypeName) {
        this.sensorTypeName = sensorTypeName;
    }

}
