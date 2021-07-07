/**
 * Represents the exception when unsupported commands are entered.
 */
class InvalidCommandArgumentException extends Exception {
    InvalidCommandArgumentException(String message) {
        super(message);
    }
}