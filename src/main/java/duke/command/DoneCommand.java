package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    private int index = 0;

    public DoneCommand(int index) {
        this.index = index;
    }

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
