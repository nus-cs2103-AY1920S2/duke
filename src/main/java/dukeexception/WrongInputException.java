package dukeexception;

public class WrongInputException extends DukeException {

    public String input;

    public WrongInputException(String input) {
        this.input = input;
    }

    @Override
    public String getMessage() {
        return "ERROR: Something is missing! Be specific!";
    }
}
