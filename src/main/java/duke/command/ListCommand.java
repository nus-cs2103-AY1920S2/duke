package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    private static final String type = "list";

    public String getType() {
        return type;
    }

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            for (int i = 0; i < storage.size(); i++) {
                System.out.printf("\t%d -%s\n", i + 1, storage.get(i));
            }
        } catch (Exception e) {
            Ui.printError(e);
        }
        return false;
    }
}
