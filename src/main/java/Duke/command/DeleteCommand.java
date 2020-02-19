package Duke.command;

import Duke.TaskList;
import Duke.task.Task;

public class DeleteCommand extends Command {
    Task taskDeleted;

    public DeleteCommand(TaskList taskList, String taskDesc) {
        super(taskList, taskDesc);
    }

    public String execute() {
        String out;
        try {
            String taskNum = taskDesc;
            Task currTask = list.get(Integer.parseInt(taskNum) - 1);
            list.remove(currTask);
            taskDeleted = currTask;
            stats.add(this);
            out = "Noted. I've removed this task:\n" +  currTask + "\nNow you have " + list.size()
                    + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            out = "☹ OOPS!!! Please input a valid number in the range of the task list to delete.\nUnsure? List the" +
                    "tasks out by typing the 'list' command to see your available tasks and their respective " +
                    "task number.";
        } catch (NumberFormatException e) { // when non-int arg provided
            out = "☹ OOPS!!! Delete must take a valid integer in the range of the task list.";
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
