package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import duke.task.Event;

public class EventCommand extends Command {

    private String taskDescription;
    private String date;

    public EventCommand(String[] eventArgs) {
        this.taskDescription = eventArgs[0];
        this.date = eventArgs[1];
    }

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        Event newEvent = new Event(this.taskDescription, this.date);
        storage.addToTaskList(newEvent);
        String text = "";
        text += "    Got it. I've added this task:\n" + "      " + newEvent + "    Now you have "
                + storage.getTaskList().size() + " tasks in the list.";
        return text;
    }
}