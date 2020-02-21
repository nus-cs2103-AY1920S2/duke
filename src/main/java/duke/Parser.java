/**
 * The Parser class handles all methods relating to parsing and processing commands from user input. It internally
 * contains a set of available commands available for the user to use.
 */

package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.HashSet;

public class Parser {
    static final HashSet<String> commandList = new HashSet<String>(
            List.of("todo", "event", "deadline",
                    "list", "done", "delete",
                    "find", "update", "upcoming",
                    "view", "save", "exit"));
    static final LocalDate TODAY = LocalDate.now();

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
        } else {
            String[] s = input.split(" ", 2);
            if (!s[1].contains("/at") && !s[1].contains("/by")) {
                return s;
            } else {
                String[] temp = s[1].split("/at|/by");
                return new String[] {s[0].strip(), temp[0].strip(), temp[1].strip()};
            }
        }
    }

    /**
     * Processes the user command, and routes the command to their respective method for execution.
     * @param command the String array representation of the user input.
     * @param tasklist the master TaskList object.
     * @return a function call to their respective method for processing the function, or an exit message if the
     *         command was 'exit', or a message indicating that the command is not found otherwise.
     */
    public static String processCommand(String[] command, TaskList tasklist) {
        if (commandList.contains(command[0])) {
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
        } else {
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
        if (command.length > 1) {
            try {
                return tasklist.newTodo(false, command[1]);
            } catch (IOException e) {
                return "Oops! Unable to write to file due to " + e + "!";
            }
        } else {
            return Ui.TASK_NEEDS_NAME;
        }
    }

    /**
     * Creates a new Event object if the input line is valid, and adds into the master TaskList object.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a function call to create a new Event object, or an error if the input line is invalid.
     */
    public static String eventCommand(String[] command, TaskList tasklist) {
        if (command.length > 2) {
            try {
                return tasklist.newEvent(false, command[1], command[2]);
            } catch (DateTimeParseException e) {
                return Ui.WRONG_DATE_TIME_FORMAT;
            } catch (IOException e) {
                return "Oops! Unable to write to file due to " + e + "!";
            }

        } else {
            return (command.length == 2) ? Ui.TASK_NEEDS_DATE_TIME : Ui.TASK_NEEDS_NAME;
        }
    }

    /**
     * Creates a new Deadline object if the input line is valid, and adds into the master TaskList object.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a function call to create a new Deadline object, or an error if the input line is invalid.
     */
    public static String deadlineCommand(String[] command, TaskList tasklist) {
        if (command.length > 2) {
            try {
                return tasklist.newDeadline(false, command[1], command[2]);
            } catch (DateTimeParseException e) {
                return Ui.WRONG_DATE_TIME_FORMAT;
            } catch (IOException e) {
                return "Oops! Unable to write to file due to " + e + "!";
            }
        } else {
            return (command.length == 2) ? Ui.TASK_NEEDS_DATE_TIME : Ui.TASK_NEEDS_NAME;
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
        if (command.length > 1 && !command[1].equals("all")) {
            TaskList query = new TaskList();
            for (Task t : tasklist.getList()) {
                if (command[1].equals("todo") && t instanceof Todo) {
                    query.tempAdd(t);
                } else if (command[1].equals("deadline") && t instanceof Deadline) {
                    query.tempAdd(t);
                } else if (command[1].equals("event") && t instanceof Event) {
                    query.tempAdd(t);
                }
            }
            return query.isEmpty() ? Ui.NO_MATCHING_TASK_IN_LIST : (Ui.DISPLAY_MATCHING_TASK_LIST + "\n" + query);
        }

        return tasklist.isEmpty() ? Ui.NO_TASK_IN_LIST : (Ui.DISPLAY_TASK_LIST + "\n" + tasklist);
    }

    /**
     * Marks a Task at a specified index (relative to the list's output) as done.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a message displaying whether this command is successful.
     */
    public static String doneCommand(String[] command, TaskList tasklist) {
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
        TaskList query = new TaskList();
        for (Task thisTask : tasklist.getList()) {
            if (command.length > 1 && thisTask.getTaskName().toLowerCase().contains(command[1].toLowerCase())) {
                query.tempAdd(thisTask);
            }
        }
        return query.isEmpty() ? Ui.NO_MATCHING_TASK_IN_LIST : (Ui.DISPLAY_MATCHING_TASK_LIST + "\n" + query);
    }

    /**
     * Updates the Task in the specified index of the TaskList
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a message displaying whether this command is successful.
     */
    public static String updateCommand(String[] command, TaskList tasklist) {
        if (command.length > 1) {
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
        } else {
            return Ui.NO_FIELD_TO_UPDATE;
        }
    }

    /**
     * Views a range of Tasks, sorted by time, over a specified number of days.
     * @param command the String array representing the parsed input.
     * @param tasklist the master TaskList object.
     * @return a list of Event and Deadline objects occurring the specified number of days.
     */
    public static String upcomingCommand(String[] command, TaskList tasklist) {
        if (command[1].equals("today")) {
            command[1] = "1";
        } else if (command[1].equals("tomorrow")) {
            command[1] = "2";
        }

        try {
            TaskList upcomingEvents = new TaskList();
            TaskList upcomingDeadlines = new TaskList();
            int dayRangeUntil = Integer.parseInt(command[1]);

            for (Task task : tasklist.getList()) {
                if (taskDateIsInRange(task, dayRangeUntil) && !(task instanceof Todo)) {
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
        try {
            LocalDate targetDate;

            if (command[1].equals("today")) {
                targetDate = TODAY;
            } else if (command[1].equals("tomorrow")) {
                targetDate = TODAY.plusDays(1);
            } else {
                targetDate = LocalDate.parse(command[1], Task.DATE_FORMATTER);
            }

            TaskList upcomingEvents = new TaskList();
            TaskList upcomingDeadlines = new TaskList();

            for (Task task : tasklist.getList()) {
                if (task.getTaskDate().equals(targetDate) && !(task instanceof Todo)) {
                    populate(task, upcomingEvents, upcomingDeadlines);
                }
            }

            return Ui.displayUpcomingDay(targetDate.format(Task.DATE_FORMATTER), upcomingDeadlines, upcomingEvents);

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


    public static boolean taskDateIsInRange(Task t, int dayRangeUntil) {
        return !t.getTaskDate().isBefore(TODAY) && t.getTaskDate().compareTo(TODAY) <= dayRangeUntil;
    }

    public static void populate(Task task, TaskList upcomingEvents, TaskList upcomingDeadlines) {
        if (task instanceof Event) {
            upcomingEvents.tempAdd(task);
        } else if (task instanceof Deadline && !task.isDone()) {
            upcomingDeadlines.tempAdd(task);
        }
    }

}
