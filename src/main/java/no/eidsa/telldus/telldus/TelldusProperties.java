package no.eidsa.telldus.telldus;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "telldus")
@Data
@Service
public class TelldusProperties {

    @NotNull
    private String bearerToken;

    @NotNull
    private String url;

}
