package duke.command;

import duke.History;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents an exit command.
 * The command deals with saving the resulting list of tasks to storage.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand with isExit initialised as true.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Saves the resulting list of tasks in file and returns relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param history The History that deals with past commands.
     * @return The relevant messages in the form of String.
     */
    @Override
    public String execute(TaskList tasks, History history) {
        return Ui.generateExitMessage();
    }

}
