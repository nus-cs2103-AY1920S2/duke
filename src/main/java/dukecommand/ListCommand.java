package dukecommand;

import dukeexceptions.InvalidEntryException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

import java.util.ArrayList;

public class ListCommand extends DukeCommand {
    ArrayList<String> toBePrinted;

    public ListCommand() {
    }

    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws InvalidEntryException {
        this.toBePrinted = dl.getListForUI();

        if (toBePrinted.isEmpty()) {
            dui.printCustomMessage("    There are 0 tasks in your list.");

        } else {
            dui.printCustomMessage("    Here are the tasks in your list:");
            for (String curr : toBePrinted) {
                dui.printCustomMessage(curr);
            }
        }

    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
