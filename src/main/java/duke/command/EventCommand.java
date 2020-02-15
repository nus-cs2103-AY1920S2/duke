package duke.command;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidTimeFormatException;
import duke.task.Event;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a EventCommand.
 * Used to execute the event command.
 */
public class EventCommand implements Command {
    private static final String NO_DATE_ERROR_MESSAGE = "HEY!!! The date of an event cannot be empty.\n";
    private static final String SAVE_WRITE_ERROR_MESSAGE = "Sorry, I could not write to the magic saving item"
            + " (Error when writing to save file).\n";

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
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //check if arg length is complete for the command to execute
            if (args.length < 2) {
                throw new InvalidCommandException(NO_DATE_ERROR_MESSAGE);
            }

            Task newEventTask = new Event(args[0], args[1]);
            tasks.addTask(newEventTask);
            storage.saveTasks(tasks.getList());
            ui.addMessage(String.format("Got it. I've added this task:\n  %s\n"
                    + "Now you have %d tasks in the list.\n", newEventTask, tasks.getSize()));
        } catch (InvalidCommandException e) {
            ui.addMessage(e.getMessage());
        } catch (InvalidDateFormatException e) {
            ui.addMessage(e.getMessage());
        } catch (InvalidTimeFormatException e) {
            ui.addMessage(e.getMessage());
        } catch (IOException e) {
            ui.addMessage(SAVE_WRITE_ERROR_MESSAGE);
        }
    }
}
