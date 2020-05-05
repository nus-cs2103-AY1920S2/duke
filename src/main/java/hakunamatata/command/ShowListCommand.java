package hakunamatata.command;

import hakunamatata.HakunaMatataException;
import hakunamatata.Storage;
import hakunamatata.Ui;
import hakunamatata.task.Task;
import hakunamatata.task.TaskList;

/**
 * Represents a command that used to display all current tasks in system storage.
 *
 * @author Wang Yuting
 */
public class ShowListCommand extends Command {

    private String title = "🆃🅰🆂🅺 🅻🅸🆂🆃\n";

    /**
     * Executes command to display all current tasks in system storage.
     *
     * @param tasks   The list of current tasks from storage.
     * @param ui      The current UI.
     * @param storage The system storage, used as database.
     * @throws HakunaMatataException If there is no task in current <code>tasks</code>.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HakunaMatataException {
        String output = "";
        if (tasks.getSize() == 0) {
            output = ui.getErrorMessage(new HakunaMatataException("emptyList"));
        } else {
            output += title;
            for (Task t : tasks.getTasks()) {
                int counter = tasks.getTasks().indexOf(t);
                output += (counter + 1) + "." + t.toString();
                if (tasks.getSize() - counter > 1) {
                    output += "\n";
                }
            }
        }
        return output;
    }

    /**
     * Returns true if it is an exit command.
     *
     * @return True, as it is the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
