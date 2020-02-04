package duke.command;

import com.sun.net.httpserver.Headers;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.util.ArrayList;

/**
 * The FindCommand calls method to find tasks with relevant keyword.
 *
 * @version 1.1
 * @since 3/2/2020
 */
public class FindCommand extends Command {

    /**
     * Constructor.
     *
     * @param storage  refers to file storage.
     * @param taskList refers a taskList object that manages the current list of tasks.
     */
    public FindCommand(Storage storage, TaskList taskList) {

        super(storage, taskList);

    }

    /**
     * Calls method to find tasks with keyword.
     *
     * @param taskDescriptionArr is a parsed task description which
     *                           contains information such as date
     *                           of task.
     */
    @Override
    public String executeCommand(String[] taskDescriptionArr) {

     return taskList.findTask(taskDescriptionArr[1]);
    }
}
