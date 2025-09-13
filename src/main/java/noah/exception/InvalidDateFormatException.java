package noah.exception;

/**
 * Exception for wrong input of date format.
 */
public class InvalidDateFormatException extends NoahException {
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
