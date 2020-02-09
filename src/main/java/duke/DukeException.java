package duke;

public class DukeException extends Exception {
//    public duke.DukeException(String errorMessage) {
//        super(errorMessage);
//    }
    public String getErrorMessage() {
        return "vanilla duke exception. Extend me!";
    }
}
