package command;

import core.Common;
import core.Ui;
import exception.DukeException;

public class Command_List extends Command{

    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.printList());
    }
}
