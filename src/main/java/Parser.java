/**
 * The Parser class handles all methods relating to parsing and processing commands from user input. It internally
 * contains a set of available commands available for the user to use.
 */

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.HashSet;

public class Parser {
    static HashSet<String> commandList = new HashSet<String>(
            List.of("todo", "event", "deadline", "list", "done", "delete", "find", "save", "exit"));


    /**
     * Analyzes the user input and splits them into a String array:
     * - Index 0 contains the action that the program should take.
     * - Index 1 contains the message tagged to the program, if applicable.
     * - Index 2 contains the date/time tagged to the program, if applicable.
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
                return listCommand(tasklist);

            case "done":
                return doneCommand(command, tasklist);

            case "delete":
                return deleteCommand(command, tasklist);

            case "find":
                return findCommand(command, tasklist);

            case "save":
                return saveCommand(tasklist);

            case "exit":
                return command[0];
            default:
                return Ui.commandNotFound;
            }
        } else {
            return Ui.commandNotFound;
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
            return tasklist.newTodo(false, command[1]);
        } else {
            return Ui.taskNeedsName;
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
                return Ui.wrongDateTimeFormat;
            }

        } else if (command.length == 2) {
            return Ui.taskNeedsDateTime;
        } else {
            return Ui.taskNeedsName;
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
                return Ui.wrongDateTimeFormat;
            }
        } else if (command.length == 2) {
            return Ui.taskNeedsDateTime;
        } else {
            return Ui.taskNeedsName;
        }
    }

    /**
     * Lists the number of Tasks present in the TaskList.
     * @param tasklist the target TaskList.
     * @return a message displaying the number of tasks in the list.
     */
    public static String listCommand(TaskList tasklist) {
        if (tasklist.getSize() == 0) {
            return Ui.noTaskInList;
        } else {
            return Ui.displayTaskList + "\n" + tasklist;
        }
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
            return Ui.noTaskFound;
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
            return Ui.noTaskFound;
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
            if (thisTask.getTaskName().toLowerCase().contains(command[1].toLowerCase())) {
                query.add(thisTask);
            }
        }
        if (query.getList().isEmpty()) {
            return Ui.noMatchingTaskInList;
        } else {
            return Ui.displayMatchingTaskList + "\n" + query;
        }
    }

    /**
     * Saves the master TaskList, at its current state when the command was called, into a save file.
     * @param tasklist the master TaskList object to save.
     * @return a message if the changes are successfully saved.
     */
    public static String saveCommand(TaskList tasklist) {
        try {
            Storage.save(tasklist);
            return Ui.changesSaved;
        } catch (Exception e) {
            return "Oops! Unable to write to file due to " + e + "!";
        }
    }

}
