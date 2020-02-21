package Duke.command;

import Duke.TaskList;
import Duke.exception.DukeException;
import Duke.task.Task;

public class FindCommand extends Command {

    public FindCommand(TaskList taskList, String taskDesc) {
        super(taskList, taskDesc);
    }

    public String execute() throws DukeException {
        String out = "";
        try {
            StringBuilder res = new StringBuilder();
            String listHeader = "Here are the matching tasks found in your list:\n";
            String keyWord = taskDesc.split(" ", 2)[1].toLowerCase(); //keyword is case-insensitive
            int i = 1;
            for (Task t : this.list) {
                String taskTypeLowerCase = t.getTaskType().toLowerCase();
                String taskDescLowerCase = t.toString().toLowerCase();
                if (taskDescLowerCase.contains(keyWord) || taskTypeLowerCase.contains(keyWord)) {
                    res.append("     " + i + "." + t + "\n");
                    i++;
                }
            }
            if (res.length() == 0) {
                res.append("Sorry! I can't find any match for the keyword '" + keyWord + "'.");
            } else {
                res.insert(0, listHeader);
            }
            out = res.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! The keyword to search for cannot be empty.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

}

