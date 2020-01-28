package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/** Represents an exit command */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Updates task list in save file.
     *
     * @param tasks list of tasks
     * @param ui prints information to user
     * @param storage manages user save file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // User request for exit
        storage.updateSaveFile(tasks);
    }
}
