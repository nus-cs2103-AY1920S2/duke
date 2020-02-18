package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an exit command that extends from the command class, describing the behaviour when exiting the bot.
 */
public class ExitCommand extends Command {
    /**
     * Returns <code>true</code> if the the command is the exit command.
     * @return <code>true</code> if the the command is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command.
     * @param tasklist The list of tasks.
     * @param ui The User Interface of the chat bot.
     * @param storage The storage client of the bot that writes to the data file.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return "Hope to see you next time! xD\n";
    }
}
