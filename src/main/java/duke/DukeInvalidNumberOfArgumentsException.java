package duke;

@SuppressWarnings("serial")
public class DukeInvalidNumberOfArgumentsException extends DukeException {
    public DukeInvalidNumberOfArgumentsException(String command, int expected, int received) {
        super(String.format(
                "Expected %d arguments for %s; instead received %d",
                expected, command, received
        ));
    }
}