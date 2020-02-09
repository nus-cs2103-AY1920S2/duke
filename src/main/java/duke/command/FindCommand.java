package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Optional;

/**
 * Represents a command used to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Finds all tasks in task list with matching keyword in the task description
     * and prints out all found tasks to user.
     *  @param tasks list of tasks
     * @param ui prints information to user
     * @param storage manages user save file
     * @return TaskList required for indicating updating of tasks
     */
    @Override
    public Optional<TaskList> execute(TaskList tasks, Ui ui, Storage storage) {
        // Find all tasks with description matching keyword
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        // Print matching tasks using ui
        ui.listTasks(matchingTasks, "Here are the matching tasks in your list:");
        return Optional.empty();
    }
}
