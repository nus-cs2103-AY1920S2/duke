package duke.command;

import java.util.Optional;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

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
     *
     * @param tasks   list of tasks
     * @param ui      prints information to user
     * @param storage manages user save file
     * @return TaskList required for indicating updating of tasks
     */
    @Override
    public Optional<TaskList> execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            String taskDescription = task.getDescription().toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }
        ui.listTasks(matchingTasks, "Here are the matching tasks in your list:");
        return Optional.empty();
    }
}
