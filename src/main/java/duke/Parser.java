package duke;

/**
 * The Parser Class parses commands and references TaskList and Storage Classes to store and retrieve Tasks.
 *
 * @author qiujingying
 * @version 1.0
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;
    private static String HELP_MESSAGE = "Type 'help' for the commands that I can process!";

    /**
     * Creates a Parser object to reference to TaskList and Storage objects.
     * @param tasks initial TaskList stores Tasks
     * @param storage initial storage with filepath to store data
     */
    public Parser(TaskList tasks, Storage storage) {
        this.taskList = tasks;
        this.storage = storage;
    }

    /**
     * Parses commands from command line.
     * @param command input to identify relevant Task objects to create and their description
     */
    public String record(String command) throws DukeException {
        StringBuilder sb;
        if (command.equals("list")) {
            sb = list(new StringBuilder());
        } else if (command.equals("help")) {
            sb = help();
        } else {
            try {
                int x = getIndex(command);
                switch (command.substring(0, x)) {
                case "delete":
                    sb = deleteTask(new StringBuilder(), command.substring(x + 1));
                    break;
                case "done":
                    sb = markDone(new StringBuilder(), command.substring(x + 1));
                    break;
                case "deadline":
                    sb = createDeadline(new StringBuilder(), command.substring(x + 1));
                    break;
                case "event":
                    sb = createEvent(new StringBuilder(), command.substring(x + 1));
                    break;
                case "todo":
                    sb = createToDo(new StringBuilder(), command.substring(x + 1));
                    break;
                case "find":
                    sb = findTask(new StringBuilder(), command.substring(x + 1).toLowerCase());
                    break;
                default:
                    throw new DukeException("Unexpected command: " + command);
                }
            } catch (Exception e) {
                throw new DukeException("There is an error!"
                        + "\nType 'help' to refer to commands and their formats.");
            }
        }
        storage.storeData(taskList);
        sb.append("\n\n" + HELP_MESSAGE);
        return sb.toString();
    }

    private int getIndex(String command) throws DukeException {
        int x;
        try {
            x = command.indexOf(' ');
            assert x >= 0;
        } catch (StringIndexOutOfBoundsException e) {
            switch (command) {
            case "todo":
                throw new DukeException("The description of a todo cannot be empty");
            default:
                throw new DukeException("I'm sorry but I don't know what that means :(");
            }
        }
        return x > 0 ? x : 0;
    }

    private StringBuilder list(StringBuilder sb) {
        sb.append("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append((i + 1) + "." + taskList.retrieveTask(i).toString() + "\n");
        }
        return sb;
    }

    private StringBuilder help() {
        String commandGuide = "Hello! Here are the commands that I can understand:\n"
                + "- 'list'\n"
                + "- 'delete' [index]\n"
                + "- 'done' [index]\n"
                + "- 'todo' [name of todo]\n"
                + "- 'deadline' [name of deadline] /by [YYYY-MM-DD HHMM]\n"
                + "- 'event' [name of event] /by [YYYY-MM-DD HHMM-HHMM]\n"
                + "- 'find' [name of task]";
        StringBuilder sb = new StringBuilder(commandGuide);
        return sb;
    }

    private StringBuilder deleteTask(StringBuilder sb, String position) {
        int b = Integer.valueOf(position) - 1;
        Task temp = taskList.retrieveTask(b);
        taskList.removeTask(b);
        sb.append("Noted. I've removed this task:\n" + temp.toString() + "\n");
        sb.append("Now you have " + taskList.getSize() + " tasks in the list.\n");
        return sb;
    }

    private StringBuilder markDone(StringBuilder sb, String position) {
        int y = Integer.valueOf(position) - 1;
        taskList.retrieveTask(y).markAsDone();
        sb.append("Nice! I've marked this task as done:\n" + taskList.retrieveTask(y).toString() + "\n");
        return sb;
    }

    private StringBuilder createDeadline(StringBuilder sb, String deadline) throws DukeException {
        int z = deadline.indexOf('/');
        try {
            Task newDeadline = new Deadline(deadline.substring(0, z - 1), deadline.substring(z + 4));
            taskList.addTask(newDeadline);
            sb.append(stringTask(newDeadline));
            return sb;
        } catch (DukeException e) {
            throw e;
        }
    }

    private StringBuilder createEvent(StringBuilder sb, String event) {
        int a = event.indexOf('/');
        Task newEvent = new Event(event.substring(0, a - 1), event.substring(a + 4));
        taskList.addTask(newEvent);
        sb.append(stringTask(newEvent));
        return sb;
    }

    private StringBuilder createToDo(StringBuilder sb, String toDo) {
        Task newToDo = new ToDo(toDo, "");
        taskList.addTask(newToDo);
        sb.append(stringTask(newToDo));
        return sb;
    }

    /**
     * Refactored to C-BetterSearch.
     * Able to find items even if the keyword matches the item only partially.
     * @param sb empty StringBuilder
     * @param description keywords to be found
     * @return StringBuilder with tasks that have matching keywords
     */
    private StringBuilder findTask(StringBuilder sb, String description) {
        TaskList tempList = new TaskList();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task tempTask = taskList.retrieveTask(i);
            String[] keywords = tempTask.description.split(" ");
            for (String s:keywords) {
                if (s.equals(description)) {
                    tempList.addTask(tempTask);
                    break;
                }
            }
        }
        return appendTasks(sb, tempList);
    }

    private StringBuilder appendTasks(StringBuilder sb, TaskList tp) {
        try {
            tp.retrieveTask(0);
        } catch (IndexOutOfBoundsException e) {
            sb.append("Sorry there are no such tasks.");
            return sb;
        }

        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tp.getSize(); i++) {
            sb.append((i + 1) + "." + tp.retrieveTask(i).toString() + "\n");
        }
        return sb;
    }

    private String stringTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n" + task.toString());
        sb.append("\nNow you have " + taskList.getSize() + " tasks in the list.");
        return sb.toString();
    }
}
