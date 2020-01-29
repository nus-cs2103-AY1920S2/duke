package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a DeadlineCommand.
 * Used to execute the deadline command.
 */
public class DeadlineCommand extends Command {
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
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //check if the argument length required is correct
            if (args.length < 2) {
                throw new InvalidCommandException("     ☹ OOPS!!!"
                        + " The date of a deadline cannot be empty.");
            }

            //add a new deadline task to the list
            Task newDeadlineTask = new Deadline(args[0], args[1]);
            tasks.addTask(newDeadlineTask);

            //print success message
            ui.printMessage(String.format("     Got it. I've added this task:\n       %s\n"
                    + "     Now you have %d tasks in the list.\n", newDeadlineTask,tasks.getSize()));

            //update save file
            storage.saveTasks(tasks.getList());
        } catch (InvalidCommandException e) {
            ui.printException(e);
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
