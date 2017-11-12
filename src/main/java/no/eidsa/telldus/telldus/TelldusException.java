package no.eidsa.telldus.telldus;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TelldusException extends RuntimeException {

    public TelldusException(String text) {
        super(text);
        log.error(text);
    }
}
