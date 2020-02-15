package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a DoneCommand.
 * Used to execute the done command.
 */
public class DoneCommand implements Command {
    private static final String SAVE_WRITE_ERROR_MESSAGE = "Sorry, I could not write to the magic saving item"
            + " (Error when writing to save file).\n";

    /** Index of the task to be set as done. */
    private int index = 0;

    /**
     * Constructs a new DoneCommand.
     *
     * @param index index of the task to set as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the done command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.setAsDone(index);
            storage.saveTasks(tasks.getList());
            ui.addMessage(String.format("Nice! I've marked this task as done:\n"
                    + "  %s\n" + "Hope we get loads of Eris for this!\n", tasks.getTask(index)));
        } catch (InvalidCommandException e) {
            ui.addMessage(e.getMessage());
        } catch (IOException e) {
            ui.addMessage(SAVE_WRITE_ERROR_MESSAGE);
        }

    }
}
