package duke;

/**
 * duke.EmptyDescriptionException extends from Exception class
 * and handle error if the user input is lack of description.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
