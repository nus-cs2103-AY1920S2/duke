package duke;// Illegal argument exceptions for duke.task.Event, duke.task.Deadline , To-do

import duke.task.Task;

/**
 * The type Duke exception.
 */
public class DukeException extends Exception {

    /**
     * Instantiates a new Duke exception.
     *
     * @param errorMessage the error message
     * @param task_type    the task type
     */
// Exception when task type is empty. Eg: when deadline is typed
    // But there is no task at hand.
    DukeException(String errorMessage, String task_type) {
        super("OOPS!! The description of a " + Task.Task_Type.valueOf(task_type) + " cannot be empty" + errorMessage);
    }

    /**
     * Instantiates a new Duke exception.
     *
     * @param errorMessage the error message
     */
    public DukeException(String errorMessage) {
        super("OOPS!! " + errorMessage);
    }


}
