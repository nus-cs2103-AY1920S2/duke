import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class Parser {
    public static List<String> commandList = new ArrayList<String>(
            List.of("todo", "event", "deadline",
                    "list", "done", "delete",
                    "save", "exit"));

    public static String commandNotFound = "Oops! Command not found!";
    public static String taskNeedsName = "Oops! This task needs a name!";
    public static String taskNeedsDateTime = "Oops! This task needs a date/time!";
    public static String wrongDateTimeFormat = "Oops! The date/time needs to be DD/MM/YYYY HHMM (24hr) format!";
    public static String noTaskFound = "Oops! Task not found!";
    public static String noTaskInList = "Yay! There are no tasks in your list!";
    public static String displayTaskList = "Here are your tasks in your list:";
    public static String changesSaved = "Changes saved!";


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

    public static String processCommand(String[] command, TaskList tasklist) {
        if (commandList.contains(command[0])) {
            switch (command[0]) {

                case "todo":
                    if (command.length > 1) {
                        return tasklist.newTodo('T', false, command[1]);
                    } else {
                        return taskNeedsName;
                    }

                case "event":
                    if (command.length > 2) {
                        try {
                            return tasklist.newEvent('E', false, command[1], command[2]);
                        } catch (DateTimeParseException e) {
                            return wrongDateTimeFormat;
                        }

                    } else if (command.length == 2) {
                        return taskNeedsDateTime;
                    } else {
                        return taskNeedsName;
                    }

                case "deadline":
                    if (command.length > 2) {
                        try {
                            return tasklist.newDeadline('D', false, command[1], command[2]);
                        } catch (DateTimeParseException e) {
                            return wrongDateTimeFormat;
                        }
                    } else if (command.length == 2) {
                        return taskNeedsDateTime;
                    } else {
                        return taskNeedsName;
                    }

                case "list":
                    if (tasklist.getSize() == 0) {
                        return noTaskInList;
                    } else {
                        return displayTaskList + "\n" + tasklist;
                    }

                case "done":
                    try {
                        int taskID = Integer.parseInt(command[1]);
                        return tasklist.markDone(taskID);
                    } catch (IndexOutOfBoundsException e) {
                        return noTaskFound;
                    }

                case "delete":
                    try {
                        int taskID = Integer.parseInt(command[1]);
                        return tasklist.deleteTask(taskID);
                    } catch (IndexOutOfBoundsException e) {
                        return noTaskFound;
                    }

                case "save":
                    Storage.save(tasklist);
                    return changesSaved;

                case "exit":
                    return command[0];
                default:
                    return commandNotFound;
            }
        } else {
            return commandNotFound;
        }
    }

}
