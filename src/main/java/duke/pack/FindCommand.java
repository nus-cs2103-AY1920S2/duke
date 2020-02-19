package duke.pack;

import java.util.ArrayList;

/**
 * Represents a command to find tasks matching user's input.
 */
public class FindCommand extends Command {
    protected String query;

    /**
     * Creates a find command.
     * @param query search keyword
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the find command and prints matching tasks.
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getList();
        ui.showFind();

        int i = 1;

        for (Task t: list) {
            if (t.toString().contains(query)) {
                System.out.println("    " + i + "." + t);
            }
            i++;
        }
    }

    /**
     * Executes find command and gets response for the GUI
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @return String response for GUI
     */
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        String resp = "I have found these matching tasks! \n";

        ArrayList<Task> list = tasks.getList();

        int i = 1;

        for (Task t: list) {
            if (t.toString().contains(query)) {
                resp = resp + i + "." + t + "\n";
            }
            i++;
        }

        return resp;
    }

    /**
     * Indicates whether command is exit.
     * @return boolean true if it is an exit command, else false
     */
    public Boolean isExit() {
        return false;
    }

}
