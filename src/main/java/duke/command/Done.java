package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class Done extends Command {

    private int taskNumber;

    public Done(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Done() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(taskNumber).markAsDone();
        storage.save(tasks);
        return ui.showDoneMessage(tasks.getTask(taskNumber).getStatusIcon(), tasks.getTask(taskNumber));
    }

    public String getHelpFormat() {
        return "Please type done command in following format:\n" +
                "done <task_number>\n" +
                "done key word must be in lower case, spacing must be adhere" +
                "and type list command to find out task number!";
    }
}
