package fr.tradevalue.rover.exception;

public class InvalidPlateauException extends RuntimeException {
    public InvalidPlateauException(String message, IllegalArgumentException e) {
        super(message, e);
    }
}
