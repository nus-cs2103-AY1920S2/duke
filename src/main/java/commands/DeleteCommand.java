package commands;

import database.MyList;
import Duke.UI;
import exceptions.DukeException;

/**
 * The class Commands.DeleteCommand handles the deletion of tasks
 */
public class DeleteCommand extends UserCommand {

    private static int id;

    /**
     * Instantiates a new Delete command.
     *
     * @param id the id
     */
    DeleteCommand(int id){
        super(5);
        setDel(id);
    }

    /**
     * Set the id of tasks
     *
     * @param ID the id
     */
    public static void setDel(int ID){
        id = ID;
    }

    @Override
    public void reply(){
        System.out.println(UI.getReply("delete"));
    }


    @Override
    public boolean action() throws DukeException {

        MyList.deleteItem(id);
        MyList.printCount();
        return true;
    }
}
