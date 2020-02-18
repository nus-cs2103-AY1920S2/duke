package commands;

import duke.MainWindow;
import duke.UI;

public class StatsCommand extends UserCommand {
    /**
     * Instantiates a new User command.
     * Set the ID
     *
     */
    StatsCommand() {
        super(13);
    }

    @Override
    public String reply() {
        return UI.getInstrStats();
    }
}
