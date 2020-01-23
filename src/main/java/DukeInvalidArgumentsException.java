@SuppressWarnings("serial")

public class DukeInvalidArgumentsException extends DukeException {
    public DukeInvalidArgumentsException(String command, int expected, int received) {
        super(String.format(
                "Expected %d arguments for %s; instead received %d",
                expected, command, received
        ));
    }
}