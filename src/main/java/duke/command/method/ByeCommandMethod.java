package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeProgramTerminatedException;

public class ByeCommandMethod implements CommandMethod {
    public static final String NAME = "bye";

    public String execute(Duke program, Command command) throws DukeException { 
        throw new DukeProgramTerminatedException();
    }
}