package Duke.command;

import Duke.TaskList;
import Duke.task.Task;

import java.util.Objects;

public class AddCommand extends Command {
    Task taskAdded;

    public AddCommand(TaskList taskList, Task newTask) {
        super(taskList);
        this.taskAdded = newTask;
    }

    /**
     * Executes the add command, to add task to task list, and save command in stats. Finally, save the task list.
     * @return Success message if succesfully added task, otherwise, failure message.
     */
    public String execute() {
        String out;
        if (Objects.isNull(taskAdded)) {
            out = "Attempting to add invalid task. Operation aborted.";
        } else {
            list.add(taskAdded);
            stats.add(this);
            out = "Got it. I've added this task:\n" + taskAdded + "\n" + "Now you have "
                    + list.size() + " tasks in the list.";
        }
        storage.saveTask(list);
        statStorage.saveStats(stats);
        return out;
    }

    public String toString() {
        return "Added task: " + taskAdded.toString();
    }
}
