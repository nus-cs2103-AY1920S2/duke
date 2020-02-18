package duke;

public class EmptyToDoException extends DukeException {
    public String getErrorMessage() {
        return "OOPS!!! The description of a todo cannot be empty.!";
    }
}
