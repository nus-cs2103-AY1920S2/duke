package duke.commands;

import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class DoneCommand extends Command {

    /**
     * Marks a particular task as done
     * @param taskNum Task number to be completed
     * @param tasks List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    public static String execute(String taskNum, TaskList tasks, Storage storage) {

        int taskNumber = Integer.parseInt(taskNum);
        String completed = null;
        try {
            completed = tasks.completeTask(storage, taskNumber);
        } catch (DukeException e) {
            System.out.println("Task number doesn't exist for completion");
        }
        return "This task has been marked as completed: \n" + completed;
    }
}
