package org.project3.restapi.controllers;

import jakarta.validation.Valid;
import org.project3.restapi.dto.MeasurementDTO;
import org.project3.restapi.dto.SensorDTO;
import org.project3.restapi.exception.SensorNotFoundException;
import org.project3.restapi.models.Measurement;
import org.project3.restapi.services.MeasurementService;
import org.project3.restapi.util.SensorErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }
    @GetMapping
    public List<MeasurementDTO> showAllMeasurementDTO(){
        return measurementService.allMeasurement();
    }

    @GetMapping("/rainyDaysCount")
    public int showRainingDays(){
        return measurementService.showRainingDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

        }
        measurementService.addMeasurement(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handlerException(SensorNotFoundException sensorNotFoundException){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(System.currentTimeMillis(), "Sensor with this name wasn't found");
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
