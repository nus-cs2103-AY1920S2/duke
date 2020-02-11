package duke.commands;

import duke.storage.Storage;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class DeleteCommand implements Command {

    /**
     * Deletes a task from list of current tasks
     * @param taskNum Task number to be deleted
     * @param tasks List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    public static String execute(String taskNum, TaskList tasks, Storage storage) {

        int taskNumber = Integer.parseInt(taskNum);
        String deleted = null;
        try {
            deleted = tasks.deleteTask(storage, taskNumber);
        } catch (DukeException e) {
            System.out.println("Task number doesn't exist for deletion");
        }
        return "This task has been deleted: \n" + deleted;
    }
}
