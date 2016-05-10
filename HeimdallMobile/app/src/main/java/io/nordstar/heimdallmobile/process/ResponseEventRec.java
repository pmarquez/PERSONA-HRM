package io.nordstar.heimdallmobile.process;

/**
 * Created by pmarquez on 09/05/2016 - 17:43
 */
public class ResponseEventRec {
    private String resultCode;
    private String resultMessage;
    private String opTimestamp;
    private String authToken;
    private String sensorTagCode;
    private String timestamp;

    public ResponseEventRec ( ) {
        this.timestamp     = "";
        this.resultCode    = "";
        this.resultMessage = "";
        this.opTimestamp   = "";
        this.authToken     = "";
        this.sensorTagCode = "";
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getOpTimestamp() {
        return opTimestamp;
    }

    public void setOpTimestamp(String opTimestamp) {
        this.opTimestamp = opTimestamp;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getSensorTagCode() {
        return sensorTagCode;
    }

    public void setSensorTagCode(String sensorTagCode) {
        this.sensorTagCode = sensorTagCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
