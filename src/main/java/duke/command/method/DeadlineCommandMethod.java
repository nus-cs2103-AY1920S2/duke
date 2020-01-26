package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeInvalidNumberOfArgumentsException;
import duke.exception.DukeNoArgumentsException;
import duke.task.DeadlineTask;

public class DeadlineCommandMethod implements CommandMethod {
    public static final String NAME = "deadline";

    public String execute(Duke program, Command command) throws DukeException { 
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(DeadlineCommandMethod.NAME);
        }
        String[] parts = command.getArgumentString().split(" /by ", 2);
        if (parts.length != 2) {
            throw new DukeInvalidNumberOfArgumentsException(
                    DeadlineCommandMethod.NAME,2, parts.length
            );
        }
        DeadlineTask newTask = new DeadlineTask(parts[0], parts[1]);
        return program.getTaskList().addTask(newTask);
    }
}
