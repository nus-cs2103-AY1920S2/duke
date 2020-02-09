package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Event;

import java.util.List;

public class AddEventCommand implements Command{
    private TaskList taskList;
    private Event event;

    public AddEventCommand(TaskList taskList, List<String> details) {
        Event event = new Event(details.get(0), details.get(1), details.get(2));
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
