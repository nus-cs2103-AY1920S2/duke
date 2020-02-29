public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    @Override
    public String getMessage(){
        return "QUACK!!! I'm sorry, but I don't understand :-(";
    }
}
