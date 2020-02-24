package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Defines an abstract class that specifies the behaviours of the command.
 */
public abstract class Command {
    /**
     * Returns <code>true</code> if the the command is the exit command.
     * @return <code>true</code> if the command is the exit command.
     */
    public abstract boolean isExitCommand();

    /**
     * Executes the command.
     * @param tasklist The list of tasks.
     * @param ui The User Interface of the chat bot.
     * @param storage The storage client of the bot that writes to the data file.
     */
    public abstract String execute(TaskList tasklist, Ui ui, Storage storage);
}
