package commands;

import database.MyList;


/**
 * FindCommand command handles when user wants to find something in list
 */
public class FindCommand extends UserCommand {
    private static String keyword;

    /**
     * Instantiates a new User command.
     * Set the ID
     *
     * @param str the keyword
     */
    FindCommand(String str) {
        super(6);
        keyword = str;
    }

    @Override
    public String reply() {
        return MyList.find(keyword);
    }
}
