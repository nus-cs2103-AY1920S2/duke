package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Event;

import java.util.List;

public class AddEventCommand implements Command {
    private TaskList taskList;
    private Event event;

    /**
     * Creates an AddEventCommand that adds a new Event to the tasklist.
     * @param taskList List of all the tasks saved by the user.
     * @param details List of description, time and date of event.
     */
    public AddEventCommand(TaskList taskList, List<String> details) {
        String description = details.remove(0);
        String time = details.remove(0);
        String date = details.remove(0);
        Event event = new Event(description, time, date, details.toArray(new String[details.size()]));
        this.taskList = taskList;
        this.event = event;
    }

    @Override
    public String execute() {
        taskList.add(event);
        return "Got it. I've added this event:\n"
                + event
                + "\nNow you have " + taskList.size() + " tasks on the list.";
    }
}
