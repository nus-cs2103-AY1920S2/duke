package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeEmptyTaskListException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidTaskException;
import duke.exception.DukeNoArgumentsException;
import duke.utils.TaskList;

public class DeleteCommandMethod implements CommandMethod {
    public static final String NAME = "delete";

    public String execute(Duke program, Command command) throws DukeException { 
        TaskList tasks = program.getTaskList();
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(command.getCommandName());
        }
        if (tasks.isEmpty()) {
            throw new DukeEmptyTaskListException();
        }
        String firstArgument = command.getArgumentList()[0];
        try {
            int taskIndex = Integer.parseInt(firstArgument) - 1;
            return tasks.removeTask(taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException(firstArgument);
        }
    }
}
