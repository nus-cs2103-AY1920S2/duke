package duke;

import duke.task.Task;

/**
 * The type Duke exception
 * Illegal argument exceptions for duke.task.Event, duke.task.Deadline , To-do.
 */
public class DukeException extends Exception {

    /**
     * Instantiates a new Duke exception.
     *
     * @param errorMessage the error message
     * @param taskType    the task type
     */
    // Exception when task type is empty. Eg: when deadline is typed
    // But there is no task at hand.
    DukeException(String errorMessage, String taskType) {
        super("OOPS!! The description of a " + Task.TaskType.valueOf(taskType) + " cannot be empty" + errorMessage);
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
