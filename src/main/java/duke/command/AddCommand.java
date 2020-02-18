package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an add command that extends from the command class, describing the behaviour of adding a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an add command.
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns <code>true</code> if the the command is the exit command.
     * @return <code>true</code> if the the command is the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the add command.
     * @param tasklist The list of tasks.
     * @param ui The User Interface of the chat bot.
     * @param storage The storage client of the bot that writes to the data file.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return tasklist.addTask(task, storage);
    }
}
