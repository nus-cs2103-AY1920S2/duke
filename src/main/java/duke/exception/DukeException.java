package duke.exception;

/**
 * Generates an error message for incorrect user input.
 */

public class DukeException extends IllegalArgumentException {
    protected String errorMsg = "";

    @Override
    public String toString() {
        assert !errorMsg.isEmpty() : "Error message should not be empty.";
        return "                    -~~o~~-\n"
                + errorMsg + "\n"
                + "                    -~~o~~-\n";
    }
}
