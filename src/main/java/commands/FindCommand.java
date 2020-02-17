package commands;

import database.MyList;
import exceptions.DukeException;
import resources.Task;
import java.util.ArrayList;

/**
 * FindCommand command handles when user wants to find something in list
 */
public class FindCommand extends UserCommand{
    private static String keyword;

    /**
     * Instantiates a new User command.
     * Set the ID
     *
     * @param str the keyword
     */
    FindCommand(String str){
        super(6);
        keyword = str;
    }

    /**
     * Set keyword.
     *
     * @param str the str
     */
    public static void setKeyword(String str){
        keyword = str;
    }

    @Override
    public boolean action() throws DukeException {
        System.out.println(MyList.find(keyword));
        return true;
    }
}
