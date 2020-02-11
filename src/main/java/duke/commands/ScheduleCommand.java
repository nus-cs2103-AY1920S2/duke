package duke.commands;

import duke.exceptions.WrongScheduleFormatException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ScheduleCommand implements Command {
    /**
     * Prints out the current list of tasks to terminal
     * @param tasks List of current tasks
     * @throws DukeException If input format is wrong. Not used here
     */
    public static String execute(String date, TaskList tasks) throws DukeException {

        LocalDate taskDate = null;
        try {
            taskDate = Task.parseDate(date);
        } catch (DateTimeParseException e) {
            throw new WrongScheduleFormatException();
        }

        return tasks.printSchedule(taskDate);
    }
}
