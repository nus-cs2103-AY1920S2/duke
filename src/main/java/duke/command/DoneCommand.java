package duke.command;

import duke.main.TaskList;
import duke.main.Ui;
import duke.main.Storage;
import duke.DukeException;
import duke.task.Task;
import java.util.Arrays;

/**
 * Command that is executed when user inputs 'done'.
 */
public class DoneCommand extends Command {
    private int[] doneIndices;

    public DoneCommand(int[] doneIndices) {
        this.doneIndices = Arrays.stream(doneIndices)
                .distinct()
                .sorted()
                .toArray();
    }

    /**
     * Executes the 'done' command and saves latest version of the task list.
     *
     * @param tasks Task list
     * @param ui Current user interface
     * @param storage Current storage
     * @return String representation of success message after task is marked as done
     * @throws DukeException If task list fails to save
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (int index : doneIndices) {
            sb.append("\n");
            Task doneTask = tasks.getTaskList().get(index - 1);
            doneTask.markAsDone();
            storage.save(tasks.getTaskList());
            sb.append(doneTask.toString());
        }
        return ui.showDone(sb.toString(), doneIndices.length);
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
