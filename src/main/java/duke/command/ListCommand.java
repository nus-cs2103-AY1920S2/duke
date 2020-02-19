package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * The ListCommand calls method to list task in taskList.
 *
 * @version 1.1
 * @since 3/2/2020
 */
public class ListCommand extends Command {

    /**
     * Constructor.
     *
     * @param storage  refers to file storage.
     * @param taskList refers a taskList object that manages the current list of tasks.
     */
    public ListCommand(Storage storage, TaskList taskList) {

        super(storage, taskList);

    }

    /**
     * Calls method to list tasks in taskList.
     *
     * @param taskDescriptionArr is a parsed task description which
     *                           contains information such as date
     *                           of task.
     */
    @Override
    public String executeCommand(String[] taskDescriptionArr) {

        if (taskList.getList().size() == 0) {

            return "The task list is empty.";
        }

        return taskList.list();

    }
}
