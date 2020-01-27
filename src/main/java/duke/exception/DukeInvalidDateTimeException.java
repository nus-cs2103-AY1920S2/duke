package duke.exception;

@SuppressWarnings("serial")
public class DukeInvalidDateTimeException extends DukeException {
    public DukeInvalidDateTimeException(String dateTime) {
        super(String.format(
                "'%s' is not a valid date-time (valid syntax: '31/05/2019 2359')",
                dateTime
        ));
    }
}