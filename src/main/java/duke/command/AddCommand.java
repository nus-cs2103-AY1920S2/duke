package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    public String execute(Storage storage, TaskList taskList) throws IOException {
        taskList.addTask(task);
        String message = "Got it. I've added this task: "
                + System.lineSeparator()
                + task.toString()
                + System.lineSeparator()
                + System.lineSeparator()
                + "Now you have " + taskList.getSize() + " tasks in the list.";
        Ui.printMessage(message);
        storage.saveTaskList(taskList);
        return message;
    }

}
