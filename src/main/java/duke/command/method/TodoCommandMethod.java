package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeNoArgumentsException;
import duke.task.TodoTask;

public class TodoCommandMethod implements CommandMethod {
    public static final String NAME = "todo";

    public String execute(Duke program, Command command) throws DukeException { 
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(TodoCommandMethod.NAME);
        }
        TodoTask newTask = new TodoTask(command.getArgumentString());
        return program.getTaskList().addTask(newTask);
    }
}
