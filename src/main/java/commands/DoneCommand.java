package commands;

import database.MyList;
import Duke.UI;
import exceptions.DukeException;

/**
 * The class Commands.DoneCommand handles done commands when user has completed the task
 */
public class DoneCommand extends UserCommand {
    /**
     * The Id.
     */
    static int id=0;

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
    public static void setDone(int ID){
        id = ID;
    }
    @Override
    public void reply(){
        System.out.println(UI.getReply("markedDone"));
        System.out.println("Marking item " + id +" as done");
    }

    @Override
    public boolean action() throws DukeException {
        MyList.markDone(id);
        return true;
    }
}
