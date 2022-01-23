package fr.tradevalue.rover.exception;

public class DangerousCommandException extends RuntimeException{
    public DangerousCommandException(final String message) {
        super(message);
    }
}
