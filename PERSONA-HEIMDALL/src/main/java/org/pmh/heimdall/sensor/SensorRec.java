
package org.pmh.heimdall.sensor;

/**
 *
 * @author pmarquez - 2016-05-02 10:01
 */

/*

SELECT hei_sensorentity.sensorCode,
       IFNULL(hei_sensorentity.sensorName,'') AS SENSOR_NAME,
       IFNULL(hei_sensorentity.sensorID,'') AS SENSOR_ID,
       IFNULL(hei_sensorentity.sensorNamespace,'') AS SENSOR_NAMESPACE,
       IFNULL(hei_sensorentity.sensorTagCode,'') AS SENSOR_TAG_CODE,
       hei_sensorentity.sensorTypeCode,
       IFNULL(hei_sensortypeentity.sensorTypeName,'') AS SENSOR_TYPE_NAME, 
       hei_sensorentity.active

FROM hei_sensorentity

LEFT OUTER JOIN hei_sensortypeentity ON hei_sensortypeentity.sensorTypeCode = hei_sensorentity.sensorTypeCode 

WHERE  hei_sensorentity.sensorTagCode = '0d287da4-78ae-4df4-86a7-4d140aae4c54';

*/
public class SensorRec {
    private int     sensorCode;
    private String  sensorName;
    private String  sensorID;
    private String  sensorNamespace;
    private String  sensorTagCode;
    private int     sensorTypeCode;
    private String  sensorTypeName;
    private boolean active; 

    public SensorRec ( ) {
        this.sensorCode      = 0;
        this.sensorName      = "";
        this.sensorID        = "";
        this.sensorNamespace = "";
        this.sensorTagCode   = "";
        this.sensorTypeCode  = 0;
        this.sensorTypeName  = "";
        this.active          = false;
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
     * @return the sensorID
     */
    public String getSensorID() {
        return sensorID;
    }

    /**
     * @param sensorID the sensorID to set
     */
    public void setSensorID(String sensorID) {
        this.sensorID = sensorID;
    }

    /**
     * @return the sensorNamespace
     */
    public String getSensorNamespace() {
        return sensorNamespace;
    }

    /**
     * @param sensorNamespace the sensorNamespace to set
     */
    public void setSensorNamespace(String sensorNamespace) {
        this.sensorNamespace = sensorNamespace;
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
