package no.eidsa.telldus.telldus;

import lombok.extern.slf4j.Slf4j;
import no.eidsa.telldus.telldus.domain.Device;
import no.eidsa.telldus.telldus.domain.Devices;
import no.eidsa.telldus.telldus.domain.Sensor;
import no.eidsa.telldus.telldus.domain.SensorList;
import no.eidsa.telldus.telldus.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class TelldusServiceImpl implements TelldusService {

    private final TelldusProperties telldusProperties;
    private final RestTemplate restTemplate;

    @Autowired
    public TelldusServiceImpl(TelldusProperties telldusProperties, RestTemplate restTemplate) {
        Assert.notNull(telldusProperties, "telldusProperties should not be null");
        Assert.notNull(restTemplate, "restTemplate should not be null");
        this.restTemplate = restTemplate;
        this.telldusProperties = telldusProperties;
    }

    @Override
    public List<Sensor> getSensorList() {
        String urlForSensorInfo = telldusProperties.getUrl() + "/sensors/list";
        return restTemplate.postForObject(urlForSensorInfo, generateEntity(), SensorList.class).getSensors();
    }

    @Override
    public Sensor getSensorInfo(Integer sensorId) {
        String urlForSensorInfo = telldusProperties.getUrl() + "/sensor/info";
        return restTemplate.postForObject(urlForSensorInfo, generateEntity(sensorId.toString()), Sensor.class);
    }


    @Override
    public Device getDeviceInfo(Integer deviceId) {
        String urlForSensorInfo = telldusProperties.getUrl() + "/device/info";
        return restTemplate.postForObject(urlForSensorInfo, generateEntity(deviceId.toString()), Device.class);
    }

    @Override
    public List<Device> getDevices() {
        String urlForSensorInfo = telldusProperties.getUrl() + "/devices/list";
        return restTemplate.postForObject(urlForSensorInfo, generateEntity(), Devices.class).getDevices();
    }

    @Override
    public Status deviceTurnOff(Integer deviceId) {
        return triggerDevice(deviceId, "turnOff");
    }

    @Override
    public Status deviceTurnOn(Integer deviceId) {
        return triggerDevice(deviceId, "turnOn");
    }

    private Status triggerDevice(Integer deviceId, String method) {
        String url = telldusProperties.getUrl() + "/device/" + method;

        Status status = restTemplate.postForObject(url, generateEntity(deviceId.toString()), Status.class);

        if (!"success".equals(status.getStatus())) {
            throw new TelldusException("Failed to turn oven on. Status: " + status.getStatus());
        } else {
            return status;

        }
    }

    private HttpEntity<MultiValueMap<String, String>> generateEntity(String id) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("id", id);

        return new HttpEntity<>(formData, getAuthHeader());
    }

    private HttpEntity<MultiValueMap<String, String>> generateEntity() {
        return new HttpEntity<>(new LinkedMultiValueMap<>(), getAuthHeader());
    }

    private HttpHeaders getAuthHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + telldusProperties.getBearerToken());
        return httpHeaders;
    }

}
