package dukeexception;

public class LoadException extends DukeException {

    @Override
    public String getMessage() {
        return "----LOADING ERROR----";
    }
}
