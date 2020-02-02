package duke.command;

import duke.exception.UnknownDateTimeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CalendarCommand implements Command {
    /**
     * calendarCommand Method finds Tasks that finds, matches and displays Tasks
     * on a specific date.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     * @param commandSuffix is the additional String that accompanies two-step commands
     * @throws UnknownDateTimeException when improperly formatted DateTime values are given
     */
    public static String run(TaskList taskList, String commandSuffix) throws UnknownDateTimeException {
        try {
            DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate calendarDate = LocalDate.parse(commandSuffix, dtFormat);
            return calendarFind(taskList, calendarDate);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new UnknownDateTimeException();
        }
    }

    //Custom calendarFind Method to find and print the list with the horizontal borders + running index
    static String calendarFind(TaskList taskList, LocalDate calendarDate) throws DateTimeParseException {
        StringBuilder sb = new StringBuilder();
        List<String> calendarList = new ArrayList<>();

        for (Task task : taskList.getTasks()) {
            if (task.getClass().equals(Deadline.class)) {
                DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma)");
                LocalDateTime testDate = LocalDateTime.parse(((Deadline) task).byDeadline.substring(5), dtFormat);
                LocalDate taskDate = testDate.toLocalDate();

                if (taskDate.equals(calendarDate)) {
                    calendarList.add(task.toString());
                }
            }
        }

        if (calendarList.size() == 0) {
            sb.append("No matching deadlines found.");
        } else {
            sb.append("Here are the task(s) in your list on that date:\n");

            for (String task : calendarList) {
                sb.append(task).append("\n");
            }

            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
}
