package duke.save;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code SaveState} class helps to store a immutable state.
 */
public class SaveState {
    final List<Task> tasks;
    final String lastCommand;

    public SaveState(TaskList tasks, String lastCommand) {
        this.tasks = tasks.getImmutableDeepCopy();
        this.lastCommand = lastCommand;
    }

    public ArrayList<Task> getTasksFromSave() {
        return new ArrayList<Task>(this.tasks);
    }

    public String getLastCommand() {
        return this.lastCommand;
    }
}
