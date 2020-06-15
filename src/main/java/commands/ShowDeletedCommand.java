package commands;

import resources.Statistics;

public class ShowDeletedCommand extends UserCommand {


    /**
     * Instantiates a new User command.
     * Set the ID
     *
     */
    ShowDeletedCommand() {
        super(11);
    }

    @Override
    public String reply() {
        return Statistics.getNumDeleteTask();
    }
}
