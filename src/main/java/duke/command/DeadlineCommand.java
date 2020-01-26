package duke.command;

import duke.ui.Ui;
import duke.task.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.exception.InvalidCommandException;
import java.io.IOException;

public class DeadlineCommand extends Command {
    private String[] args = null;

    public DeadlineCommand(String[] args) {
        this.args = args;
    }

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
