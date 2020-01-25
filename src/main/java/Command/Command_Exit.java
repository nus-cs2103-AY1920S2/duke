package Command;

import Core.Common;
import Core.Ui;

public class Command_Exit extends Command{

    @Override
    public void execute(Common common, Ui ui) {
        ui.endLog();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
