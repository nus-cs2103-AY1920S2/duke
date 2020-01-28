import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {
    public static void main(String[] args) {
        try {
            greet();
            loadFile(tasks);
            run();
            saveFile(tasks);
        } catch (DukeException e) {
            System.err.println(e);
            print("An error occurred, please try again later.");
        } catch (FileNotFoundException f) {
            System.err.println(f);
        } catch (IOException i) {
            System.err.println(i);
        } catch (DateTimeParseException d) {
            System.err.println("Please enter the date as yyyy-mm-dd followed by the time.");
            print("Try again later.");
        } finally {
            print("Program terminated.");
        }
    }

    private static void loadFile(List<Task> tasks) throws FileNotFoundException {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        File file = new File(path.toString());
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] task = sc.nextLine().split("/");
            switch (task[0]) {
                case "T":
                    Task toDo = new Todo(task[2]);
                    if (task[1].equals("true")) {
                        toDo.markAsDone();
                    }
                    tasks.add(toDo);
                    break;
                case "D":
                    Task deadLine = new Deadline(task[2], task[3]);
                    if (task[1].equals("true")) {
                        deadLine.markAsDone();
                    }
                    tasks.add(deadLine);
                    break;
                case "E":
                    Task event = new Event(task[2], task[3]);
                    if (task[1].equals("true")) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
            }
        }
    }

    private static void saveFile(List<Task> tasks) throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        File file = new File(path.toString());
        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            if (task instanceof Todo) {
                writer.write("T/" + task.isDone + "/" + task.description + "\n");
            } else if (task instanceof Deadline) {
                writer.write("D/" + task.isDone + "/" + task.description + "/" + ((Deadline) task).by + "\n");
            } else if (task instanceof Event) {
                writer.write("E/" + task.isDone + "/" + task.description + "/" + ((Event) task).at + "\n");
            }
        }
        writer.close();
    }

    private static String line = "____________________________________________________________";

    private static void print(String s) {
        System.out.println(s);
    }

    private static void greet() {
        print(line);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        print("Hello from\n" + logo);
        print("What can I do for you?");
        print(line);
    }

    private static List<Task> tasks = new ArrayList<>();

    private static void displayTasks() throws DukeInvalidTaskException {
        if (tasks.size() != 0) {
            print("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                print(i + "." + tasks.get(i - 1));
            }
            print(line);
        } else {
            throw new DukeInvalidTaskException("There are no remaining tasks in the list");
        }
    }

    private static void printInvalidCmdError(String command) throws DukeInvalidCommandException {
        String wrongCmd = command.split(" ")[0];
        if (wrongCmd.equals("todo") || wrongCmd.equals("deadline") || wrongCmd.equals("event")) {
            throw new DukeInvalidCommandException("☹ OOPS!!! The description of a " + wrongCmd + " cannot be empty.");
        } else {
            throw new DukeInvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void markAsDone(String command) throws DukeInvalidTaskException {
        int i = Integer.parseInt(command.split(" ")[1]) - 1;
        if (i >= tasks.size() || i < 0) {
            throw new DukeInvalidTaskException("There is no such task in the list!");
        } else {
            tasks.get(i).markAsDone();
            print("Nice! I've marked this task as done: ");
            System.out.println(tasks.get(i));
            print(line);
        }
    }

    private static void deleteTask(String command) throws DukeInvalidTaskException {
        int i = Integer.parseInt(command.split(" ")[1]) - 1;
        if (i >= tasks.size() || i < 0) {
            throw new DukeInvalidTaskException("There is no such task in the list!");
        } else {
            print("Noted. I've removed this task: ");
            print("  " + tasks.get(i));
            tasks.remove(tasks.get(i));
            print("Now you have " + tasks.size() + " task(s) in the list.");
            print(line);
        }
    }

    private static void addTask(String command) throws DateTimeParseException {
        Task task = null;
        String taskType = command.split(" ")[0];
        command = command.substring(command.indexOf(" "));
        switch (taskType) {
            case "todo":
                task = new Todo(command);
                break;
            case "event":
                task = new Event(command.split("/at ")[0],
                        LocalDate.parse(command.split("/at ")[1].split(" ")[0]),
                        command.split("/at ")[1].split(" ")[1]);
                break;
            case "deadline":
                task = new Deadline(command.split("/by ")[0],
                        LocalDate.parse(command.split("/by ")[1].split(" ")[0]),
                        command.split("/by ")[1].split(" ")[1]);
                break;
        }
        tasks.add(task);
        print("Got it. I've added this task:");
        print("  " + task);
        print("Now you have " + tasks.size() + " task(s) in the list.");
        print(line);
    }

    private static void run() throws DukeException, DateTimeParseException {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                displayTasks();
            } else if (command.split(" ").length == 1) {
                printInvalidCmdError(command);
                return;
            } else {
                switch (command.split(" ")[0]) {
                    case "done":
                        markAsDone(command);
                        break;
                    case "delete":
                        deleteTask(command);
                        break;
                    default:
                        addTask(command);
                        break;
                }
            }
            command = sc.nextLine();
        }
        sc.close();
        print("Bye. Hope to see you again soon!");
        print(line);
    }

}

