package duke.task;

import duke.exception.DukeException;
import duke.exception.UnknownTaskException;
import duke.main.Storage;
import duke.main.Ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    List<Task> taskList = new ArrayList<>();

    public TaskList() throws DukeException {
    }

    public TaskList(Storage storage) throws DukeException {
        super();
        taskList = storage.load().getTasks();
    }

    //Custom deleteCommand Method to delete Tasks
    public void deleteTask(String commandSuffix) throws UnknownTaskException {
        try {
            int deleteTaskNo = Integer.parseInt(commandSuffix) - 1;
            Task deletedShadowTask = taskList.get(deleteTaskNo);
            taskList.remove(deleteTaskNo);

            for (Task task : taskList) {
                task.taskNo = taskList.indexOf(task) + 1;
            }

            Ui.print("Noted. I've removed this task:\n\t" + deletedShadowTask
                + "\nNow you have " + taskList.size() + " task(s) in the list.");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new UnknownTaskException();
        }
    }

    //Custom doneCommand Method to mark Tasks as done
    public void doneTask(String commandSuffix) throws UnknownTaskException {
        try {
            int doneTaskNo = Integer.parseInt(commandSuffix) - 1;
            taskList.get(doneTaskNo).taskCompleted = true;
            Ui.print("Nice! I've marked this task as done:\n\t[✓] " + taskList.get(doneTaskNo).taskName);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new UnknownTaskException();
        }
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public boolean add(Task t) {
        taskList.add(t);
        return true;
    }

    public int size() {
        return taskList.size();
    }
}
