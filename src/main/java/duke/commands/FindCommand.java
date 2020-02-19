package duke.commands;

import duke.Ui;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches for tasks from taskList with descriptions containing the FindCommand object's keyword
     * Passes these tasks to Ui for printing.
     *
     * @param storage
     * @param taskList
     * @param ui
     * @return String find command message
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) { // TODO change implementation to streams
        ArrayList<Task> lst = taskList.getTaskList();
        ArrayList<Task> found = new ArrayList<>();
        for (Task task : lst) {
            if (task.getDescription().contains(this.keyword)) {
                found.add(task);
            }
        }
        return ui.findMsg(found, this.keyword);
    }
}
