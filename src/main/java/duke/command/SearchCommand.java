package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class SearchCommand extends Command {
    private String searchKey;

    public SearchCommand(String searchKey) {
        super();
        this.searchKey = searchKey;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> tasksWithKey = new ArrayList<>();
        for (Task t : taskList.getTaskList()) {
            if (t.toString().contains(searchKey)) {
                tasksWithKey.add(t);
            }
        }
        ui.showList(tasksWithKey, "Here are the tasks that contains '" + searchKey + "' in your list:");
    }
}
