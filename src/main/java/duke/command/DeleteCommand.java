package duke.command;

import duke.exception.DukeException;
import duke.exception.InsufficientArgumentsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    /**
     * Initialises a new DeleteCommand object.
     * @param cmdArgs The arguments passed into the DeleteCommand object
     * @throws DukeException If an insufficient number of arguments is provided or non-integer argument is provided
     */
    public DeleteCommand(String[] cmdArgs) throws DukeException {
        // TODO: add varargs (etc: delete 4 2 1)
        if (!hasValidNumOfArgs(cmdArgs.length)) {
            throw new InsufficientArgumentsException("☹ OOPS!!! DELETE command expects 1 integer argument!");
        } else {
            try {
                this.index = Integer.parseInt(cmdArgs[1]) - 1;
            } catch (NumberFormatException ex) {
                throw new DukeException("OOPS!!! Please enter a Integer value!");
            }
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(this.index);
        assert task != null : "Task should not be null.";
        tasks.deleteTask(this.index);

        Ui.printMessage("Noted! I've removed this task:\n\t" + task + "\nNow you have " + tasks.getNumberOfTasks()
                        + " tasks in the list.");

        storage.storeTasks(tasks.getTasks());
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
