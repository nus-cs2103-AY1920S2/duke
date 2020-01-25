@SuppressWarnings("serial")

public class DukeUnrecognisedTaskException extends DukeException {
    public DukeUnrecognisedTaskException(String taskType) {
        super(String.format("Unrecognised task: %s", taskType));
    }
}