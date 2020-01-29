package duke.command;

import duke.ui.Ui;
import duke.task.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Event;
import duke.exception.InvalidCommandException;
import java.io.IOException;

/**
 * Represents a EventCommand.
 * Used to execute the event command.
 */
public class EventCommand extends Command {
    /** String array for the command arguments. */
    private String[] args = null;

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
            if (args.length < 2) {
                throw new InvalidCommandException("     ☹ OOPS!!! The date of a "
                        + "event cannot be empty.");
            }
            Task newEventTask = new Event(args[0], args[1]);
            tasks.addTask(newEventTask);
            ui.printMessage(String.format("     Got it. I've added this task:\n       %s\n"
                    + "     Now you have %d tasks in the list.\n", newEventTask,tasks.getSize()));
            storage.saveTasks(tasks.getList());
        } catch (InvalidCommandException e) {
            ui.printException(e);
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
