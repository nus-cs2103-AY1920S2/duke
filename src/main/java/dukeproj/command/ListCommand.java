package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;

/**
 * Represents a command to list all tasks in Duke.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to list out all available tasks in the task list in Duke.
     *
     * @param ui The user interface of Duke, used to return Duke's response.
     * @param taskList The list of tasks to list from.
     * @param storage Unused.
     * @param schedule Unused.
     * @return Duke's response in the form of a String.
     */
    public String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule) {
        return ui.say(SayType.LIST) + "\n" + taskList.toString();
    }
}
