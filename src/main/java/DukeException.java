/**
 * Handles invalid input by user and prints error message.
 */
public class DukeException extends Throwable {
    private String err;
    public DukeException(String error) {
        this.err = error;
    }

    public String errMsg() {
        return "\u2639" + " " + err + "\n";
    }
}
