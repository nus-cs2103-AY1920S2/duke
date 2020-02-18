package duke.exception;

/**
 * Signals exception when there is missing information.
 */
public class DukeMissingInfoException extends DukeException {

    private String task;

    public DukeMissingInfoException(String task) {
        this.task = task;
    }

    @Override
    public String getMessage() {
        switch (task) {
        case "delete":
            return "OOPS!!! Which task should I remove?";
        case "done":
            return "OOPS!!! Please give me the task number.";
        case "todo":
            return "OOPS!!! The description of a todo cannot be empty.";
        case "event":
            return "OOPS!!! Missing information regarding event.";
        case "deadline":
            return "OOPS!!! Missing information regarding deadline.";
        case "find":
            return "OOPS!!! Please give me the keyword to look for!";
        default:
            return "OOPS!!! Insufficient information provided.";
        }
    }
}
