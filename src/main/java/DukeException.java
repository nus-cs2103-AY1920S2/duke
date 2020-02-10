/**
 * Generates an error message for improper user input.
 */

public class DukeException extends IllegalArgumentException {
    protected String errorMsg = "";

    @Override
    public String toString() {
        assert !errorMsg.isEmpty() : "Error message should not be empty.";
        String text = "                    -~~o~~-\n"
                + errorMsg + "\n"
                + "                    -~~o~~-\n";
        return text;
    }
}
