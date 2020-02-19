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
        String out = "Here are the tasks in your list:\n";
        int i = 1;
        for (Task task : list) {
            out += "     " + i + "." + task + "\n";
            i++;
        }
        return out;
    }
}
