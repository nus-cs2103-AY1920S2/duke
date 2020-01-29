/**
 * Generates an error message for the situation where user input cant be decoded.
 */
public class DukeExceptionCommand extends DukeException {
    public DukeExceptionCommand() {
        super.errorMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
