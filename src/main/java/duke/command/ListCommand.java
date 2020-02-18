package duke.command;

import duke.logic.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * A command to list out the tasks stored in Duke.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the tasks in your list:\n";
        int counter = 1;
        for (Task t : tasks.getTasks()) {
            response += (counter + "."); // Index of task
            response += (" " + t + '\n'); // Description of task and whether it is done
            counter++;
        }
        response = response.substring(0, response.length() - 1);
        // Last character of the response string shouldn't be a newline character
        assert response.charAt(response.length() - 1) != '\n';
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
