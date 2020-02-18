package dukecommand;

import dukelist.DukeList;
import dukestorage.DukeStorage;
import duketasks.Task;
import dukeui.DukeUI;

/**
 * Represents a command that adds a new Task into the DukeList
 */

public class AddCommand extends DukeCommand {
    private Task toBeAdded;

    public AddCommand(Task tba) {
        this.toBeAdded = tba;
    }

    /**
     * Executes the requested command by adding the task to the list, saving the changes in the storage
     * and returns the success message of adding the task
     *
     * @return String output message of a successful add command
     *
     * @param dl DukeList from the main Duke program
     * @param ds DukeStorage from the main Duke program
     * @param dui DukeUI from the main Duke program
     */
    public String execute(DukeList dl, DukeStorage ds, DukeUI dui) {
        assert this.toBeAdded != null : "Task to be added cannot be null!";
        dl.addTask(this.toBeAdded);
        ds.save(dl);

        dui.holdCurrentMessage("    Got it I've added this task:");
        dui.holdCurrentMessage("        " + toBeAdded.getTaskName());
        String output = String.format("    Now you have %d tasks in the list.", dl.getNumOfTasks());
        dui.holdCurrentMessage(output);
        return dui.getCurrentMessage();
    }

    /**
     * Returns a True boolean if this is a ByeCommand
     *
     * @return False as this is not a ByeCommand
     */
    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
