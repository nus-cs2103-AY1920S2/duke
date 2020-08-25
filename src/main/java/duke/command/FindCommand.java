package duke.command;

import duke.Controller;
import duke.Storage;
import duke.Ui;
import duke.state.StateController;
import duke.task.Task;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class representing a find command.
 */
public class FindCommand extends Command {
    private static final String type = "find";
    private String keyword;

    /**
     * Constructs the FindCommand object with a specified keyword.
     *
     * @param keyword a String value for the keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays the list of tasks that contain the query keyword. The list is consistent with data stored in the record
     * specified by the file path in the current Duke instance.
     *
     * @param storageController a Storage object
     * @param storage           an ArrayList collection of Task objects for processing in-program.
     * @return false
     */
    @Override
    public boolean execute(StateController stateController, Storage storageController, ArrayList<Task> storage) {
        try {
            AtomicInteger index = new AtomicInteger(1);
            StringBuilder result = new StringBuilder();
            int count = 0;
            for (Task task : storage) {
                if (task.getDescription().toLowerCase().contains(this.keyword.toLowerCase())) {
                    count += 1;
                    result.append(String.format("\t%d -%s\n", count, task.toString()));
                }
            }
            Ui.printFind(keyword, result.toString(), count);

        } catch (Exception e) {
            Controller.raiseException(e);
        }
        return false;
    }
}
