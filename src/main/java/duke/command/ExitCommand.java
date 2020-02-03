package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ExitCommand extends Command {
    private static final String type = "exit";

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        Ui.goodbye();
        return true;
    }
}
