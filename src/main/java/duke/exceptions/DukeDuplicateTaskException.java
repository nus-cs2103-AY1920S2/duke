package duke.exceptions;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Handles event where user tries to add a duplicate task to task list.
 */
public class DukeDuplicateTaskException extends DukeException {
    /**
     * Customises exception message according to task specified.
     * @param t task specified.
     */
    public DukeDuplicateTaskException(Task t) {
        if (t instanceof Event) {
            super.message = "DUPLICATE TASK!!\n" + t.getTaskName() + " @ "
                    + ((Event) t).getDateTime() + " is already in the task list!!";
        } else if (t instanceof Deadline) {
            super.message = "DUPLICATE TASK!!\n" + t.getTaskName() + " by "
                    + ((Deadline) t).getDateTime() + " is already in the task list!!";
        } else {
            super.message = "DUPLICATE TASK!!\n" + t.getTaskName() + " is already in the task list!!";
        }
    }
}
