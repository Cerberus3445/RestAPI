package org.project3.restapi.controllers;

import jakarta.validation.Valid;
import org.project3.restapi.dto.SensorDTO;
import org.project3.restapi.exception.SensorNotCreatedException;
import org.project3.restapi.exception.SensorNotFoundException;
import org.project3.restapi.models.Sensor;
import org.project3.restapi.services.SensorService;
import org.project3.restapi.util.SensorErrorResponse;
import org.project3.restapi.validator.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService sensorService;

    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        sensorValidator.validate(sensorDTO, bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrorList){
                stringBuilder.append(fieldError.getField() + " - " + fieldError.getDefaultMessage() + "; ");
            }
            throw new SensorNotCreatedException(stringBuilder.toString());
        }
        sensorService.register(sensorService.convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public SensorDTO showOneSensor(@PathVariable("id") int id){
        return sensorService.convertToSensorDTO(sensorService.findSensorById(id));
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handlerException(SensorNotCreatedException sensorNotCreatedException){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(System.currentTimeMillis(), sensorNotCreatedException.getMessage());
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handlerException(SensorNotFoundException sensorNotFoundException){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(System.currentTimeMillis(), "Sensor with this id wasn't found");
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
