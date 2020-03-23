package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    private Integer taskNumber;

    public DeleteCommand(Integer taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    public String execute(Storage storage, TaskList taskList) throws IOException {
        Task task = taskList.removeTask(taskNumber);
        String message = "Noted! I've removed this task: "
                + System.lineSeparator()
                + task.toString()
                + System.lineSeparator()
                + "Now you have " + taskList.getSize()
                + " tasks in the list.";
        Ui.printMessage(message);
        storage.saveTaskList(taskList);
        return message;
    }

}
