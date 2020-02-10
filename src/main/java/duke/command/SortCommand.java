package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

/**
 * Represents a sort command issued by the user.
 */
public class SortCommand extends Command {
    /**
     * Executes the command.
     *
     * @param storage Storage class for the command to write data.
     * @param tasks TaskList class for the command to get the sorted list.
     * @return Response containing the sorted task list.
     * @throws DukeException Thrown when there is an error writing file.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        ArrayList<Task> sortedTasks = tasks.sort();
        String msg = "The tasks have been sorted alphabetically.\nHere is the new list:\n";
        for (int i = 0; i < sortedTasks.size(); i++) {
            msg += i + 1 + "." + sortedTasks.get(i) + "\n";
        }
        storage.writeToFile(sortedTasks);
        return msg;
    }
}
