/**
 * Generates an error message for improper user input.
 */

public class DukeException extends IllegalArgumentException {
    protected String errorMsg = "";

    @Override
    public String toString() {
        String text = "                    -~~o~~-\n"
                + errorMsg + "\n"
                + "                    -~~o~~-\n";
        return text;
    }
}
