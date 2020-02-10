package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.interact.UiDesign;
import duke.task.Task;

/**
 * Represents an command that adds a new Task to the TaskList.
 */
public class CommandAdd implements Command {

    private StringBuilder appendedText;
    Task taskToBeAdded;

    /**
     * Remembers the Task to be added in execute.
     * @param taskToBeAdded Task to be added.
     */
    public CommandAdd(Task taskToBeAdded) {
        this.taskToBeAdded = taskToBeAdded;
    }

    /**
     * Adds the Task to the TaskList and generates the StringBuilder for output.
     * @param tasks TaskList to store the Task in.
     * @param storage Storage to save data.
     * @throws DukeException Exception thrown if saving fails.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        int numTasks = tasks.getSize() + 1;
        appendedText = new StringBuilder(UiDesign.ADD_TOP_PART.getString());
        appendedText.append(taskToBeAdded)
                .append("\n")
                .append("Now you have ").append(numTasks).append(" tasks in the list.\n")
                .append(UiDesign.BORDER.getString());
        tasks.addTask(taskToBeAdded);
        storage.save(tasks);
    }

    @Override
    public String output() {
        return appendedText.toString();
    }
}
