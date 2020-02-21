package Duke.command;

import Duke.TaskList;
import Duke.exception.DukeException;
import Duke.task.Task;

public class FindCommand extends Command {

    public FindCommand(TaskList taskList, String taskDesc) {
        super(taskList, taskDesc);
    }

    public String execute() throws DukeException {
        try {
            StringBuilder res = new StringBuilder();
            String listHeader = "Here are the matching tasks found in your list:\n";
            String keyWord = taskDesc.split(" ", 2)[1];
            ;
            ;
            int i = 1;
            for (Task t : this.list) {
                String taskType = t.getTaskType();
                if (t.toString().contains(keyWord) || taskType.contains(taskType)) {
                    res.append("     " + i + "." + t + "\n");
                    i++;
                }
            }
            if (res.length() == 0) {
                res.append("Sorry! I can't find any match for the keyword '" + keyWord + "'.");
            } else {
                res.insert(0, listHeader);
            }
            String out = res.toString();
            return out;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! The keyword to search for cannot be empty.");
        }
    }

}

