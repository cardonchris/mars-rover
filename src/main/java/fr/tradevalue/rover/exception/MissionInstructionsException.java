package fr.tradevalue.rover.exception;

public class MissionInstructionsException extends RuntimeException {
    public MissionInstructionsException(final String message) {
        super(message);
    }

    public MissionInstructionsException(String message, Exception e) {
        super(message, e);
    }
}
