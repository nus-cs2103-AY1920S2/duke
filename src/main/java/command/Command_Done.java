package command;

import core.Common;
import core.Ui;
import exception.DukeException;

public class Command_Done extends Command{

    private int index;

    public Command_Done(int index){
        this.index=index;
    }

    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.markAsDone(index));
    }
}
