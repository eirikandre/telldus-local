package no.eidsa.telldus.telldus;

import no.eidsa.telldus.telldus.domain.Device;
import no.eidsa.telldus.telldus.domain.Sensor;
import no.eidsa.telldus.telldus.domain.Status;

import java.util.List;

public interface TelldusService {
    List<Sensor> getSensorList();

    Sensor getSensorInfo(Integer sensorId);

    Device getDeviceInfo(Integer deviceId);

    List<Device> getDevices();

    Status deviceTurnOff(Integer deviceId);

    Status deviceTurnOn(Integer deviceId);
}
