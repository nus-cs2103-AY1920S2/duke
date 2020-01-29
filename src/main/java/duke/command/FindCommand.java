package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
