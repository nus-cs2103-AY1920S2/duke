public class DeadlineException extends DukeException {
    public DeadlineException() {
        super();
    }

    @Override
    public String getMessage(){
        return "\u2639 OOPS!!! The description of a deadline cannot be empty.";
    }
}
