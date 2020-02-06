package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A class representing an exit command.
 */
public class ExitCommand extends Command {
    private static final String type = "exit";

    /**
     * Exits the program by returning a true value that will be propagated upward.
     *
     * @param storageController a Storage object
     * @param storage           an ArrayList collection of Task objects for processing in-program.
     * @return true
     */
    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        Ui.goodbye();
        return true;
    }

    @Override
    public void executeGui(Storage storageController, ArrayList<Task> storage) {
        System.out.println("asdas");
        Ui.goodbye();
    }
}
