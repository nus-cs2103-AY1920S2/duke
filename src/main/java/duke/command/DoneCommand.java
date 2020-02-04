package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Storage;
import duke.task.TaskList;

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
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            //set the desired task as done
            tasks.setAsDone(index);

            //update save file
            storage.saveTasks(tasks.getList());

            //print success message
            return String.format("Nice! I've marked this task as done:\n"
                    + "%s\n" + "Hope we get loads of Eris for this!\n", tasks.getTask(index));
        } catch (InvalidCommandException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Sorry, I could not write to the magic saving item (Error when writing to save file).";
        }

    }
}
