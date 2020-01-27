package Command;

import Core.Common;
import Core.Ui;
import Exceptions.DukeException;

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
