package commands;

import resources.Statistics;

public class TodayCommand extends UserCommand {
    /**
     * Instantiates a new User command.
     * Set the ID
     *
     */
    TodayCommand() {
        super(10);
    }

    @Override
    public String reply() {
        return Statistics.taskDone();
    }
}
