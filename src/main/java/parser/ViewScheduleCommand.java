package parser;

import java.time.LocalDate;

public class ViewScheduleCommand extends Command{
    private LocalDate targetDate;

    ViewScheduleCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public String execute() {
        return this.taskList.findTasksOnDate(targetDate);
    }
}
