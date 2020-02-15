package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    /**
     * The keyword used to find tasks in a task list.
     * Note that the search is case insensitive. */
    private String keyword;

    /**
     * Constructs a new command that finds tasks in a task list
     * that contain a specific keyword.
     * Note that the search is case insensitive.
     *
     * @param keyword the keyword used to find tasks in a task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Integer> taskIds = new ArrayList<>();

        // Find all task descriptions containing the keyword
        for (int i = 1; i <= tasks.getNumTasks(); i++) {
            String description = tasks.get(i).getDescription().toLowerCase();

            if (description.contains(keyword)) {
                taskIds.add(i);
            }
        }
        // Just display list of tasks with keyword in description
        ui.showFind(tasks, taskIds);

        return tasks; // Don't update anything
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
