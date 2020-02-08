package dukecommand;

import dukeexceptions.InvalidEntryException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

import java.util.ArrayList;


/**
 * Represents a command that will list out all current Tasks from DukeList
 */

public class ListCommand extends DukeCommand {
    ArrayList<String> toBePrinted;

    public ListCommand() {
    }

    /**
     * Returns a string of the current list of tasks from the DukeList
     *
     * @return Formatted String of all the tasks
     *
     * @param dl DukeList from the main Duke program
     * @param ds DukeStorage from the main Duke program
     * @param dui DukeUI from the main Duke program
     * @throws InvalidEntryException
     */
    @Override
    public String execute(DukeList dl, DukeStorage ds, DukeUI dui) throws InvalidEntryException {
        this.toBePrinted = dl.getListForUI();

        if (toBePrinted.isEmpty()) {
            dui.holdCurrentMessage("    There are 0 tasks in your list.");

        } else {
            dui.holdCurrentMessage("    Here are the tasks in your list:");
            dui.holdListOfTasks(toBePrinted);
        }

        String output = dui.getCurrentMessage();
        System.out.println("LisCom:\n" + output);
        return output;
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
