package duke.command;

import duke.task.Deadline;
import duke.main.TaskList;

public class DeadlineCommand {


    /**
     * Creates a Deadline task and store it in list of tasks created.
     * @param tasks the list of tasks created so far
     * @param task the Deadline task that needs to stored
     * @param byDeadline the deadline of the Deadline task
     * @return the output to inform user that the task has been added
     */
    public static String run(TaskList tasks, String task, String byDeadline) {
        Deadline deadline = new Deadline(task, byDeadline);
        tasks.add(deadline);
        return "Got it, I've added the following task:\n" + "  " + deadline + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }


}
