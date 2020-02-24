package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a delete command that extends from the command class, describing the behaviour of deleting a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a delete command.
     * @param index The index number of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns <code>true</code> if the the command is the exit command.
     * @return <code>true</code> if the the command is the exit command.
     */
    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Executes the delete command.
     * @param tasklist The list of tasks.
     * @param ui The User Interface of the chat bot.
     * @param storage The storage client of the bot that writes to the data file.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return tasklist.deleteTask(index, storage);
    }
}
