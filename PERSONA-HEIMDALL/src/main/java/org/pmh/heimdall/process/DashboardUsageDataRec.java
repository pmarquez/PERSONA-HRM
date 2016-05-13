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
public class DashboardUsageDataRec {
    private String sensorName;
    private int    useCount;
    private String hour;

    public DashboardUsageDataRec ( ) {
        this.sensorName = "";
        this.useCount   = 0;
        this.hour       = "";
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
     * @return the useCount
     */
    public int getUseCount() {
        return useCount;
    }

    /**
     * @param useCount the useCount to set
     */
    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    /**
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(String hour) {
        this.hour = hour;
    }
}
