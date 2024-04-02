package org.project3.restapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 3, max = 30, message = "Name size should be between 3 and 30 characters")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurementList;

    public Sensor(){

    }

    public Sensor(String name, List<Measurement> measurementList) {
        this.name = name;
        this.measurementList = measurementList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<Measurement> measurementList) {
        this.measurementList = measurementList;
    }
}
