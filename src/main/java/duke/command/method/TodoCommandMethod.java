package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeNoArgumentsException;
import duke.storage.Storage;
import duke.task.TodoTask;

public class TodoCommandMethod implements CommandMethod {
    public static final String NAME = "todo";

    public void execute(Command command) throws DukeException {
        Duke program = Duke.getProgram();
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(TodoCommandMethod.NAME);
        }
        TodoTask newTask = new TodoTask(command.getArgumentString());
        String message = program.getTaskList().addTask(newTask);
        program.getUi().print(message);
        Storage storage = program.getStorage();
        storage.saveTaskList(program.getTaskList());
    }
}
