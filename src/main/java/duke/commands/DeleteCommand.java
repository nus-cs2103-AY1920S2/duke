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
    public static String execute(String taskNum, TaskList tasks, Storage storage) throws DukeException {

        int taskNumber = Integer.parseInt(taskNum);
        String deleted = tasks.deleteTask(storage, taskNumber);
        return "We've deleted this task! \n" + deleted;
    }
}
