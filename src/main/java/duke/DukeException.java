package duke;

/**
 * DukeException occurs when commands pertaining to the Duke program are invalid.
 */
public class DukeException extends Exception {

    private String error;


    private final static String LINE = "    ____________________________________________________________";
    private final static String INVALID_COMMAND = "OOPS! I'm sorry, but I don't know what that means :(";
    private final static String INVALID_DATE_TIME = "OOPS! Please input deadline DATE and TIME in the format: <task> /by DD/MM/YYYY 2359.";

    public DukeException(String error) {
        super(error);
        this.error = error;
    }

    @Override
    public String toString() {
        String output = "";
        if (this.error.equals("todo") || this.error.equals("deadline") || this.error.equals("event")) {
            output += "OOPS! The description of a " + this.error + " cannot be empty.";

        } else if (this.error.equals("dateTime")) {
            output = INVALID_DATE_TIME;
        } else {
            output = INVALID_COMMAND;
        }

        return output;
    }

}
