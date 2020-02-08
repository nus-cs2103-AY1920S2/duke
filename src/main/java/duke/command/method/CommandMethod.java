package duke.command.method;

import duke.command.Command;
import duke.exception.DukeException;

public interface CommandMethod {
    void execute(Command command) throws DukeException;
    String getFormat();
    String getDescription();
}