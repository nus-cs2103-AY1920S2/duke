package dukecommand;

import dukeexceptions.InvalidEntryException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import duketasks.Task;
import dukeui.DukeUI;

public class AddCommand extends DukeCommand {
    private Task toBeAdded;

    public AddCommand(Task tba) {
        this.toBeAdded = tba;
    }

    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws InvalidEntryException {
        if (toBeAdded == null) {
            throw new InvalidEntryException("Error in AddCommand. There's no task for me to add!");
        } else {
            dl.addTask(toBeAdded);
            ds.save(dl);
            dui.printCustomMessage("    Got it I've added this task:\n      " + toBeAdded);
            System.out.printf("    Now you have %d tasks in the list.\n", dl.getNumOfTasks());
        }
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
