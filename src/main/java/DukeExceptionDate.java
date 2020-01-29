public class DukeExceptionDate extends DukeException {
    public DukeExceptionDate(String task) {
        super.errorMsg = "☹ OOPS!!! I'm sorry, but a date is required for a " + task + ".";
    }
}
