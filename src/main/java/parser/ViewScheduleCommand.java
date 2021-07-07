package parser;

import static parser.Parser.VIEW_SCHEDULE_PATTERN;

import exceptions.IllegalDateTimeFormatException;

import java.time.LocalDate;

public class ViewScheduleCommand extends Command {
    private LocalDate targetDate;

    ViewScheduleCommand(String userInput) throws IllegalDateTimeFormatException {
        targetDate = this.findDate(VIEW_SCHEDULE_PATTERN, userInput);
    }

    public String execute() {
        return this.taskList.findTasksOnDate(targetDate);
    }
}
