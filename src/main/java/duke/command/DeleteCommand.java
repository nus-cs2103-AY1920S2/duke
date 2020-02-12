package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        storage.deleteFromTaskList(taskNumber);
        String text = "";
        text += "    Noted. I've removed this task:" + System.lineSeparator() + "    "
                + storage.getTaskList().get(taskNumber - 1) + System.lineSeparator()
                + "    Now you have " + storage.getTaskList().size() + " tasks in the list.";
        return text;
    }
}