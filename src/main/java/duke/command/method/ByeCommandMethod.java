package duke.command.method;

import duke.command.Command;
import duke.exception.DukeProgramTerminatedException;

public class ByeCommandMethod implements CommandMethod {
    public static final String NAME = "bye";

    public void execute(Command command)
            throws DukeProgramTerminatedException {
        throw new DukeProgramTerminatedException();
    }
}