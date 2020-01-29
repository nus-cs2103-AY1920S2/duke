package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index = 0;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //delete the desired task
            Task taskToDelete = tasks.deleteTask(index);

            //print success message
            ui.printMessage(String.format("     Noted. I've removed this task:\n         %s\n"
                    + "     Now you have %d tasks in the list.\n", taskToDelete, tasks.getSize()));

            //update save file
            storage.saveTasks(tasks.getList());
        } catch (InvalidCommandException e) {
            ui.printException(e);
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
