package duke.exception;

@SuppressWarnings("serial")
public class DukeInvalidTaskException extends DukeException {
    public DukeInvalidTaskException(String taskIndex) {
        super(String.format(
                "%s is not a suitable task number!",
                taskIndex
        ));
    }
}

