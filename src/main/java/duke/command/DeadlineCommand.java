package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import duke.task.Deadline;

public class DeadlineCommand extends Command {

    private String taskDescription;
    private String by;

    public DeadlineCommand(String[] eventArgs) {
        this.taskDescription = eventArgs[0];
        this.by = eventArgs[1];
    }

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        Deadline newDeadline = new Deadline(this.taskDescription, this.by);
        storage.addToTaskList(newDeadline);
        String text = "";
        text += "    Got it. I've added this task:\n" + "      " + newDeadline + "    Now you have "
                + storage.getTaskList().size() + " tasks in the list.";
        return text;
    }
}