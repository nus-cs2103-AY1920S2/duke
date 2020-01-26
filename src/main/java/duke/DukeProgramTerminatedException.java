package duke;

@SuppressWarnings("serial")
public class DukeProgramTerminatedException extends DukeException {
    public DukeProgramTerminatedException() {
        super("Program was terminated");
    }
}