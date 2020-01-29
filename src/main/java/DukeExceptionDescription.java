public class DukeExceptionDescription extends DukeException {
    public DukeExceptionDescription(String task) {
        super.errorMsg = "☹ OOPS!!! The description of a " + task + " cannot be empty.";
    }
}
