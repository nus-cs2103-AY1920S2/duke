package duke.command;

import duke.History;
import duke.TaskList;
import duke.ui.Ui;


/**
 * Represents a list command.
 * The command deals with listing the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Returns the messages that show the list of tasks.
     *
     * @param tasks The TaskList that contains list of tasks..
     * @param history The History that deals with past commands.
     * @return The messages that show the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, History history) {
        return Ui.generateListMessage(tasks.getTasks());
    }


}
