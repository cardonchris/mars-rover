package fr.tradevalue.rover.exception;

public class InvalidRoverMissionException extends Exception {

    public InvalidRoverMissionException(String message) {
        super(message);
    }

    public InvalidRoverMissionException(String message, IllegalArgumentException e) {
        super(message, e);
    }
}
