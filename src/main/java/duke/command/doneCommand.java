package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;

public class doneCommand extends Command {

    private int taskNumber;

    public doneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        storage.markTaskAsDone(this.taskNumber);
        return "Nice! I've marked this task as done:" + System.lineSeparator()
                + "    " + storage.getTaskList().get(taskNumber - 1);
    }
}