package hakunamatata.command;

import hakunamatata.HakunaMatataException;
import hakunamatata.Storage;
import hakunamatata.Ui;
import hakunamatata.task.Task;
import hakunamatata.task.TaskList;

/**
 * Represents a command that used to mark a task at <code>index</code> as done.
 *
 * @author Wang Yuting
 */
public class DoneCommand extends Command {

    private int index;
    private String title = "🆃🅰🆂🅺 🅲🅾🅼🅿🅻🅴🆃🅴🅳\n";

    /**
     * Creates a new <code>DoneCommand</code> with the given index.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes command to mark a task at <code>index</code> as done.
     *
     * @param tasks   The list of current tasks from storage.
     * @param ui      The current UI.
     * @param storage The system storage, used as database.
     * @throws HakunaMatataException If there is no task in current <code>tasks</code>,
     *                       the <code>index</code> is out side of the valid range
     *                       or the selected task is done already.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HakunaMatataException {
        int listSize = tasks.getSize();

        if (listSize == 0) {
            throw new HakunaMatataException("emptyList");
        }
        if (this.index > listSize || index < 1) {
            throw new HakunaMatataException("doneWrongIndexRange");
        }

        Task chosenTask = tasks.getTasks().get(index - 1);
        if (chosenTask.isDone()) {
            throw new HakunaMatataException("doneComplectedTask");
        }

        chosenTask.markAsDone();
        assert chosenTask.isDone() : "The task selected is still in pending status after this action,"
                + " please check.";
        storage.save(tasks);

        String output = title
                + chosenTask.toString();
        return output;
    }

    /**
     * Returns true if it is an exit command.
     *
     * @return False, as it is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
