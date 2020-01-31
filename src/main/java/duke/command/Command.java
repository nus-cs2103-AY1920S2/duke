package duke.command;

import duke.exception.DukeException;
import duke.exception.MissingAtEventException;
import duke.exception.MissingByDeadlineException;
import duke.exception.MissingDetailsException;
import duke.exception.UnknownDateTimeException;
import duke.main.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Command {
    //Custom byeCommand Method to exit Duke
    public static boolean byeCommand() {
        Ui.print("Bye. Hope to see you again soon!");
        return false;
    }

    /**
     * calendarCommand Method finds Tasks that finds, matches and displays Tasks
     * on a specific date.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     * @param commandSuffix is the additional String that accompanies two-step commands
     * @throws UnknownDateTimeException when improperly formatted DateTime values are given
     */
    public static void calendarCommand(TaskList taskList, String commandSuffix) throws UnknownDateTimeException {
        try {
            DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate calendarDate = LocalDate.parse(commandSuffix, dtFormat);
            calendarFind(taskList, calendarDate);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new UnknownDateTimeException();
        }
    }

    //Custom calendarFind Method to find and print the list with the horizontal borders + running index
    static void calendarFind(TaskList taskList, LocalDate calendarDate) throws DateTimeParseException {
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

        Ui.print(sb.toString());
    }

    /**
     * deadlineCommand Method creates Deadline Tasks.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     * @param commandSuffix is the additional String that accompanies two-step commands
     * @throws DukeException when multiple exceptions are caught (e.g. unfilled secondary input)
     */
    public static void deadlineCommand(TaskList taskList, String commandSuffix) throws DukeException {
        try {
            String[] byDeadline = commandSuffix.split(" /by ");

            try {
                //Initialising proTip; also used as a silent check for MissingByDeadlineException
                String proTip = byDeadline[1];
                boolean unknownDate = false;

                try {
                    try {
                        DateTimeFormatter inputdTFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime outputDT = LocalDateTime.parse(byDeadline[1], inputdTFormat);
                        DateTimeFormatter outputdTFormat = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma");
                        byDeadline[1] = outputDT.format(outputdTFormat);
                    } catch (DateTimeParseException e) {
                        throw new UnknownDateTimeException();
                    }
                } catch (DukeException e) {
                    unknownDate = true;
                    proTip = e.toString();
                }

                taskList.add(new Deadline(false, taskList.size(), byDeadline[0], byDeadline[1]));

                String deadlineOutput = ("Got it. I've added this task:\n\t[D][✗] "
                    + byDeadline[0] + " (by: " + byDeadline[1] + ")"
                    + "\nNow you have " + taskList.size() + " task(s) in the list.");

                if (unknownDate) {
                    deadlineOutput = deadlineOutput + "\nPS: " + proTip;
                }

                Ui.print(deadlineOutput);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingByDeadlineException();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingDetailsException();
        }
    }

    /**
     * eventCommand Method creates Event Tasks.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     * @param commandSuffix is the additional String that accompanies two-step commands
     * @throws DukeException when multiple exceptions are caught (e.g. unfilled secondary input)
     */
    public static void eventCommand(TaskList taskList, String commandSuffix) throws DukeException {
        try {
            String[] atEvent = commandSuffix.split(" /at ");

            try {
                taskList.add(new Event(false, taskList.size(), atEvent[0], atEvent[1]));
                Ui.print("Got it. I've added this task:\n\t[E][✗] "
                    + atEvent[0] + " (at: " + atEvent[1] + ")"
                    + "\nNow you have " + taskList.size() + " task(s) in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingAtEventException();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingDetailsException();
        }
    }

    /**
     * listCommand Method prints the list of Tasks (if not empty) running index.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     */
    public static void listCommand(TaskList taskList) {
        StringBuilder sb = new StringBuilder();

        if (taskList.size() == 0) {
            sb.append("List is empty.");
        } else {
            sb.append("Here are the task(s) in your list:\n");

            for (Task task : taskList.getTasks()) {
                sb.append(task).append("\n");
            }
        }

        sb.setLength(sb.length() - 1);
        Ui.print(sb.toString());
    }

    /**
     * todoCommand Method creates Todo Tasks.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     * @param commandSuffix is the additional String that accompanies two-step commands
     * @throws MissingDetailsException when unfilled secondary input is caught (empty commandSuffix)
     */
    public static void todoCommand(TaskList taskList, String commandSuffix) throws MissingDetailsException {
        try {
            taskList.add(new Todo(false, taskList.size(), commandSuffix));
            Ui.print("Got it. I've added this task:\n\t[T][✗] "
                + commandSuffix + "\nNow you have " + taskList.size() + " task(s) in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingDetailsException();
        }
    }
}
