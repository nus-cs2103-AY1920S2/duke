package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeNoArgumentsException;
import duke.storage.Storage;
import duke.task.TodoTask;

public class TodoCommandMethod implements CommandMethod {
    public String getCommandName() {
        return "todo";
    }

    public String getFormat() {
        return getCommandName() + " <description>";
    }

    public String getDescription() {
        return "Adds a new todo task with the given description.";
    }

    public void execute(Command command) throws DukeException {
        Duke program = Duke.getProgram();
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(getCommandName());
        }
        TodoTask newTask = new TodoTask(command.getArgumentString());
        String message = program.getTaskList().addTask(newTask);
        program.getUi().print(message);
        Storage storage = program.getStorage();
        storage.saveTaskList(program.getTaskList());
    }
}
