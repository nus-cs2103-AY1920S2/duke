package duke.command;

import duke.task.Event;
import duke.main.TaskList;

public class EventCommand {

    public static String run(TaskList tasks, String task, String atEvent) {
        Event event = new Event(task, atEvent);

        tasks.add(event);
        return "Got it, I've added the following task:\n" + "  " + event + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
