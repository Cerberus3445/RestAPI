package org.project3.restapi.util;

public class SensorErrorResponse {
    private float timestamp;

    private String errorDescription;

    public SensorErrorResponse(float timestamp, String errorDescription) {
        this.timestamp = timestamp;
        this.errorDescription = errorDescription;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
