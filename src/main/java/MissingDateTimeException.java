/**
 * Represents an exception when there is missing date or time in commands.
 */
class MissingDateTimeException extends Exception {
    MissingDateTimeException(String message) {
        super(message);
    }
}