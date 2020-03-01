package duke.command;

import duke.exception.DukeException;
import duke.exception.InsufficientArgumentsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String description;

    /**
     * Initialises a new TodoCommand.
     * @param cmdArgs The arguments passed into the TodoCommand object
     * @throws DukeException If an insufficient number of arguments is provided
     */
    public TodoCommand(String[] cmdArgs) throws DukeException {
        if (!hasValidNumOfArgs(cmdArgs.length)) {
            throw new InsufficientArgumentsException("OOPS!!! The description of a TODO cannot be empty!");
        } else {
            this.description = cmdArgs[1];
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.equals("")) {
            throw new InsufficientArgumentsException("☹ OOPS!!! TODO description cannot be empty!");
        } else {
            Task newTask = new Todo(description);
            assert newTask != null : "newTask should not be null.";
            tasks.addTask(newTask);
            Ui.displayAddTaskSuccessMsg(newTask, tasks.getNumberOfTasks());
            storage.storeTasks(tasks.getTasks());
        }
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
