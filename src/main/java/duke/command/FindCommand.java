package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    /**
     * Create a find command.
     *
     * @param  command  the find command
     */
    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Find tasks that contains a keyword.
     *
     * @param  tasks   the task list
     * @param   storage the storage object to save the list
     * @param   ui  the ui object to interact with user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] inputTokens = this.command.split(" ");
        String dateString = (inputTokens.length > 1) ? inputTokens[1] : "";
        TaskList filteredTasks = filterByString(tasks, dateString);
        ui.prettyPrintList(filteredTasks);

    }

    /**
     * Find tasks that contain the keyword.
     *
     * @param  tasks   the task list
     * @param   filter the keyword to filter with
     * @return  list    the filtered task list
     */
    public TaskList filterByString(TaskList tasks, String filter) {
        TaskList filteredTasks = new TaskList();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(filter)) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }
}