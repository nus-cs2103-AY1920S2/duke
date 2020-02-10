package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

/**
 * Represents a find command issued by the user.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs the find command.
     *
     * @param input User's input to specify the search keyword.
     * @throws DukeException Thrown when user did not specify a keyword.
     */
    public FindCommand(String input) throws DukeException {
        try {
            String[] inputs = input.trim().split(" ", 2);
            keyword = inputs[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The keyword for find cannot be empty.");
        }
    }

    /**
     * Executes the command.
     *
     * @param storage Storage class is not used.
     * @param tasks TaskList class for the command to retrieve the tasks.
     * @return Response containing all the matching tasks.
     * @throws DukeException Not thrown.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        ArrayList<Task> result = tasks.find(keyword);
        String msg;
        if (result.size() == 0) {
            msg = "No match found.";
        } else {
            msg = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < result.size(); i++) {
                msg += i + 1 + "." + result.get(i) + "\n";
            }
        }
        return msg;
    }
}
