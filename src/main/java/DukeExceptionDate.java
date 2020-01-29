
/**
 * Generates an error message for the situation where a date was not given for a Deadline or Event Task.
 */
public class DukeExceptionDate extends DukeException {
    public DukeExceptionDate(String task) {
        super.errorMsg = "☹ OOPS!!! I'm sorry, but a date is required for a " + task + ".";
    }
}
