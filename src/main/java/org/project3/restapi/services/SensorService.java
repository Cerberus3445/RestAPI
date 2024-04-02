package org.project3.restapi.services;

import org.modelmapper.ModelMapper;
import org.project3.restapi.dto.SensorDTO;
import org.project3.restapi.exception.SensorNotFoundException;
import org.project3.restapi.models.Sensor;
import org.project3.restapi.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public void register(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findSensorById(int id){
        Optional<Sensor> optionalSensor = sensorRepository.findById(id);
        return Optional.ofNullable(optionalSensor.orElseThrow(SensorNotFoundException::new));
    }

    public Sensor convertToSensor(SensorDTO sensorDTO){
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
        sensor.setMeasurementList(new ArrayList<>());
        return sensor;
    }

    public SensorDTO convertToSensorDTO(Optional<Sensor> sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
