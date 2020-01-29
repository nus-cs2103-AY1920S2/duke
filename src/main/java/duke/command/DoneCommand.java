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
public class DoneCommand extends Command {
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
            //set the desired task as done
            tasks.setAsDone(index);

            //print success message
            ui.printMessage(String.format("     Nice! I've marked this task as done:\n"
                    + "     %s\n", tasks.getTask(index)));

            //update save file
            storage.saveTasks(tasks.getList());
        } catch (InvalidCommandException e) {
            ui.printException(e);
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }

    }
}
