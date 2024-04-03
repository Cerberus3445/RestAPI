package org.project3.restapi.services;

import org.modelmapper.ModelMapper;
import org.project3.restapi.dto.MeasurementDTO;
import org.project3.restapi.dto.SensorDTO;
import org.project3.restapi.models.Measurement;
import org.project3.restapi.models.Sensor;
import org.project3.restapi.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService, ModelMapper modelMapper) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    public void addMeasurement(MeasurementDTO measurementDTO){
        Sensor sensor = sensorService.findSensorByName(measurementDTO.getSensorDTO().getName());
        Measurement measurement = convertToMeasurement(measurementDTO);
        sensor.getMeasurementList().add(measurement);
        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
    }

    public List<MeasurementDTO> allMeasurement(){
        List<Measurement> list = measurementRepository.findAll();
        List<MeasurementDTO> measurementDTO = new ArrayList<>();
        for(Measurement measurement : list){
            MeasurementDTO measurementDTO1 = convertToMeasurementDTO(measurement);
            measurementDTO1.setSensorDTO(sensorService.convertToSensorDTO(measurement.getSensor()));
            measurementDTO.add(measurementDTO1);
        }
        return measurementDTO;
    }

    public int showRainingDays(){
        List<MeasurementDTO> measurementDTOList = allMeasurement();
        int count = 0;
        for(MeasurementDTO measurementDTO : measurementDTOList){
            if(measurementDTO.isRaining()){
                count++;
            }
        }
        return count;
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
