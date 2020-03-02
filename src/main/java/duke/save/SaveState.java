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
    final List<Task> archive;
    final String lastCommand;

    /**
     * Initialises a {@code SaveState} instance.
     *
     * @param tasks       Task list to save.
     * @param lastCommand Command to save.
     */
    public SaveState(TaskList tasks, String lastCommand) {
        this.tasks = tasks.getImmutableDeepCopyOfTasks();
        this.archive = tasks.getImmutableDeepCopyOfArchive();
        this.lastCommand = lastCommand;
    }

    /**
     * Returns the saved active tasks.
     *
     * @return Saved active tasks.
     */
    public ArrayList<Task> getTasksFromSave() {
        return new ArrayList<Task>(this.tasks);
    }

    /**
     * Returns the saved archived tasks.
     *
     * @return Saved archived tasks.
     */
    public ArrayList<Task> getArchiveFromSave() {
        return new ArrayList<Task>(this.archive);
    }

    /**
     * Returns the saved command.
     *
     * @return Saved command.
     */
    public String getLastCommand() {
        return this.lastCommand;
    }
}
