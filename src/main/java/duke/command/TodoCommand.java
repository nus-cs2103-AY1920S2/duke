package duke.command;

import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a EventCommand.
 * Used to execute the event command.
 */
public class TodoCommand implements Command {
    private static final String SAVE_WRITE_ERROR_MESSAGE = "Sorry, I could not write to the magic saving item"
            + " (Error when writing to save file).\n";

    /** String argument for the command. */
    private String arg = "";

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
            storage.saveTasks(tasks.getList());
            ui.addMessage(String.format("Got it. I've added this task:\n  %s\n"
                    + "Now you have %d tasks in the list.\n", newTodoTask, tasks.getSize()));
        } catch (IOException e) {
            ui.addMessage(SAVE_WRITE_ERROR_MESSAGE);
        }
    }
}
