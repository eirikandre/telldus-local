package no.eidsa.telldus.telldus.domain;

import lombok.Data;

import java.util.List;

@Data
public class Sensor {

    private int id;
    private String model;
    private String name;
    private String protocol;
    private int sensorId;
    private boolean novalues;

    private List<SensorData> data;

}
