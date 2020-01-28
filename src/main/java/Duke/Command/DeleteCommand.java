package Duke.Command;

import Duke.DukeExceptions.DukeException;
import Duke.List.DukeList;
import Duke.Storage.DukeStorage;
import Duke.Tasks.Task;
import Duke.UI.DukeUI;

public class DeleteCommand extends DukeCommand {
    private int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        Task removedTask = dl.deleteTask(deleteIndex);
        int remainingNum = dl.getNumOfTasks();
        System.out.println("    The task requested has been successfully removed:");
        System.out.println("      " + removedTask);

        if(remainingNum == 1) {
            System.out.println("    There is " + remainingNum + " task left.");
        } else {
            System.out.println("    There are " + remainingNum + " tasks left.");
        }
    }
}
