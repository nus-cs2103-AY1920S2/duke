package commands;

import resources.Statistics;

public class ThisWeekCommand extends UserCommand {

    /**
     * Instantiates a new User command.
     * Set the ID
     *
     */
    ThisWeekCommand() {
        super(7);
    }

    @Override
    public String reply() {
        return Statistics.taskDoneWeek();
    }
}
