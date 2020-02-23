package duke.commands;

import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class DoneCommand implements Command {

    /**
     * Marks a particular task as done
     * @param taskNum Task number to be completed
     * @param tasks List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    public static String execute(String taskNum, TaskList tasks, Storage storage) throws DukeException {

        int taskNumber = Integer.parseInt(taskNum);
        String completed = tasks.completeTask(storage, taskNumber);
        return "We've completed this task! \n" + completed;
    }
}
