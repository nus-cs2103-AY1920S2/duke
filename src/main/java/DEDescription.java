public class DEDescription extends DukeException{
    public DEDescription(String task) {
        super.errorMsg = "☹ OOPS!!! The description of a " + task + " cannot be empty.";
    }
}
