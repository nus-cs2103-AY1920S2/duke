package command;

import core.Common;
import core.Ui;
import dukexception.DukeException;


public class Command_Exit extends Command{


    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        common.saveData();
        ui.endLog();
    }


    @Override
    public boolean isExit() {
        return true;
    }
}
