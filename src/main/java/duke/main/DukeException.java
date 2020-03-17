package duke.main;

/**
 * A class for user-specific exceptions.
 */
public class DukeException extends Exception {
    String errorMsg = "Error is not specified";

    DukeException(String s) {
        errorMsg = s;
    }

    @Override
    public String toString() {
        return errorMsg;
    }
}