package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeInvalidNumberOfArgumentsException;
import duke.exception.DukeNoArgumentsException;
import duke.task.EventTask;

public class EventCommandMethod implements CommandMethod {
    public static final String NAME = "event";

    public String execute(Duke program, Command command) throws DukeException { 
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(EventCommandMethod.NAME);
        }
        String[] parts = command.getArgumentString().split(" /at ", 2);
        if (parts.length != 2) {
            throw new DukeInvalidNumberOfArgumentsException(
                    EventCommandMethod.NAME, 2, parts.length
            );
        }
        EventTask newTask = new EventTask(parts[0], parts[1]);
        return program.getTaskList().addTask(newTask);
    }
}
