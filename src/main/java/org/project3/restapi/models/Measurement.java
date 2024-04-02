package org.project3.restapi.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    private double value;

    @Column(name = "raining")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement(){

    }

    public Measurement(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return id == that.id && Double.compare(value, that.value) == 0 && raining == that.raining && Objects.equals(sensor, that.sensor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, raining, sensor);
    }
}
