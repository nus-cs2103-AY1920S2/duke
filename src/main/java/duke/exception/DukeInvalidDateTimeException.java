package duke.exception;

import duke.task.Task;

@SuppressWarnings("serial")
public class DukeInvalidDateTimeException extends DukeException {
    public DukeInvalidDateTimeException(String dateTime) {
        super(String.format(
                "'%s' is not in %s format!",
                dateTime, Task.DATE_TIME_INPUT_PATTERN));
    }
}