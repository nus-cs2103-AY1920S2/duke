public class TodoException extends DukeException {
    public TodoException() {
        super();
    }

    @Override
    public String getMessage(){
        return "\u2639 OOPS!!! The description of a todo cannot be empty.";
    }
}
