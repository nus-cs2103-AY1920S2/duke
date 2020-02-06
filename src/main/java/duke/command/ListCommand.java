package duke.command;

import duke.Controller;
import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A class representing a listing command.
 */
public class ListCommand extends Command {
    private static final String type = "list";

    /**
     * Displays the current list of tasks. The list is consistent with data stored in the record specified by the file
     * path in the current Duke instance.
     *
     * @param storageController a Storage object
     * @param storage           an ArrayList collection of Task objects for processing in-program.
     * @return false
     */
    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            String lst = "";
            for (int i = 0; i < storage.size(); i++) {
                lst += String.format("\t%d -%s\n", i + 1, storage.get(i));
            }
            Ui.printList(lst);

        } catch (Exception e) {
            Controller.raiseException(e);
        }
        return false;
    }

    @Override
    public void executeGui(Storage storageController, ArrayList<Task> storage) {
        execute(storageController, storage);
    }
}
