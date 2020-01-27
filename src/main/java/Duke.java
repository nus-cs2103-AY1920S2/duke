import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke {
    private static final String indent = "    ";
    private static final String horizontal_line = "____________________________________________________________";

    private static List<Task> tasks;

    public static void main(String[] args) {
        try {
            tasks = loadTasks();
        } catch (FileNotFoundException e) {
            createFile();
            tasks = new ArrayList<>();
        } catch (DukeException e) {
            print("OOPS!!! " + e.getMessage());
        }

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
        sc.close();

        try {
            saveTasks();
        } catch (IOException e) {
            print("OOPS!!! Could not save tasks.");
        }
        print("Bye. Hope to see you again soon!");
    }

    private static void createFile() {
        try {
            Path path = Paths.get("data/duke.txt");
            Path parentDir = path.getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }
            Files.createFile(path);
        } catch (IOException e) {
            print("OOPS!!! Could not create data file.");
        }
    }

    private static List<Task> loadTasks() throws FileNotFoundException, DukeException {
        File file = new File("data/duke.txt");
        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(" \\| ");
            Task task;
            switch (data[0]) {
                case "T":
                    task = new Todo(data[2], data[1].equals("1"));
                    break;
                case "D":
                    task = new Deadline(data[2], data[3], data[1].equals("1"));
                    break;
                case "E":
                    task = new Event(data[2], data[3], data[1].equals("1"));
                    break;
                default:
                    throw new DukeException("Could not load tasks.");
            }
            tasks.add(task);
        }
        sc.close();
        return tasks;
    }

    private static void saveTasks() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task task : tasks) {
            fw.write(task.formatToSave() + System.lineSeparator());
        }
        fw.close();
    }

    private static void run(String input) throws DukeException {
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
            case "list":
                printTasks();
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
}
