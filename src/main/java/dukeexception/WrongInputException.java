package dukeexception;

public class WrongInputException extends DukeException {

    @Override
    public String getMessage() {
        return "ERROR: Something is missing! Be specific!";
    }
}
