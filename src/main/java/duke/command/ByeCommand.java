package duke.command;

import duke.main.Ui;

public class ByeCommand extends Command {
    public static boolean run() {
        Ui.end();
        return true;
    }
}
