package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an find command. Upon execution, produces some feedback to the user.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * @param keyword keyword to find
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find the task which contains the keyword.
     *
     * @param tasks all of the tasks
     * @return a TaskList of result tasks
     */
    private TaskList getTaskWithKeyword(TaskList tasks) {
        TaskList result = new TaskList();
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(keyword)) {
                result.addTask(task);
            }
        }
        return result;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList result = getTaskWithKeyword(tasks);
        ui.showFindTask(result);
    }
}
