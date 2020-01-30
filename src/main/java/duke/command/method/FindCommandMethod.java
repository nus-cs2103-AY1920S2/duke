package duke.command.method;

import java.util.ListIterator;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeEmptyTaskListException;
import duke.exception.DukeException;
import duke.exception.DukeNoArgumentsException;
import duke.task.Task;
import duke.utils.TaskList;

public class FindCommandMethod implements CommandMethod {
    public static final String NAME = "find";

    public void execute(Duke program, Command command) throws DukeException {
        TaskList tasks = program.getTaskList();
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(command.getCommandName());
        }
        if (tasks.isEmpty()) {
            throw new DukeEmptyTaskListException();
        }
        String query = command.getArgumentString();
        TaskList results = new TaskList();
        ListIterator<Task> iterator = tasks.listIterator();
        while (iterator.hasNext()) {
            Task t = iterator.next();
            if (t.getDescription().contains(query)) {
                results.addTask(t);
            }
        }
        program.getUi().print(String.format("Tasks with '%s':", query));
        program.getUi().print(results.toString());
    }
}
