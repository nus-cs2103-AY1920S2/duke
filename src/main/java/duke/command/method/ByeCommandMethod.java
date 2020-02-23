package duke.command.method;

import duke.command.Command;
import duke.exception.DukeProgramTerminatedException;

public class ByeCommandMethod implements CommandMethod {
    public String getCommandName() {
        return "bye";
    }

    public String getFormat() {
        return getCommandName();
    }

    public String getDescription() {
        return "Quits the program.";
    }

    public void execute(Command command)
            throws DukeProgramTerminatedException {
        throw new DukeProgramTerminatedException();
    }
}