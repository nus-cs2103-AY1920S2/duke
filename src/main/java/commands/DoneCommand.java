package commands;

import database.MyList;
import duke.UI;
import exceptions.DukeException;

/**
 * The class Commands.DoneCommand handles done
 * commands when user has completed the task
 */
public class DoneCommand extends UserCommand {
    /**
     * The Id.
     */
    static int id = 0;

    /**
     * Instantiates a new Done command.
     *
     * @param id the id
     */
    DoneCommand(int id) {
        super(4);
        setDone(id);
    }

    /**
     * Set done.
     *
     * @param ID the id
     */
    public static void setDone(int ID) {
        id = ID;
    }
    @Override
    public String reply() {
        return UI.getReply("markedDone") + "\nMarking item " + id + " as done";
    }

    @Override
    public void action() throws DukeException {
        MyList.markDone(id);
    }
}
