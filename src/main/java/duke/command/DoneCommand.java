package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    private Integer taskNumber;

    public DoneCommand(Integer taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    public String execute(Storage storage, TaskList taskList) throws IOException {
        Task task = taskList.completeTask(taskNumber);
        String message = "Nice! I've marked this task as done: "
                + System.lineSeparator()
                + task.toString();
        Ui.printMessage(message);
        storage.saveTaskList(taskList);
        return message;
    }

}
