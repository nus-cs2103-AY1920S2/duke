package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a Command that finds a keyword from taskList.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        ArrayList<Task> queryTasks = new ArrayList<>();
        for (Task task: taskList.getList()) {
            if (task.toString().contains(keyword)) {
                queryTasks.add(task);
            }
        }

        return ui.showFindMessage(queryTasks);
    }
}
