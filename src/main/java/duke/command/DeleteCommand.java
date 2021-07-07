package duke.command;

import duke.DukeException;
import duke.main.TaskList;
import duke.main.Ui;
import duke.main.Storage;
import duke.task.Task;
import java.util.Arrays;

/**
 * Command that is executed when user inputs 'delete'.
 */
public class DeleteCommand extends Command {
    private int[] deleteIndices;

    public DeleteCommand(int[] deleteIndices) {
        this.deleteIndices = Arrays.stream(deleteIndices)
                .distinct()
                .sorted()
                .toArray();
    }

    /**
     * Executes the 'delete' command and saves latest version of the task list.
     *
     * @param tasks Task list
     * @param ui Current user interface
     * @param storage Current storage
     * @return String representation of success message after task is deleted
     * @throws DukeException If task list fails to save
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deleteIndices.length; i++) {
            sb.append("\n");
            Task deletedTask = tasks.getTaskList().get(deleteIndices[i] - i - 1);
            tasks.deleteFromTaskList(deleteIndices[i] - i - 1);
            storage.save(tasks.getTaskList());
            sb.append(deletedTask.toString());
        }
        return ui.showDelete(sb.toString(), tasks.getTaskList().size(), deleteIndices.length);
    }

    /**
     * Updates main function if it should exit the programme or not.
     *
     * @return True if 'bye' command is called, false otherwise. In this case, false is returned.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
