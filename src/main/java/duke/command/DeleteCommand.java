package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        Task taskToDelete = storage.getTaskList().get(taskNumber - 1);
        storage.deleteFromTaskList(taskNumber);

        String text = "";
        text += "    Noted. I've removed this task:" + System.lineSeparator() + "     "
                + taskToDelete + System.lineSeparator()
                + "    Now you have " + storage.getTaskList().size() + " tasks in the list.";
        return text;
    }
}