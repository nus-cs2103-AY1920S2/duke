package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    /**
     * Returns true if the command terminates the program.
     *
     * @return true if the command terminates the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Saves the tasks in the TaskList to the data file and terminates the program with the exit message.
     *
     * @param tasks The TaskList containing the tasks.
     * @return A string with the message to be printed.
     */
    @Override
    public String execute(TaskList tasks) {
        return Ui.showExit();
    }
}
