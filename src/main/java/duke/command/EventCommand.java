package duke.command;

import duke.task.Event;
import duke.main.TaskList;

public class EventCommand {


    /**
     * Creates a Event task and store it in list of tasks created.
     * @param tasks is the list of tasks created so far
     * @param task is the Event task that needs to stored
     * @param atEvent is the time of the event
     * @return the output to inform user that the Event task has been added
     */
    public static String run(TaskList tasks, String task, String atEvent) {
        Event event = new Event(task, atEvent);

        tasks.add(event);
        return "Got it, I've added the following task:\n" + "  " + event + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
