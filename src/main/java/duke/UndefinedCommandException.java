package duke;

public class UndefinedCommandException extends DukeException {
    @Override
    public String getErrorMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
