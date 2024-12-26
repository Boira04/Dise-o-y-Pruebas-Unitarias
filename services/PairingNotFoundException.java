package data.services;

public class PairingNotFoundException extends RuntimeException {
    public PairingNotFoundException(String message) {
        super(message);
    }
}
