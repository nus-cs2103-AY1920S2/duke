import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {
    private static final String indent = "    ";
    private static final String horizontal_line = "____________________________________________________________";

    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        print(logo + "\nHello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                run(input);
            } catch (DukeException e) {
                print("OOPS!!! " + e.getMessage());
            }
            input = sc.nextLine();
        }
        print("Bye. Hope to see you again soon!");
        sc.close();
    }

    private static void run(String input) throws DukeException {
        if (input.equals("list")) {
            printTasks();
            return;
        }
        String[] inputArr = input.split(" ", 2);
        switch(inputArr[0]) {
            case "todo":
                addTodo(inputArr);
                break;
            case "deadline":
                addDeadline(inputArr);
                break;
            case "event":
                addEvent(inputArr);
                break;
            case "done":
                completeTask(inputArr);
                break;
            case "delete":
                deleteTask(inputArr);
                break;
            case "get":
                getTasks(inputArr);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void addTodo(String[] inputArr) throws DukeException {
        try {
            if (inputArr[1].trim().equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            addTask(new Todo(inputArr[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private static void addDeadline(String[] inputArr) throws DukeException {
        try {
            if (inputArr[1].trim().equals("")) {
                throw new DukeException("The description and due date of a deadline cannot be empty.");
            }
            String[] deadlineArr = inputArr[1].split(" /by ");
            if (deadlineArr[0].trim().equals("") || deadlineArr[1].trim().equals("")) {
                throw new DukeException("The description and due date of a deadline cannot be empty.");
            }
            addTask(new Deadline(deadlineArr[0], deadlineArr[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description and due date of a deadline cannot be empty.");
        }
    }

    private static void addEvent(String[] inputArr) throws DukeException {
        try {
            if (inputArr[1].trim().equals("")) {
                throw new DukeException("The description and time of an event cannot be empty.");
            }
            String[] eventArr = inputArr[1].split(" /at ");
            if (eventArr[0].trim().equals("") || eventArr[1].trim().equals("")) {
                throw new DukeException("The description and time of an event cannot be empty.");
            }
            addTask(new Event(eventArr[0], eventArr[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description and time of an event cannot be empty.");
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        List<String> lines = new ArrayList<>();
        lines.add("Got it. I've added this task: ");
        lines.add("  " + task);
        lines.add("Now you have " + Task.getNumOfTasks() + " tasks in the list.");
        print(lines);
    }

    private static void completeTask(String[] inputArr) throws DukeException {
        try {
            if (inputArr[1].trim().equals("")) {
                throw new DukeException("The ID of a task done cannot be empty.");
            }
            int taskId = Integer.parseInt(inputArr[1]);
            Task task = tasks.get(taskId - 1);
            task.markAsDone();
            List<String> lines = new ArrayList<>();
            lines.add("Nice! I've marked this task as done: ");
            lines.add("  " + task);
            print(lines);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The ID of a task done cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task cannot be found.");
        } catch (NumberFormatException e) {
            throw new DukeException("The ID of task done should be a number.");
        }
    }

    private static void deleteTask(String[] inputArr) throws DukeException {
        try {
            if (inputArr[1].trim().equals("")) {
                throw new DukeException("The ID of a task done cannot be empty.");
            }
            int taskId = Integer.parseInt(inputArr[1]);
            Task task = tasks.get(taskId - 1);
            tasks.remove(task);
            Task.numOfTasks--;
            List<String> lines = new ArrayList<>();
            lines.add("Noted. I've removed this task: ");
            lines.add("  " + task);
            lines.add("Now you have " + Task.getNumOfTasks() + " tasks in the list.");
            print(lines);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The ID of a task done cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task cannot be found.");
        } catch (NumberFormatException e) {
            throw new DukeException("The ID of task done should be a number.");
        }
    }

    private static void getTasks(String[] inputArr) throws DukeException {
        try {
            if (inputArr[1].trim().equals("")) {
                throw new DukeException("The date of tasks to retrieve cannot be empty.");
            }
            LocalDate date = LocalDate.parse(inputArr[1]);
            List<Task> filteredTasks = new ArrayList<>();
            for (Task task : tasks) {
                if ((task instanceof Deadline && ((Deadline)task).getDate().equals(date)) ||
                        (task instanceof Event && ((Event)task).getDate().equals(date))) {
                    filteredTasks.add(task);
                }
            }
            printTasks(filteredTasks, inputArr[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format. Format required: yyyy-mm-dd");
        }
    }

    private static void print(String text) {
        String[] lines = text.split("\n");
        System.out.println(indent + horizontal_line);
        for (String line : lines) {
            System.out.println(indent + line);
        }
        System.out.println(indent + horizontal_line + "\n");
    }

    private static void print(List<String> lines) {
        System.out.println(indent + horizontal_line);
        for (String line : lines) {
            System.out.println(indent + line);
        }
        System.out.println(indent + horizontal_line + "\n");
    }

    private static void printTasks() {
        System.out.println(indent + horizontal_line);
        if (tasks.isEmpty()) {
            System.out.println(indent + "There are no tasks in the list.");
        } else {
            System.out.println(indent + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(indent + (i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(indent + horizontal_line + "\n");
    }

    private static void printTasks(List<Task> filteredTasks, String date) {
        System.out.println(indent + horizontal_line);
        if (filteredTasks.isEmpty()) {
            System.out.println(indent + "There are no tasks on " + date + ".");
        } else {
            System.out.println(indent + "Here are the tasks on " + date + ":");
            for (Task task : filteredTasks) {
                System.out.println(indent + "  " + task);
            }
        }
        System.out.println(indent + horizontal_line + "\n");
    }
}
