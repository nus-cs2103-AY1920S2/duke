package duke.pack;

import java.util.Collections;

/**
 * Represents a sort command.
 */
public class SortCommand extends Command {
    /**
     * Creates a sort command.
     */
    public SortCommand() {
    }


    /**
     * Executes the sort command and prints sorted tasks.
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Collections.sort(tasks.getList(), new DateSorter());
        Collections.sort(tasks.getList(), new TimeSorter());
        ui.showSort();
    }

    /**
     * Executes the sort command and gets response for GUI.
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     */
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        Collections.sort(tasks.getList(), new DateSorter());
        Collections.sort(tasks.getList(), new TimeSorter());

        String resp = "I have sorted the tasks chronologically!";

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
