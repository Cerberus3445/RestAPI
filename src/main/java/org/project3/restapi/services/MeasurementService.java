package org.project3.restapi.services;

import org.project3.restapi.dto.MeasurementDTO;
import org.project3.restapi.dto.SensorDTO;
import org.project3.restapi.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    private void addMeasurement(SensorDTO sensorDTO, MeasurementDTO measurementDTO){

    }
}
