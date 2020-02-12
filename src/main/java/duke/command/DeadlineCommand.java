package duke.command;

import duke.task.Deadline;
import duke.main.TaskList;

public class DeadlineCommand {

    public static String run(TaskList tasks, String task, String byDeadline) {
        Deadline deadline = new Deadline(task, byDeadline);
        tasks.add(deadline);
        return "Got it, I've added the following task:\n" + "  " + deadline + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }


}
