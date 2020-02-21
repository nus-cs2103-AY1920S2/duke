package Duke.command;

import Duke.TaskList;
import Duke.exception.DukeException;
import Duke.task.Task;

public class DeleteCommand extends Command {
    Task taskDeleted;

    public DeleteCommand(TaskList taskList, String taskDesc) {
        super(taskList, taskDesc);
    }

    public String execute() throws DukeException {
        String out;
        try {
            String taskNum = taskDesc.split(" ", 2)[1];;
            Task currTask = list.get(Integer.parseInt(taskNum) - 1);
            list.remove(currTask);
            taskDeleted = currTask;
            stats.add(this);
            out = "Noted. I've removed this task:\n" +  currTask + "\nNow you have " + list.size()
                    + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            StringBuilder errMsg = new StringBuilder("☹ Ahhh!!! Please input a valid number in the range of the "
                    + "task list to delete.\nUnsure? List the tasks out by typing the 'list' command to see your "
                    + "available tasks and their respective task number.");
            if (!list.isEmpty()) {
                if (list.size() == 1) {
                    errMsg.append("Available range: 1");
                } else {
                    errMsg.append("Available range from 1 to " + list.size());
                }
            }
            throw new DukeException(errMsg.toString());

        } catch (NumberFormatException e) { // when non-int arg provided
            out = "☹ Ahhh!!! Delete must take a valid INTEGER in the range of the task list.\nUnsure? List the "
                    + "tasks out by typing the 'list' command to see your available tasks and their respective "
                    + "task number.";
            throw new DukeException(out);
        } finally {
            storage.saveTask(list);
            statStorage.saveStats(stats);
        }
        return out;
    }

    public String toString() {
        return "Deleted task: " + taskDeleted;
    }
}
