public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super();
    }

    @Override
    public String getMessage(){
        return "\u2639 OOPS!!! The description cannot be empty.";
    }
}
