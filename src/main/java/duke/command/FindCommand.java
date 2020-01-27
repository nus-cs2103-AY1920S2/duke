package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] inputTokens = this.command.split(" ");
        String dateString = (inputTokens.length > 1) ? inputTokens[1] : "";
        TaskList filteredTasks = filterByString(tasks, dateString);
        ui.prettyPrintList(filteredTasks);

    }

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
