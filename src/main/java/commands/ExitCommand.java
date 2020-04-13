package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    /**
     * Executes the exit command.
     *
     * @param tasks is task list for in-memory.
     * @param ui is ui to display to user.
     * @param storage is file where data is written to and read from.
     * @return exit message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getExitMessage();
    }
}
