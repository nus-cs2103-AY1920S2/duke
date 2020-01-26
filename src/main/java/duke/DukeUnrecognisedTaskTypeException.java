package duke;

@SuppressWarnings("serial")
public class DukeUnrecognisedTaskTypeException extends DukeException {
    public DukeUnrecognisedTaskTypeException(String taskType) {
        super(String.format("Unrecognised task: %s", taskType));
    }
}