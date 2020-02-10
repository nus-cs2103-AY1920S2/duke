package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a DeadlineCommand.
 * Used to execute the deadline command.
 */
public class DeadlineCommand extends Command {
    private static final String NO_DATE_ERROR_MESSAGE = "HEY!!! The date of a deadline cannot be empty.";
    private static final String SAVE_WRITE_ERROR_MESSAGE = "Sorry, I could not write to the magic saving item"
            + " (Error when writing to save file).";

    /** String array for the command arguments. */
    private String[] args = new String[0];

    /**
     * Constructs a new DeadlineCommand.
     *
     * @param args arguments for the deadline command.
     */
    public DeadlineCommand(String[] args) {
        this.args = args;
    }

    /**
     * Executes the deadline command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            //check if arg length is complete for the command to execute
            if (args.length < 2) {
                throw new InvalidCommandException(NO_DATE_ERROR_MESSAGE);
            }

            Task newDeadlineTask = new Deadline(args[0], args[1]);
            tasks.addTask(newDeadlineTask);
            storage.saveTasks(tasks.getList());
            return String.format("Got it. I've added this task:\n  %s\n"
                    + "Now you have %d tasks in the list.\n", newDeadlineTask, tasks.getSize());
        } catch (InvalidCommandException e) {
            return e.getMessage();
        } catch (IOException e) {
            return SAVE_WRITE_ERROR_MESSAGE;
        }
    }
}
