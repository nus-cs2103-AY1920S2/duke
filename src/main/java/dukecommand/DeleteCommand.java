package dukecommand;

import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import duketasks.Task;
import dukeui.DukeUI;

public class DeleteCommand extends DukeCommand {
    private int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        Task removedTask = dl.deleteTask(deleteIndex);
        ds.save(dl);
        System.out.println("    The task requested has been successfully removed:");
        System.out.println("      " + removedTask);

        int remainingNum = dl.getNumOfTasks();
        if (remainingNum == 1) {
            System.out.println("    There is " + remainingNum + " task left.");
        } else {
            System.out.println("    There are " + remainingNum + " tasks left.");
        }
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
