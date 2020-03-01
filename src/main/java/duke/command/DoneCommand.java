package duke.command;

import duke.exception.DukeException;
import duke.exception.InsufficientArgumentsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int index;

    /**
     * Initialises a new DoneCommand object.
     * @param cmdArgs The arguments passed into the DoneCommand object
     * @throws DukeException If an insufficient number of arguments is provided or non-integer arg is provided
     */
    public DoneCommand(String[] cmdArgs) throws DukeException {
        // TODO: add varargs (etc: done 4 2 1)
        if (!hasValidNumOfArgs(cmdArgs.length)) {
            throw new InsufficientArgumentsException("OOPS!!! DONE command expects 1 integer argument!");
        } else {
            try {
                this.index = Integer.parseInt(cmdArgs[1]) - 1;
            } catch (NumberFormatException ex) {
                throw new DukeException("OOPS!!! DONE command expects 1 integer argument!");
            }
        }
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(this.index);
        assert task != null : "Task should not be null.";
        tasks.markTaskAsDone(this.index);

        Ui.printMessage("Nice! I've marked this task as done:\n\t" + task);
        storage.storeTasks(tasks.getTasks());
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
