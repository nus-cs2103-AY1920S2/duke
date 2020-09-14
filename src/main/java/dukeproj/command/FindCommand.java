package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;
import dukeproj.exception.DukeDescriptionException;

/**
 * Represents a find command to find tasks with certain keywords in the task list.
 */
public class FindCommand extends Command {
    /** Keywords used to find tasks. */
    private String keyword;

    /**
     * Executes the find command to find tasks using the keywords in the description.
     *
     * @param ui The user interface of Duke, used to return Duke's response.
     * @param taskList The list of tasks to find the tasks from.
     * @param storage Unused.
     * @param schedule Unused.
     * @return Duke's response in the form of a String.
     * @throws DukeDescriptionException If the description is empty.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule)
            throws DukeDescriptionException {
        if (keyword.isEmpty()) {
            throw new DukeDescriptionException("Empty Description");
        }
        //TaskList outputList = new TaskList(taskList.find(keyword.split(" ")));
        return ui.say(SayType.FIND) + "\n" + taskList.find(keyword.split(" "));
    }

    /**
     * Constructs a find command object.
     *
     * @param keyword The keyword used to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
}
