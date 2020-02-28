/**
 * The Parser class handles all methods relating to parsing and processing commands from user input. It internally
 * contains a set of available commands available for the user to use.
 */

package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    static final LocalDate TODAY = LocalDate.now();
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
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


    public static String[] parseNewTask(String[] command) {
        String type = command[1];
        String[] parameters = type.split(" ", 2);
        String[] description;

        if (parameters[1].contains("/at") || parameters[1].contains("/by")) {
            description = parameters[1].split("/at|/by");
        } else {
            description = parameters;
        }
        return description;
    }

    /**
     * Processes the user command, and routes the command to their respective method for execution.
     * @param command the String array representation of the user input.
     * @param taskLists a master list of TaskLists.
     * @return a function call to their respective method for processing the function, or an exit message if the
     *         command was 'exit', or a message indicating that the command is not found otherwise.
     */
    public static String processCommand(String[] command, TaskList[] taskLists) {
        switch (command[0]) {
            case "todo":
                return todoCommand(command, taskLists);

            case "deadline":
                return deadlineCommand(command, taskLists);

            case "event":
                return eventCommand(command, taskLists);

            case "list":
                return listCommand(command, taskLists);

            case "done":
                return doneCommand(command, taskLists);

            case "delete":
                return deleteCommand(command, taskLists);

            case "find":
                return findCommand(command, taskLists);

            case "update":
                return updateCommand(command, taskLists);

            case "sort":
                return sortCommand(command, taskLists);

            case "upcoming":
                return upcomingCommand(command, taskLists);

            case "view":
                return viewCommand(command, taskLists);

            case "help":
                return helpCommand();

            case "save":
                return saveCommand(taskLists);

            default:
                return Ui.COMMAND_NOT_FOUND;
        }
    }

    /**
     * Creates a new Todo object if the input line is valid, and adds into the master TaskList object.
     * @param command the String array representing the parsed input.
     * @param taskLists an array of TaskLists.
     * @return a function call to create a new Todo object, or an error if the input line is invalid.
     */
    public static String todoCommand(String[] command, TaskList[] taskLists) {
        assert command[0].equals("todo");

        if (command.length <= 1) {
            return Ui.TASK_NEEDS_NAME;
        }

        try {
            String result = taskLists[0].newTodo(false, command[1]);
            Storage.save(taskLists);
            return result;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Creates a new Deadline object if the input line is valid, and adds into the master TaskList object.
     * @param command the String array representing the parsed input.
     * @param taskLists an array of TaskLists.
     * @return a function call to create a new Deadline object, or an error if the input line is invalid.
     */
    public static String deadlineCommand(String[] command, TaskList[] taskLists) {
        assert command[0].equals("deadline");

        if (command.length <= 2) {
            return (command.length == 2)
                    ? Ui.TASK_NEEDS_DATE_TIME
                    : Ui.TASK_NEEDS_NAME;
        }

        try {
            LocalDateTime taskDateTime = parseDateTime(command[2]);
            String result = taskLists[1].newDeadline(false, command[1], taskDateTime);
            Storage.save(taskLists);
            return result;
        } catch (DateTimeParseException e) {
            return Ui.WRONG_DATE_TIME_FORMAT;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Creates a new Event object if the input line is valid, and adds into the master TaskList object.
     * @param command the String array representing the parsed input.
     * @param taskLists an array of TaskLists.
     * @return a function call to create a new Event object, or an error if the input line is invalid.
     */
    public static String eventCommand(String[] command, TaskList[] taskLists) {
        assert command[0].equals("event");

        if (command.length <= 2) {
            return (command.length == 2)
                    ? Ui.TASK_NEEDS_DATE_TIME
                    : Ui.TASK_NEEDS_NAME;
        }

        try {
            LocalDateTime taskDateTime = parseDateTime(command[2]);
            String result = taskLists[2].newEvent(false, command[1], taskDateTime);
            Storage.save(taskLists);
            return result;
        } catch (DateTimeParseException e) {
            return Ui.WRONG_DATE_TIME_FORMAT;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

    /**
     * Lists the number of Tasks present in any TaskList.
     * @param taskLists an array of TaskLists.
     * @return a message displaying the number of tasks in the list.
     */
    public static String listCommand(String[] command, TaskList[] taskLists) {
        assert command[0].equals("list");
        if (command.length <= 1) {
            return Ui.NOT_ENOUGH_PARAMETERS;
        }

        TaskList thisList;
        switch (command[1]) {
            case "todo":
                thisList = taskLists[0];
                break;
            case "deadline":
                thisList = taskLists[1];
                break;
            case "event":
                thisList = taskLists[2];
                break;
            case "all":
                thisList = consolidateList(taskLists);
                break;
            default:
                return Ui.COMMAND_NOT_FOUND;
        }

        return thisList.isEmpty()
                ? Ui.NO_TASK_IN_LIST
                : (Ui.DISPLAY_TASK_LIST + "\n" + thisList);
    }

    /**
     * Marks a Task at a specified index (relative to the list's output) as done.
     * @param command the String array representing the parsed input.
     * @param taskLists an array of TaskLists.
     * @return a message displaying whether this command is successful.
     */
    public static String doneCommand(String[] command, TaskList[] taskLists) {
        assert command[0].equals("done");

        if (command.length <= 1) {
            return Ui.NO_FIELD_TO_UPDATE;
        }
        String[] parameters = command[1].split(" ", 2);

        try {
            int taskID = Integer.parseInt(parameters[1]);
            String result;
            TaskList thisList;

            switch (parameters[0]) {
                case "todo":
                    thisList = taskLists[0];
                    break;
                case "deadline":
                    thisList = taskLists[1];
                    break;
                case "event":
                    thisList = taskLists[2];
                    break;
                default:
                    return Ui.INVALID_FIELD;
            }

            result = thisList.markDone(taskID);
            Storage.save(taskLists);
            return result;
        } catch (IndexOutOfBoundsException e) {
            return Ui.NO_TASK_FOUND;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }

    }

    /**
     * Deletes a Task at a specified index (relative to the list's output).
     * @param command the String array representing the parsed input.
     * @param taskLists an array of TaskLists.
     * @return a message displaying whether this command is successful.
     */
    public static String deleteCommand(String[] command, TaskList[] taskLists) {
        assert command[0].equals("delete");

        if (command.length <= 1) {
            return Ui.NO_FIELD_TO_UPDATE;
        }
        String[] parameters = command[1].split(" ", 2);

        try {
            int taskID = Integer.parseInt(parameters[1]);
            String result;
            TaskList thisList;
            switch (parameters[0]) {
                case "todo":
                    thisList = taskLists[0];
                    break;
                case "deadline":
                    thisList = taskLists[1];
                    break;
                case "event":
                    thisList = taskLists[2];
                    break;
                default:
                    return Ui.INVALID_FIELD;
            }
            result = thisList.deleteTask(taskID);
            Storage.save(taskLists);
            return result;

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
     * @param taskLists an array of TaskLists.
     * @return the new TaskList containing a filtered list of tasks.
     */
    public static String findCommand(String[] command, TaskList[] taskLists) {
        assert command[0].equals("find");

        if (command.length <= 1) {
            return Ui.NOT_ENOUGH_PARAMETERS;
        }

        TaskList query = new TaskList();

        for (TaskList thisList : taskLists) {
            for (Task thisTask : thisList.getList()) {
                boolean taskContainsKeyword = thisTask.getTaskName()
                        .toLowerCase()
                        .contains(command[1].toLowerCase());
                if (taskContainsKeyword) {
                    query.add(thisTask);
                }
            }
        }

        return query.isEmpty()
                ? Ui.NO_MATCHING_TASK_IN_LIST
                : (Ui.DISPLAY_MATCHING_TASK_LIST + "\n" + query);
    }

    /**
     * Updates the Task in the specified index of the TaskList
     * @param command the String array representing the parsed input.
     * @param taskLists an array of TaskLists.
     * @return a message displaying whether this command is successful.
     */
    public static String updateCommand(String[] command, TaskList[] taskLists) {
        assert command[0].equals("update");

        if (command.length <= 1) {
            return Ui.NO_FIELD_TO_UPDATE;
        }

        try {
            String[] parameters = command[1].split(" ", 4);

            if (parameters.length < 4) {
                return Ui.NO_FIELD_TO_UPDATE;
            }

            TaskList thisList;
            switch (parameters[0]) {
                case "todo":
                    thisList = taskLists[0];
                    break;
                case "deadline":
                    thisList = taskLists[1];
                    break;
                case "event":
                    thisList = taskLists[2];
                    break;
                default:
                    return Ui.INVALID_FIELD;
            }
            int taskID = Integer.parseInt(parameters[1]);
            String fieldType = parameters[2];
            String fieldDescription = parameters[3];
            String result = thisList.updateTask(taskID, fieldType, fieldDescription);
            Storage.save(taskLists);
            return result;


        } catch (IndexOutOfBoundsException e) {
            return Ui.NO_TASK_FOUND;
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }


    /**
     * Sorts a TaskList by name or date, in its natural order
     * @param command the String array representing the parsed input.
     * @param taskLists an array of TaskLists.
     * @return a message displaying whether this command is successful.
     */

    public static String sortCommand(String[] command, TaskList[] taskLists) {
        if (command.length <= 1) {
            return Ui.NO_FIELD_TO_UPDATE;
        }
        String[] parameters = command[1].split(" ", 2);
        if (parameters.length <= 1) {
            return Ui.NOT_ENOUGH_PARAMETERS;
        }
        boolean sortingTodoByDateTime = parameters[0].equals("todo") && parameters[1].equals("date");
        if (sortingTodoByDateTime) {
            return Ui.CANNOT_SORT_TODO_BY_DATE_TIME;
        }

        TaskList thisList;
        switch (parameters[0]) {
            case "todo":
                thisList = taskLists[0];
                break;
            case "deadline":
                thisList = taskLists[1];
                break;
            case "event":
                thisList = taskLists[2];
                break;
            default:
                return Ui.INVALID_FIELD;
        }

        String result;
        switch (parameters[1]) {
            case "name":
                thisList.sortName();
                result = Ui.SORTED_BY_NAME;
                break;
            case "date":
                thisList.sortDateTime();
                result = Ui.SORTED_BY_DATE_TIME;
                break;
            default:
                return Ui.INVALID_FIELD;
        }

        try {
            Storage.save(taskLists);
        } catch (IOException e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
        return result;
    }


    /**
     * Views a range of Tasks, sorted by time, over a specified number of days.
     * @param command the String array representing the parsed input.
     * @param taskLists an array of TaskLists.
     * @return a list of Event and Deadline objects occurring the specified number of days.
     */
    public static String upcomingCommand(String[] command, TaskList[] taskLists) {
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

            for (TaskList thisList : taskLists) {
                for (Task task : thisList.getList()) {
                    if (taskIsInDateRange(task, TODAY, task.getTaskDate(), dayRangeUntil)) {
                        populate(task, upcomingEvents, upcomingDeadlines);
                    }
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
     * @param taskLists an array of TaskLists.
     * @return a list of Event and Deadline objects occurring on the specified day.
     */
    public static String viewCommand(String[] command, TaskList[] taskLists) {
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

            for (TaskList thisList : taskLists) {
                for (Task task : thisList.getList()) {
                    if (taskIsInDateRange(task, TODAY, task.getTaskDate(), dayRangeUntil)) {
                        populate(task, upcomingEvents, upcomingDeadlines);
                    }
                }
            }

            return Ui.displayViewDay(targetDate.format(DATE_FORMATTER), upcomingDeadlines, upcomingEvents);

        } catch (DateTimeParseException e) {
            return Ui.WRONG_DATE_TIME_FORMAT;
        }

    }

    /**
     * Gives a link to the main page for help.
     * @return
     */
    public static String helpCommand() {
        return "View the user guide here:\nhttps://lsjxavier.github.io/duke/";
    }

    /**
     * Saves the master TaskList, at its current state when the command was called, into the data file.
     * @param taskLists an array of TaskLists.
     * @return a message if the changes are successfully saved.
     */
    public static String saveCommand(TaskList[] taskLists) {
        try {
            Storage.save(taskLists);
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
            upcomingEvents.add(task);
        } else if (task instanceof Deadline && !task.isDone()) {
            upcomingDeadlines.add(task);
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

    /**
     * Consolidates the array of TaskLists into a single TaskList
     * @param taskLists a list of TaskLists.
     * @return a TaskList containing every task from the array of TaskLists.
     */
    public static TaskList consolidateList(TaskList[] taskLists) {
        TaskList master = new TaskList();
        for (TaskList thisList : taskLists) {
            master.addAll(thisList);
        }
        return master;
    }
}
