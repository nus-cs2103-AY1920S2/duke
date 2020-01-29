package duke.task;

import duke.main.UI;
import duke.exception.UnknownTaskException;

import java.util.List;

public class TaskList {
    //Custom deleteCommand Method to delete Tasks
    public static void deleteTask(List<Task> taskList, String commandSuffix) throws UnknownTaskException {
        try {
            int deleteTaskNo = Integer.parseInt(commandSuffix) - 1;
            Task deletedShadowTask = taskList.get(deleteTaskNo);
            taskList.remove(deleteTaskNo);

            for (Task task : taskList) {
                task.taskNo = taskList.indexOf(task) + 1;
            }

            UI.print("Noted. I've removed this task:\n\t" + deletedShadowTask
                    + "\nNow you have " + taskList.size() + " task(s) in the list.");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new UnknownTaskException();
        }
    }

    //Custom doneCommand Method to mark Tasks as done
    public static void doneTask(List<Task> taskList, String commandSuffix) throws UnknownTaskException {
        try {
            int doneTaskNo = Integer.parseInt(commandSuffix) - 1;
            taskList.get(doneTaskNo).taskCompleted = true;
            UI.print("Nice! I've marked this task as done:\n\t[✓] " + taskList.get(doneTaskNo).taskName);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new UnknownTaskException();
        }
    }
}
