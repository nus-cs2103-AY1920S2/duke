package duke.command;

import duke.exception.*;
import duke.main.Storage;
import duke.main.UI;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Command {
    //Custom byeCommand Method to exit Duke
    public static boolean byeCommand(List<Task> taskList) throws CannotSaveFileException {
        Storage.dataSave(taskList);
        UI.print("Bye. Hope to see you again soon!");
        return false;
    }

    //Custom calendarCommand Method to run Calendar Command
    public static void calendarCommand(List<Task> taskList, String commandSuffix) throws UnknownDateTimeException {
        try {
            DateTimeFormatter DTF = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate calendarDate = LocalDate.parse(commandSuffix, DTF);
            calendarFind(taskList, calendarDate);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new UnknownDateTimeException();
        }
    }

    //Custom calendarFind Method to find and print the list with the horizontal borders + running index
    static void calendarFind(List<Task> tasksList, LocalDate calendarDate) {
        StringBuilder sb = new StringBuilder();
        List<String> calendarList = new ArrayList<>();

        for (Task task : tasksList) {
            if (task.getClass().equals(Deadline.class)) {
                try {
                    DateTimeFormatter DTF = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma)");
                    LocalDateTime testDate = LocalDateTime.parse(((Deadline) task).byDeadline.substring(5), DTF);
                    LocalDate taskDate = testDate.toLocalDate();

                    if (taskDate.equals(calendarDate)) {
                        calendarList.add(task.toString());
                    }
                } catch (DateTimeParseException ignored) {

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

        UI.print(sb.toString());
    }

    //Custom deadlineCommand to create Deadline Tasks
    public static void deadlineCommand(List<Task> taskList, String commandSuffix) throws DukeException {
        try {
            String[] byDeadline = commandSuffix.split(" /by ");

            try {
                //Initialising proTip; also used as a silent check for MissingByDeadlineException
                String proTip = byDeadline[1];
                boolean unknownDate = false;

                try {
                    try {
                        DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime outputDT = LocalDateTime.parse(byDeadline[1], inputDTF);
                        DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma");
                        byDeadline[1] = outputDT.format(outputDTF);
                    } catch (DateTimeParseException e) {
                        throw new UnknownDateTimeException();
                    }
                } catch (DukeException e) {
                    unknownDate = true;
                    proTip = e.toString();
                }

                taskList.add(new Deadline(false, taskList.size(), byDeadline[0], byDeadline[1]));

                String deadlineOutput = ("Got it. I've added this task:\n\t[D][✗] "
                        + byDeadline[0] + " (by: " + byDeadline[1] + ")" +
                        "\nNow you have " + taskList.size() + " task(s) in the list.");

                if (unknownDate) {
                    deadlineOutput = deadlineOutput + "\nPS: " + proTip;
                }

                UI.print(deadlineOutput);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingByDeadlineException();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingDetailsException();
        }
    }

    //Custom eventCommand Method to create Event Tasks
    public static void eventCommand(List<Task> taskList, String commandSuffix) throws DukeException {
        try {
            String[] atEvent = commandSuffix.split(" /at ");

            try {
                taskList.add(new Event(false, taskList.size(), atEvent[0], atEvent[1]));
                UI.print("Got it. I've added this task:\n\t[E][✗] "
                        + atEvent[0] + " (at: " + atEvent[1] + ")" +
                        "\nNow you have " + taskList.size() + " task(s) in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingAtEventException();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingDetailsException();
        }
    }

    //Custom listCommand Method to print the list with the horizontal borders + running index
    public static void listCommand(List<Task> tasksList) {
        StringBuilder sb = new StringBuilder();

        if (tasksList.size() == 0) {
            sb.append("List is empty.");
        } else {
            sb.append("Here are the task(s) in your list:\n");

            for (Task task : tasksList) {
                sb.append(task).append("\n");
            }
        }

        sb.setLength(sb.length() - 1);
        UI.print(sb.toString());
    }

    //Custom todoCommand to create Todo Tasks
    public static void todoCommand(List<Task> taskList, String commandSuffix) throws MissingDetailsException {
        try {
            taskList.add(new Todo(false, taskList.size(), commandSuffix));
            UI.print("Got it. I've added this task:\n\t[T][✗] "
                    + commandSuffix + "\nNow you have " + taskList.size() + " task(s) in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingDetailsException();
        }
    }
}
