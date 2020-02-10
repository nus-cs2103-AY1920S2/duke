package dukecommand;

import dukeexceptions.InvalidEntryException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

/**
 * Represents a command that will display the help message which contains
 * all available commands and their required format.
 */


public class HelpCommand extends DukeCommand {
    public HelpCommand() {
    }

    /**
     * Executes the help command by having DukeUI return the help message
     *
     * @return  String message of help command
     *
     * @param dl DukeList from the main Duke program
     * @param ds DukeStorage from the main Duke program
     * @param dui DukeUI from the main Duke program
     */
    @Override
    public String execute(DukeList dl, DukeStorage ds, DukeUI dui) throws InvalidEntryException {
        dui.holdCurrentMessage(helpString);
        return dui.getCurrentMessage();
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }

    public String getHelpString() {
        return helpString;
    }

    private final String helpString =
            "    All available commands are: \n"
                    + "    1. 'help' to see all available commands.\n"
                    + "    2. 'list' to see all listed tasks.\n"
                    + "    3. 'todo x' where x is an event description to note a To-Do event.\n"
                    + "    4. 'deadline x /by y' where is x is a description and y is a date in YYYY-MM-DD format.\n"
                    + "    5. 'event x /at y' where x is an event description and y is a date in YYYY-MM-DD format.\n"
                    + "    6. 'delete x' where x in the task in the list you want to delete (1-indexed).\n"
                    + "    7. 'find x where x is a 1-word long string description'";
}
