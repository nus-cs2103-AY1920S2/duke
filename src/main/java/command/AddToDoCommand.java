package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;
import task.ToDo;

import java.io.IOException;

/**
 * A command object for adding todo tasks to the list.
 */
public class AddToDoCommand extends AddCommand {
    /**
     * Constructs a command object to add todo task to the list.
     * @param taskName The name of the todo task to be added.
     */
    public AddToDoCommand(String taskName) {
        super(taskName);
    }

    /**
     * Executes the command to add todo tasks to the list.
     * @param tasks List of tasks of the Duke object.
     * @param ui UI object of the Duke object.
     * @param storage Storage object of the Duke object.
     * @return String as the response of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new ToDo(this.taskName);
            tasks.add(newTask);
            storage.updateFile(newTask);
            return Ui.showTask(newTask, tasks.size());
        } catch (IOException e) {
            return Ui.showException(new DukeException(
                    "☹ OOPS!!! The file of duke.txt can't be updated, list not updated."));
        }
    }
}
