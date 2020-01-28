import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.HashSet;

public class Parser {
    static HashSet<String> commandList = new HashSet<String>(
            List.of("todo", "event", "deadline", "list", "done", "delete", "find", "save", "exit"));

    static String commandNotFound = "Oops! Command not found!";
    static String taskNeedsName = "Oops! This task needs a name!";
    static String taskNeedsDateTime = "Oops! This task needs a date/time!";
    static String wrongDateTimeFormat = "Oops! The date/time needs to be DD/MM/YYYY HHMM (24hr) format!";
    static String noTaskFound = "Oops! Task not found in the list!";
    static String noTaskInList = "Yay! There are no tasks in your list!";
    static String displayTaskList = "Here are your tasks in your list:";
    static String displayMatchingTaskList = "Here are your tasks that matched your query:";
    static String changesSaved = "Changes saved!";


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
                return commandNotFound;
            }
        } else {
            return commandNotFound;
        }
    }

    public static String todoCommand(String[] command, TaskList tasklist) {
        if (command.length > 1) {
            return tasklist.newTodo('T', false, command[1]);
        } else {
            return taskNeedsName;
        }
    }

    public static String eventCommand(String[] command, TaskList tasklist) {
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
    }

    public static String deadlineCommand(String[] command, TaskList tasklist) {
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
    }

    public static String listCommand(TaskList tasklist) {
        if (tasklist.getSize() == 0) {
            return noTaskInList;
        } else {
            return displayTaskList + "\n" + tasklist;
        }
    }

    public static String doneCommand(String[] command, TaskList tasklist) {
        try {
            int taskID = Integer.parseInt(command[1]);
            return tasklist.markDone(taskID);
        } catch (IndexOutOfBoundsException e) {
            return noTaskFound;
        }
    }

    public static String deleteCommand(String[] command, TaskList tasklist) {
        try {
            int taskID = Integer.parseInt(command[1]);
            return tasklist.deleteTask(taskID);
        } catch (IndexOutOfBoundsException e) {
            return noTaskFound;
        }
    }

    public static String findCommand(String[] command, TaskList tasklist) {
        TaskList query = new TaskList();
        for (Task thisTask : tasklist.list) {
            if (thisTask.getTaskName().toLowerCase().contains(command[1].toLowerCase())) {
                query.add(thisTask);
            }
        }
        if (query.list.isEmpty()) {
            return noTaskFound;
        } else {
            return displayMatchingTaskList + "\n" + query;
        }
    }

    public static String saveCommand(TaskList tasklist) {
        Storage.save(tasklist);
        return changesSaved;
    }

}
