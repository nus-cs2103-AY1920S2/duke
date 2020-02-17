package commands;

import exceptions.DukeException;

/**
 * The class Commands.UserCommand acts as a parent class for commands
 */
public class UserCommand implements Command {
    private int ID = 0;

    /**
     * Instantiates a new User command.
     * Set the ID
     *
     * @param id the id
     */
    UserCommand(int id){
        ID = id;
    };


    @Override
    public void reply() {
        System.out.println("This is some crazy event");
    }

    @Override
    public boolean action() throws DukeException {
        return true;
    }


    @Override
    public int getID() {
        return ID;
    }
}
