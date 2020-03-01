package duke.save;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;

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
