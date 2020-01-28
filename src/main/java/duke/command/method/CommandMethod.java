package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;

public interface CommandMethod {
    void execute(Duke program, Command command) throws DukeException;
}