package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.io.IOException;

/**
 * The DeleteCommand program calls method to delete task from taskList and storage.
 *
 * @version 1.1
 * @since 3/2/2020
 */
public class DeleteCommand extends Command {

    /**
     * Constructor.
     *
     * @param storage  refers to file storage.
     * @param taskList refers a taskList object that manages the current list of tasks.
     */
    public DeleteCommand(Storage storage, TaskList taskList) {

        super(storage, taskList);
    }

    /**
     * Calls method to delete the task from taskList.
     *
     * @param taskDescriptionArr is a parsed task description which
     *                           contains information such as date
     *                           of task.
     */
    @Override
    public String executeCommand(String[] taskDescriptionArr) {

        try {

           /* System.out.println(HEADER);
            System.out.println(taskList.deleteTask(Integer.parseInt(taskDescriptionArr[1])));
            System.out.println(taskList.reportTotal());
            System.out.println();
            */

            return taskList.deleteTask(Integer.parseInt(taskDescriptionArr[1]))
                    + taskList.reportTotal();


        } catch (IOException e) {

           return e.toString();

        }
    }
}
