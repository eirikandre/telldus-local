package no.eidsa.telldus.telldus.domain;

import lombok.Data;

@Data
public class Device {

    private Integer id;
    private Integer methods;
    private String model;
    private String name;
    private String protocol;
    private Integer state;
    private Integer statevalue;
    private Integer type;

}
