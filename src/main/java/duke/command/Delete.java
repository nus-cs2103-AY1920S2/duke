package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class Delete extends Command {

    private int taskNumber;

    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Delete() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        Task deletedTask = tasks.deleteTask(taskNumber);
        storage.save(tasks);
        return ui.showDeleteMessage(deletedTask, tasks.getSize());
    }

    public String getHelpFormat() {
        return "Please type delete command in following format:\n" +
                "delete <task_number>\n" +
                "delete key word must be in lower case, spacing must be adhered " +
                "and type list command to find out task number!";
    }
}
