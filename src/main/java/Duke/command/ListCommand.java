package Duke.command;

import Duke.TaskList;
import Duke.task.Task;

public class ListCommand extends Command {

    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * String of every item in taskList.
     */
    public String execute() {
        StringBuilder res = new StringBuilder();
        String listHeader = "Here are the tasks in your list:\n";
        int i = 1;
        for (Task task : this.list) {
            res.append("     " + i + "." + task + "\n");
            i++;
        }
        if (res.length() != 0) {
            res.insert(0, listHeader);
        }
        return res.toString();
    }
}
