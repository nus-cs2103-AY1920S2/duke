package duke.ui;

import duke.Duke;
import duke.DukeHistory;
import duke.parser.CommandExecutionExeption;
import duke.parser.Parser;

public class UiLogic {

    public void executeCommand(String cmd) throws CommandExecutionExeption {
        DukeHistory.progress(Parser.parse(cmd).execute(DukeHistory.getCurrent()));
    }
}
