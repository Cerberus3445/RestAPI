package org.project3.restapi.dto;

import jakarta.validation.constraints.*;

public class MeasurementDTO {

    @NotNull(message = "Value should not be empty")
    @DecimalMin(value = "-100.00", message = "dsdsds")
    private double value;


    @NotNull(message = "Raining field should not be empty")
    private boolean raining;

    @NotNull(message = "Sensor name should not be empty")
    private SensorDTO sensorDTO;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}
