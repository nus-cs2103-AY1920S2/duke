package duke.command;

import duke.ui.Ui;
import duke.task.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import java.io.IOException;

/**
 * Represents a EventCommand.
 * Used to execute the event command.
 */
public class TodoCommand extends Command {
    /** String argument for the command. */
    private String arg = null;

    /**
     * Constructs a new TodoCommand.
     *
     * @param arg argument for the todo command.
     */
    public TodoCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Executes the todo command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTodoTask = new Todo(arg);
            tasks.addTask(newTodoTask);
            ui.printMessage(String.format("     Got it. I've added this task:\n       %s\n"
                    + "     Now you have %d tasks in the list.\n", newTodoTask,tasks.getSize()));
            storage.saveTasks(tasks.getList());
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
