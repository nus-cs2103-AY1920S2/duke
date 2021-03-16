package duke;

/**
 * DukeException occurs when commands pertaining to the Duke program are invalid.
 */
public class DukeException extends Exception {

    private String error;


    private final static String LINE = "    ____________________________________________________________";
    private final static String INVALID_COMMAND = "OOPS! Kirby doesn't know what that means :(";
    private final static String INVALID_DATE_TIME = "OOPS! Please input deadline DATE and TIME in the format:\n" +
            "    <task> /by DD/MM/YYYY 2359.\n";
    private final static String INVALID_PRIORITY = "OOPS! Please input priority in the format:\n" +
            "    priority <index of task> <priority level (high, medium, low)>\n";

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
        } else if (this.error.equals("priority input")) {
            output = INVALID_PRIORITY;
        } else {
            output = INVALID_COMMAND;
        }

        return output;
    }

}
