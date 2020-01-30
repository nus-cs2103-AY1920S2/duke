package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeProgramTerminatedException;

public class ByeCommandMethod implements CommandMethod {
    public static final String NAME = "bye";

    public void execute(Duke program, Command command)
            throws DukeProgramTerminatedException {
        throw new DukeProgramTerminatedException();
    }
}