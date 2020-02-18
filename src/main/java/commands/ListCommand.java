package commands;

import database.MyList;

/**
 * The command handles when user calls list
 */
public class ListCommand extends UserCommand {
    /**
     * Instantiates a new List command.
     */
    ListCommand() {
        super(3);
    }
    @Override
    public String reply() {
        return MyList.printList();
    }
}
