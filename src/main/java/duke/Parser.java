/**
 * The Parser class handles all methods relating to parsing and processing commands from user input. It internally
 * contains a set of available commands available for the user to use.
 */

package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    static final LocalDate TODAY = LocalDate.now();
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Analyzes the user input and splits them into a String array:
     * - Index 0 contains the action that the program should take.
     * - Index 1 contains the argument tagged to the action, if applicable.
     * - Index 2 contains the date/time tagged to the action, if applicable.
     * @param input the original user input
     * @return a String array containing a segmentized version of the user input for processing.
     */
    public static String[] parseInput(String input) {
        if (!input.contains(" ")) {
            return new String[] {input};
        }

        String[] s = input.split(" ", 2);
        if (!s[1].contains("/at") && !s[1].contains("/by")) {
            return s;
        } else {
            String[] temp = s[1].split("/at|/by");
            return new String[] {s[0].strip(), temp[0].strip(), temp[1].strip()};
        }
    }

    /**
     * Parses the date and time given. If no time is given, a default time of 2359 is used,
     * @param taskDateTime the given date and/or time of the Task.
     * @return a LocalDateTime containing the date and time of the Task.
     * @throws DateTimeParseException if the date or time provided is in the wrong format.
     */
    public static LocalDateTime parseDateTime(String taskDateTime) throws DateTimeParseException {
        String[] dateTime = taskDateTime.split(" ");
        LocalDate taskDate = LocalDate.parse(dateTime[0], DATE_FORMATTER);
        if (dateTime.length > 1) {
            LocalTime taskTime = LocalTime.parse(dateTime[1], TIME_FORMATTER);
            return taskDate.atTime(taskTime);
        }
        return taskDate.atTime(23,59);

    }

    /**
     * Processes the user command, and routes the command to their respective method for execution.
     * @param command the String array representation of the user input.
     * @param tasklist the master TaskList object.
     * @return a function call to their respective method for processing the function, or an exit message if the
     *         command was 'exit', or a message indicating that the command is not found otherwise.
     */
    public static String processCommand(String[] command, TaskList tasklist) {
        switch (command[0]) {
        case "todo":
            return todoCommand(command, tasklist);

        case "event":
            return eventCommand(command, tasklist);

        case "deadline":
            return deadlineCommand(command, tasklist);

        case "list":
            return listCommand(command, tasklist);

        case "done":
            return doneCommand(command, tasklist);

        case "delete":
            return deleteCommand(command, tasklist);

        case "find":
            return findCommand(command, tasklist);

        case "update":
            return updateCommand(command, tasklist);

        case "upcoming":
            return upcomingCommand(command, tasklist);

        case "view":
            return viewCommand(command, tasklist);

        case "save":
            return saveCommand(tasklist);

        case "exit":
            return command[0];
        default:
            return Ui.COMMAND_NOT_FOUND;
        }
    }

    /**
     * Creates a new Todo object if the input line is valid, and adds into the master TaskList object.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a function call to create a new Todo object, or an error if the input line is invalid.
     */
    public static String todoCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("todo");

        if (command.length <= 1) {
            return Ui.TASK_NEEDS_NAME;
        }

        try {
            return tasklist.newTodo(false, command[1]);
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Creates a new Event object if the input line is valid, and adds into the master TaskList object.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a function call to create a new Event object, or an error if the input line is invalid.
     */
    public static String eventCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("event");

        if (command.length <= 2) {
            return (command.length == 2)
                    ? Ui.TASK_NEEDS_DATE_TIME
                    : Ui.TASK_NEEDS_NAME;
        }

        try {
            LocalDateTime taskDateTime = parseDateTime(command[2]);
            return tasklist.newEvent(false, command[1], taskDateTime);
        } catch (DateTimeParseException e) {
            return Ui.WRONG_DATE_TIME_FORMAT;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Creates a new Deadline object if the input line is valid, and adds into the master TaskList object.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a function call to create a new Deadline object, or an error if the input line is invalid.
     */
    public static String deadlineCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("deadline");

        if (command.length <= 2) {
            return (command.length == 2)
                    ? Ui.TASK_NEEDS_DATE_TIME
                    : Ui.TASK_NEEDS_NAME;
        }

        try {
            LocalDateTime taskDateTime = parseDateTime(command[2]);
            return tasklist.newDeadline(false, command[1], taskDateTime);
        } catch (DateTimeParseException e) {
            return Ui.WRONG_DATE_TIME_FORMAT;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Lists the number of Tasks present in the TaskList, with either 0 or 1 argument.
     * If no argument is present or the argument is "all", displays all available Tasks.
     * If the argument is either "todo", "deadline" or "event", displays only Todo, Deadline,
     * or Event Tasks respectively.
     * @param tasklist the target TaskList.
     * @return a message displaying the number of tasks in the list.
     */
    public static String listCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("list");

        boolean listAll = command.length == 1 || command[1].equals("all");
        if (listAll) {
            return tasklist.isEmpty()
                    ? Ui.NO_TASK_IN_LIST
                    : (Ui.DISPLAY_TASK_LIST + "\n" + tasklist);
        }

        boolean listTodo = command[1].equals("todo");
        boolean listDeadline = command[1].equals("deadline");
        boolean listEvent = command[1].equals("event");

        TaskList query = new TaskList();
        for (Task t : tasklist.getList()) {
            if (listTodo && t instanceof Todo) {
                query.tempAdd(t);
            } else if (listDeadline && t instanceof Deadline) {
                query.tempAdd(t);
            } else if (listEvent && t instanceof Event) {
                query.tempAdd(t);
            }
        }
        return query.isEmpty()
                ? Ui.NO_MATCHING_TASK_IN_LIST
                : (Ui.DISPLAY_MATCHING_TASK_LIST + "\n" + query);
    }

    /**
     * Marks a Task at a specified index (relative to the list's output) as done.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a message displaying whether this command is successful.
     */
    public static String doneCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("done");

        try {
            int taskID = Integer.parseInt(command[1]);
            return tasklist.markDone(taskID);
        } catch (IndexOutOfBoundsException e) {
            return Ui.NO_TASK_FOUND;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Deletes a Task at a specified index (relative to the list's output).
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a message displaying whether this command is successful.
     */
    public static String deleteCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("delete");

        try {
            int taskID = Integer.parseInt(command[1]);
            return tasklist.deleteTask(taskID);
        } catch (IndexOutOfBoundsException e) {
            return Ui.NO_TASK_FOUND;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Creates a new temporary TaskList containing any Task with the same name as specified in the user input. If
     * no tasks in the master TaskList matches the input query, returns a message that no tasks matches the query.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return the new TaskList containing a filtered list of tasks.
     */
    public static String findCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("find");

        if (command.length <= 1) {
            return Ui.INVALID_FIELD;
        }

        TaskList query = new TaskList();
        for (Task thisTask : tasklist.getList()) {
            boolean taskContainsKeyword = thisTask.getTaskName()
                    .toLowerCase()
                    .contains(command[1].toLowerCase());

            if (taskContainsKeyword) {
                query.tempAdd(thisTask);
            }
        }
        return query.isEmpty()
                ? Ui.NO_MATCHING_TASK_IN_LIST
                : (Ui.DISPLAY_MATCHING_TASK_LIST + "\n" + query);
    }

    /**
     * Updates the Task in the specified index of the TaskList
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a message displaying whether this command is successful.
     */
    public static String updateCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("update");

        if (command.length <= 1) {
            return Ui.NO_FIELD_TO_UPDATE;
        }

        try {
            String[] s = command[1].split(" ", 2);
            if (s.length < 2) {
                return Ui.NO_FIELD_TO_UPDATE;
            }
            int taskID = Integer.parseInt(s[0]);
            return tasklist.updateTask(taskID, s[1]);
        } catch (IndexOutOfBoundsException e) {
            return Ui.NO_TASK_FOUND;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Views a range of Tasks, sorted by time, over a specified number of days.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a list of Event and Deadline objects occurring the specified number of days.
     */
    public static String upcomingCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("upcoming");

        if (command.length <= 1) {
            return Ui.NEED_TO_SPECIFY_PERIOD;
        }

        if (command[1].equals("today")) {
            command[1] = "0";
        } else if (command[1].equals("tomorrow")) {
            command[1] = "1";
        }

        try {
            TaskList upcomingEvents = new TaskList();
            TaskList upcomingDeadlines = new TaskList();
            int dayRangeUntil = Integer.parseInt(command[1]);

            for (Task task : tasklist.getList()) {
                if (taskIsInDateRange(task, TODAY, task.getTaskDate(), dayRangeUntil)) {
                    populate(task, upcomingEvents, upcomingDeadlines);
                }
            }

            return Ui.displayUpcomingRange(dayRangeUntil, upcomingDeadlines, upcomingEvents);

        } catch (NumberFormatException e) {
            return Ui.COMMAND_NOT_FOUND;
        }
    }

    /**
     * Views the schedule of a particular date.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a list of Event and Deadline objects occurring on the specified day.
     */
    public static String viewCommand(String[] command, TaskList tasklist) {
        assert command[0].equals("view");

        if (command.length <= 1) {
            return Ui.NEED_TO_SPECIFY_DATE;
        }

        LocalDate targetDate;

        if (command[1].equals("today")) {
            targetDate = TODAY;
        } else if (command[1].equals("tomorrow")) {
            targetDate = TODAY.plusDays(1);
        } else {
            targetDate = LocalDate.parse(command[1], DATE_FORMATTER);
        }

        try {
            TaskList upcomingEvents = new TaskList();
            TaskList upcomingDeadlines = new TaskList();
            int dayRangeUntil = 0;

            for (Task task : tasklist.getList()) {
                if (taskIsInDateRange(task, targetDate, task.getTaskDate(), dayRangeUntil)) {
                    populate(task, upcomingEvents, upcomingDeadlines);
                }
            }
            return Ui.displayUpcomingDay(targetDate.format(DATE_FORMATTER), upcomingDeadlines, upcomingEvents);

        } catch (DateTimeParseException e) {
            return Ui.WRONG_DATE_TIME_FORMAT;
        }

    }

    /**
     * Saves the master TaskList, at its current state when the command was called, into the data file.
     * @param tasklist the master TaskList object to save.
     * @return a message if the changes are successfully saved.
     */
    public static String saveCommand(TaskList tasklist) {
        try {
            Storage.save(tasklist);
            return Ui.CHANGES_SAVED;
        } catch (Exception e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Populates a Event or Deadline TaskList with a Task. To be used when the upcoming or view commands are called.
     * @param task The task to add.
     * @param upcomingEvents A list of Events with the specified Date in the upcoming or view command.
     * @param upcomingDeadlines A list of Deadlines with the specified Date in the upcoming or view command.
     */
    public static void populate(Task task, TaskList upcomingEvents, TaskList upcomingDeadlines) {
        if (task instanceof Event) {
            upcomingEvents.tempAdd(task);
        } else if (task instanceof Deadline && !task.isDone()) {
            upcomingDeadlines.tempAdd(task);
        }
    }

    /**
     * Checks if a date of a task is within a specified range by first calculating the difference between its date
     * and a specified start date, and returns true if it is and false if it is not.
     *
     * If the Task is an instance of Todo, this method will always return false.
     * @param task The Task to check if its date is within range.
     * @param startDate The start date to compare to. In the 'upcoming' command, this will always be default to
     *                  the current system date. In the 'view' command, this will always default to the queried
     *                  date by the user.
     * @param endDate The date of the Task.
     * @param dayRangeUntil The number of days specified to determine the range.
     * @return True if the difference of endDate and startDate is between 0 and dayRangeUntil, both inclusive.
     *         False if it falls outside the specified range, or if the Task is an instance of Todo.
     */
    public static boolean taskIsInDateRange(Task task, LocalDate startDate, LocalDate endDate, int dayRangeUntil) {
        if (!(task instanceof Todo)) {
            long dateDifference = ChronoUnit.DAYS.between(startDate, endDate);
            return dateDifference >= 0 && dateDifference <= dayRangeUntil;
        } else {
            return false;
        }
    }





}
