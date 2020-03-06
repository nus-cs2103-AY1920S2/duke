package duke.command;

import duke.exceptions.Exceptions;
import duke.exceptions.InvalidIndexError;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * The DoneCommand program calls method to mark task as done.
 *
 * @version 1.1
 * @since 3/2/2020
 */
public class DoneCommand extends Command {

    /**
     * Constructor.
     *
     * @param storage  refers to file storage.
     * @param taskList refers a taskList object that manages the current list of tasks.
     */
    public DoneCommand(Storage storage, TaskList taskList) {

        super(storage, taskList);

    }

    /**
     * Calls method to mark the task as done.
     *
     * @param taskDescriptionArr is a parsed task description which
     *                           contains information such as date
     *                           of task.
     */
    @Override
    public String executeCommand(String[] taskDescriptionArr) {

        try {

            if (taskList.getList().size() < Integer.parseInt(taskDescriptionArr[1])
                    || Integer.parseInt(taskDescriptionArr[1]) <= 0) {

                throw new InvalidIndexError(taskList.getList().size());
            }

            Task t = taskList.getTask(Integer.parseInt(taskDescriptionArr[1]));

            if (t.getStatus().equals(Task.Status.Y)) {

                return "Task is already done!";
            }

            return "Nice! I've marked this task as done:\n" + taskList.markDone(t);

        } catch (Exception e) {

            if (e instanceof Exceptions) {

                return e.toString();

            } else {

                return "Error encountered when marking event done. Please try again!";

            }

        }

    }
}
