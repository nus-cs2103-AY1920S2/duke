package duke.command;

import duke.gui.Gui;
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

    /**
     * Search for task.
     * @param taskList List of tasks.
     * @return Tasks that have keyword.
     */
    public ArrayList<Task> search(TaskList taskList) {
        ArrayList<Task> tasksWithKey = new ArrayList<>();
        for (Task t : taskList.getTaskList()) {
            if (t.toString().contains(searchKey)) {
                tasksWithKey.add(t);
            }
        }
        return tasksWithKey;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> tasksWithKey = search(taskList);
        ui.showList(tasksWithKey, "Here are the tasks that contains '" + searchKey + "' in your list:");
    }

    @Override
    public String execute(TaskList taskList, Gui gui) {
        ArrayList<Task> tasksWithKey = search(taskList);
        return gui.showList(tasksWithKey, "Here are the tasks that contains '" + searchKey + "' in your list:");
    }
}
