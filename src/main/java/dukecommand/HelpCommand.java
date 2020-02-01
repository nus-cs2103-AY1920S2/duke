package dukecommand;

import dukeexceptions.InvalidEntryException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

public class HelpCommand extends DukeCommand {
    public HelpCommand() {
    }

    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws InvalidEntryException {
        dui.printCustomMessage(helpString);
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }

    private final String helpString =
            "    All available commands are: \n"
                    + "    1. 'help' to see all available commands.\n"
                    + "    2. 'list' to see all listed tasks.\n"
                    + "    3. 'todo x' where x is an event description to note a To-Do event.\n"
                    + "    4. 'deadline x /by y' where is x is a description and y is a date in YYYY-MM-DD format.\n"
                    + "    5. 'event x /at y' where x is an event description and y is a date in YYYY-MM-DD format.\n"
                    + "    6. 'delete x where x in the task in the list you want to delete (1-indexed.)";
}
