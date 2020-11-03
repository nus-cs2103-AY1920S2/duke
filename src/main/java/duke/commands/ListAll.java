package duke.commands;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;
import duke.parsers.CommandParser;

/**
 * Displays all Tasks in the TaskList.
 */
class ListAll extends Command {

    public ListAll(CommandParser commandParser) {
        super(commandParser);
    }

    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are currently no tasks.");
        }

        // Generate the list of tasks for the user
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (Task task : tasks.getAllTasks()) {
            sb.append(counter);
            sb.append(".");
            sb.append(task);
            sb.append("\n");
            counter += 1;
        }

        ui.showReply(sb.toString());
    }
}
