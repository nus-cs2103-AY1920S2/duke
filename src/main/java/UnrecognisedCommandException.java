public class UnrecognisedCommandException extends DukeException {
    public UnrecognisedCommandException() {
        super();
    }

    @Override
    public String getMessage(){
        return "QUACK!!! I don't understand what you just said, master :-(";
    }
}
