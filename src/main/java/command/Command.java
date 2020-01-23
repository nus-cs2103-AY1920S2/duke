package command;

import core.Common;
import core.Ui;
import exception.DukeException;


public abstract class Command {

    public void execute(Common common, Ui ui) throws DukeException {}

    public boolean isExit(){
        return false;
    }
}
