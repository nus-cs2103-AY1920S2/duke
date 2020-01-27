package Command;

import Core.Common;
import Core.Ui;
import Exceptions.DukeException;

public class Command_List extends Command{

    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.printList());
    }
}
