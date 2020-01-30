package duke.pack;

import java.util.ArrayList;

/**
 * represents a command to find tasks matching user's input
 */
public class FindCommand extends Command {
    protected String query;

    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the find command and prints matching tasks
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getList();
        ui.showFind();

        int i = 1;

        for (Task t: list) {
            if (t.getFullDesc().contains(query)) {
                System.out.println("    " + i + "." + t);
            }
            i++;
        }
    }

    public Boolean isExit() {
        return false;
    }

}
