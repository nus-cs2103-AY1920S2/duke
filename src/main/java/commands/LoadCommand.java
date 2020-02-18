package commands;

import database.MyList;
import exceptions.DukeException;

/**
 * Loads the memory back into list
 */
public class LoadCommand extends AddCommand {

    private static boolean isDone = false;
    private static int count = 0;

    /**
     * Instantiates a new Load command.
     *
     * @param type    the type of task
     * @param setDone the set done status of tasks
     * @param desc    the description of the task
     */
    public LoadCommand(String type, boolean setDone, String desc) {
        super(type, desc);
        this.isDone = setDone;
    }

    @Override
    public void action() throws DukeException {
        super.action();
        count++;
        if (isDone) {
            MyList.markDone(count);
        }
    }
}
