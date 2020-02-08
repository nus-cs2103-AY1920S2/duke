package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeEmptyTaskListException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidTaskException;
import duke.exception.DukeNoArgumentsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.utils.TaskList;

public class DoneCommandMethod implements CommandMethod {
    public String getCommandName() {
        return "done";
    }

    public String getFormat() {
        return getCommandName() + " <index>";
    }

    public String getDescription() {
        return "Marks the task at the given index as done.";
    }

    public void execute(Command command) throws DukeException {
        Duke program = Duke.getProgram();
        TaskList tasks = program.getTaskList();
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(getCommandName());
        }
        if (tasks.isEmpty()) {
            throw new DukeEmptyTaskListException();
        }
        String firstArgument = command.getArgumentList()[0];
        try {
            int taskIndex = Integer.parseInt(firstArgument) - 1;
            Task task = tasks.getTask(taskIndex);
            String message = task.markAsCompleted();
            program.getUi().print(message);
            Storage storage = program.getStorage();
            storage.saveTaskList(program.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException(firstArgument);
        }
    }
}
