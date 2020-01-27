package Command;

import Core.Common;
import Core.Ui;
import Exceptions.DukeException;

public class Command_Reset extends Command{

    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        common.reset();
        ui.display("The system has been reset.");
    }
}
