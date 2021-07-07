public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super();
    }

    @Override
    public String getMessage(){
        return "QUACK!!! The description cannot be empty, master :-(";
    }
}
