package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;


/**
 * Represents a list command issued by the user.
 */
public class ListCommand extends Command {
    /**
     * Executes the command.
     *
     * @param storage Storage class is not used.
     * @param tasks TaskList class for the command to retrieve the tasks.
     * @return Response containing all the tasks currently in the task list.
     * @throws DukeException Not thrown.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        String msg = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            msg += i + 1 + "." + tasks.get(i) + "\n";
        }
        return msg;
    }
}
