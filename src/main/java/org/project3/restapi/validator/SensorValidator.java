package org.project3.restapi.validator;

import org.project3.restapi.dto.SensorDTO;
import org.project3.restapi.models.Sensor;
import org.project3.restapi.repositories.SensorRepository;
import org.project3.restapi.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorRepository sensorRepository;

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorRepository sensorRepository, SensorService sensorService) {
        this.sensorRepository = sensorRepository;
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        Sensor sensor = sensorService.convertToSensor(sensorDTO);
        if(sensorRepository.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "", "Sensor with this name already exists");
        }
    }
}
