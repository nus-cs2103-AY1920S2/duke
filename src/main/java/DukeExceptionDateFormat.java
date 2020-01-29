
/**
 * Generates an error message for the situation where a date was not given in the yyyy-mm-dd format.
 */
public class DukeExceptionDateFormat extends DukeException {
    public DukeExceptionDateFormat() {
        super.errorMsg = "☹ OOPS!!! The date must be in the format yyyy-mm-dd";
    }
}
