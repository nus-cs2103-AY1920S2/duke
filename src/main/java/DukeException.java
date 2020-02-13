public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    @Override
    public String getMessage(){
        return "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
