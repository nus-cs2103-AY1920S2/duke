import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static List<Task> tasks;

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        tasks = new ArrayList<>();
        String input = sc.nextLine();
        while (true) {
            try {
                String[] parsedInput = input.strip().split("\\s+", 2);
                switch (parsedInput[0]) {
                case "bye":
                    if (parsedInput.length > 1) {
                        throw new InvalidCommandException();
                    }
                    exit();
                    break;
                case "list":
                    if (parsedInput.length > 1) {
                        throw new InvalidCommandException();
                    }
                    list();
                    break;
                case "done":
                    try {
                        int taskNumber = Integer.parseInt(parsedInput[1]);
                        completeTask(taskNumber);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MissingTaskNumberException();
                    } catch (NumberFormatException e) {
                        throw new InvalidTaskException();
                    }
                    break;
                case "delete":
                    try {
                        int taskNumber = Integer.parseInt(parsedInput[1]);
                        deleteTask(taskNumber);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MissingTaskNumberException();
                    } catch (NumberFormatException e) {
                        throw new InvalidTaskException();
                    }
                    break;
                case "todo":
                    try {
                        addTodo(parsedInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new EmptyTodoException();
                    }
                    break;
                case "deadline":
                    try {
                        addDeadline(parsedInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new EmptyDeadlineException();
                    }
                    break;
                case "event":
                    try {
                        addEvent(parsedInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new EmptyEventException();
                    }
                    break;
                default:
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                System.out.println(style(e.getMessage()));
            }
            input = sc.nextLine();
        }
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello, I'm Duke!\n"
                + "What can I do for you?";
        System.out.println(logo + style(greeting));
    }

    private static void exit() {
        String bye = "Goodbye. Hope to see you again soon!";
        System.out.println(style(bye));
        System.exit(0);
    }

    private static void addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        printTask(todo);
    }

    private static void addDeadline(String info) throws MissingDeadlineException {
        try {
            String[] parsedInfo = info.split("\\s*/by\\s*", 2);
            Deadline deadline = new Deadline(parsedInfo[0], parsedInfo[1]);
            tasks.add(deadline);
            printTask(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingDeadlineException();
        }
    }

    private static void addEvent(String info) throws MissingTimeException {
        try {
            String[] parsedInfo = info.split("\\s*/at\\s*", 2);
            Event event = new Event(parsedInfo[0], parsedInfo[1]);
            tasks.add(event);
            printTask(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingTimeException();
        }
    }

    private static void printTask(Task task) {
        String numberOfTasks;
        if (tasks.size() == 1) {
            numberOfTasks = "There is now 1 task in the list.";
        } else {
            numberOfTasks = "There are now " + tasks.size() + " tasks in the list.";
        }
        System.out.println(style("Got it. I've added this task:\n  " + task + "\n" + numberOfTasks));
    }

    private static void list() {
        if (tasks.size() == 0) {
            System.out.println(style("There are no tasks now."));
        } else {
            StringBuilder list = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                if (i > 0) {
                    list.append("\n");
                }
                list.append((i + 1) + ". " + tasks.get(i));
            }
            System.out.println(style(list.toString()));
        }
    }

    private static void completeTask(int taskNumber) throws InvalidTaskNumberException {
        try {
            int taskIndex = taskNumber - 1;
            Task completedTask = tasks.get(taskIndex).complete();
            tasks.set(taskIndex, completedTask);
            System.out.println(style("Nice! I've marked this task as done:\n  " + completedTask));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(taskNumber);
        }
    }

    private static void deleteTask(int taskNumber) throws InvalidTaskNumberException {
        try {
            int taskIndex = taskNumber - 1;
            Task deletedTask = tasks.remove(taskIndex);
            System.out.println(style("Noted. I've removed this task:\n  " + deletedTask));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(taskNumber);
        }
    }

    private static String style(String message) {
        String horizontalLine = new String(new char[76]).replace("\0", "-");
        message = horizontalLine + "\n" + message + "\n" + horizontalLine;
        return message.lines()
            .map(x -> "    " + x + "\n")
            .reduce("", (x, y) -> x + y);
    }
}
