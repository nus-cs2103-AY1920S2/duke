package commands;

import resources.Statistics;

public class ExpireCommand extends UserCommand {

    /**
     * Instantiates a new User command.
     * Set the ID
     *
     */
    ExpireCommand() {
        super(12);
    }

    @Override
    public String reply() {
        return Statistics.getExpired();
    }
}
