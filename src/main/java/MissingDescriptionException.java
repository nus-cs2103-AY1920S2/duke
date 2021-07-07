/**
 * Represents an exception when a description is missing in the command.
 */
class MissingDescriptionException extends Exception {
    MissingDescriptionException(String message) {
        super(message);
    }
}