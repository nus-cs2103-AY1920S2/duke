package duke.command;

import duke.ui.Ui;
import duke.task.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.exception.InvalidCommandException;
import java.io.IOException;

/**
 * Represents a DeadlineCommand.
 * Used to execute the deadline command.
 */
public class DeadlineCommand extends Command {
    /** String array for the command arguments. */
    private String[] args = null;

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
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (args.length < 2) {
                throw new InvalidCommandException("     ☹ OOPS!!!"
                        + " The date of a deadline cannot be empty.");
            }
            Task newDeadlineTask = new Deadline(args[0], args[1]);
            tasks.addTask(newDeadlineTask);
            ui.printMessage(String.format("     Got it. I've added this task:\n       %s\n"
                    + "     Now you have %d tasks in the list.\n", newDeadlineTask,tasks.getSize()));
            storage.saveTasks(tasks.getList());
        } catch (InvalidCommandException e) {
            ui.printException(e);
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
