public class EventException extends DukeException {
    public EventException() {
        super();
    }

    @Override
    public String getMessage(){
        return "\u2639 OOPS!!! The description of an event cannot be empty.";
    }
}
