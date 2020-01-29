package duke.command;

import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {
    private String arg = "";

    public TodoCommand(String arg) {
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //add a new todo task to the list
            Task newTodoTask = new Todo(arg);
            tasks.addTask(newTodoTask);

            //print success message
            ui.printMessage(String.format("     Got it. I've added this task:\n       %s\n"
                    + "     Now you have %d tasks in the list.\n", newTodoTask,tasks.getSize()));

            //update save file
            storage.saveTasks(tasks.getList());
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
