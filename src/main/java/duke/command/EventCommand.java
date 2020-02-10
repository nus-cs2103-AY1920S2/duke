package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Event;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a EventCommand.
 * Used to execute the event command.
 */
public class EventCommand extends Command {
    private static final String NO_DATE_ERROR_MESSAGE = "HEY!!! The date of an event cannot be empty.";
    private static final String SAVE_WRITE_ERROR_MESSAGE = "Sorry, I could not write to the magic saving item"
            + " (Error when writing to save file).";

    /** String array for the command arguments. */
    private String[] args = new String[0];

    /**
     * Constructs a new EventCommand.
     *
     * @param args arguments for the event command.
     */
    public EventCommand(String[] args) {
        this.args = args;
    }

    /**
     * Executes the event command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            //check if the argument length required is correct
            if (args.length < 2) {
                throw new InvalidCommandException(NO_DATE_ERROR_MESSAGE);
            }

            //add a new event task to the list
            Task newEventTask = new Event(args[0], args[1]);
            tasks.addTask(newEventTask);

            //update save file
            storage.saveTasks(tasks.getList());

            //print success message
            return String.format("Got it. I've added this task:\n  %s\n"
                    + "Now you have %d tasks in the list.\n", newEventTask, tasks.getSize());
        } catch (InvalidCommandException e) {
            return e.getMessage();
        } catch (IOException e) {
            return SAVE_WRITE_ERROR_MESSAGE;
        }
    }
}
