/**
 * Generates an error message for the situation where the description of the Task was not given.
 */
public class DEDescription extends DukeException{
    public DEDescription(String task) {
        super.errorMsg = "☹ OOPS!!! The description of a " + task + " cannot be empty.";
    }
}
