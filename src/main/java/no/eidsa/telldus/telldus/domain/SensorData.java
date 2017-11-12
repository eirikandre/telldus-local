package no.eidsa.telldus.telldus.domain;

import lombok.Data;

@Data
public class SensorData {

    private String name;
    private Double scale;
    private Double value;

}
