package command;

import core.Common;
import core.Ui;
import core.UiMessage;
import dukexception.DukeException;


public class Command_Reset extends Command{


    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        common.reset();
        ui.display(UiMessage.RESET.getMsg());
    }
}
