package net.etfbl.sni.insurancesystem.exception;

public class WrongActivationCodeException extends RuntimeException {
    public WrongActivationCodeException(String message) {
        super(message);
    }
}
