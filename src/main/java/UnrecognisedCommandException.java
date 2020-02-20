public class UnrecognisedCommandException extends DukeException {
    public UnrecognisedCommandException() {
        super();
    }

    @Override
    public String getMessage(){
        return "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
