import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String line = "    ____________________________________________________________";
    private static final String space = "    ";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        checkSaveFile();
        // Write all files
        Storage.writeTasks(tasks);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputs = input.split(" ", 2);
                String command = inputs[0];

                if (command.equals("bye")) {
                    sayGoodbye();
                    break;
                } else if (command.equals("list")) {
                    displayList();
                } else if (command.equals("done")) {
                    int taskNo = Integer.parseInt(inputs[1]);
                    markTaskAsDone(taskNo);
                } else if (command.equals("delete")) {
                    int taskNo = Integer.parseInt(inputs[1]);
                    deleteTask(taskNo);
                } else if (command.equals("deadline")) {
                    if (inputs.length == 1) { throw new EmptyDescriptionException(); };
                    if (tasks.size() == 100) { throw new TooManyTasksException(); };
                    addDeadline(inputs[1]);
                } else if (command.equals("event")) {
                    if (inputs.length == 1) { throw new EmptyDescriptionException(); };
                    if (tasks.size() == 100) { throw new TooManyTasksException(); };
                    addEvent(inputs[1]);
                } else if (command.equals("todo")) {
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    if (tasks.size() == 100) {
                        throw new TooManyTasksException();
                    }
                    addTodo(inputs[1]);
                } else {
                    throw new UnknownCommandException();
                }

                // Rewrites the entire file for every update you make here
                // Probably O(n^2) time where n is the number of tasks but this is the simplest change we can make
                Storage.writeTasks(tasks);
            } catch (DukeException e) {
                printLine();
                indent(e.toString());
                printLine();
            } catch (DateTimeParseException e) {
                printLine();
                indent("It seems that you have entered a format we don't understand. ");
                indent("Please use the YYYY-MM-DD format.");
                printLine();
            }
        }
    }

    private static void printLine() {
        System.out.println(line);
    }

    private static void indent(String toIndent) {
        System.out.printf(space);
        System.out.println(toIndent);
    }

    private static void greet() {
        printLine();
        String logo = space
                + " ____        _        \n" + space
                + "|  _ \\ _   _| | _____ \n" + space
                + "| | | | | | | |/ / _ \\\n" + space
                + "| |_| | |_| |   <  __/\n" + space
                + "|____/ \\__,_|_|\\_\\___|\n";
        indent("Greetings, you may call me\n" + logo);
        indent("How may I help you in this fine day today?");
        printLine();
    }

    private static void sayGoodbye() {
        printLine();
        indent("I bid you adieu. Until the day we meet again.");
        printLine();
    }

    private static void displayList() {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            indent((i + 1) + ". " +tasks.get(i).toString());
        }
        printLine();
    }

    private static void markTaskAsDone(int taskNo) throws InvalidIndexException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(taskNo - 1); // The user starts counting from 1
        task.markAsDone();

        printLine();
        indent("Excellent! You have completed this task: ");
        indent(space + task.toString());
        printLine();
    }

    private static void deleteTask(int taskNo) throws InvalidIndexException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(taskNo - 1); // The user starts counting from 1
        tasks.remove(taskNo - 1);

        printLine();
        indent("Understood. I have now removed this task: ");
        indent(space + task.toString());
        printTaskCount();
        printLine();
    }

    private static void printTaskCount() {
        int len = tasks.size();
        if (len == 0 || len == 1) {
            indent("As of now, you have " + len + " task in the list.");
        } else {
            indent("As of now, you have " + len + " tasks in the list.");
        }
    }

    private static void addDeadline(String args) {
        String[] descAndBy = args.split(" /by ");
        Deadline deadline = new Deadline(descAndBy[0], descAndBy[1]);
        tasks.add(deadline);

        printLine();
        indent("Acknowledged. I have added: ");
        indent(space + deadline.toString());
        printTaskCount();
        printLine();
    }

    private static void addEvent(String args) {
        String[] descAndAt = args.split(" /at ");
        Event event = new Event(descAndAt[0], descAndAt[1]);
        tasks.add(event);

        printLine();
        indent("Acknowledged. I have added: ");
        indent(space + event.toString());
        printTaskCount();
        printLine();
    }

    private static void addTodo(String args) {
        Todo todo = new Todo(args);
        tasks.add(todo);
        printLine();
        indent("Acknowledged. I have added: ");
        indent(space + todo.toString());
        printTaskCount();
        printLine();
    }

    private static void checkSaveFile() {
        try {
            List<List<String>> savedTasks = Storage.loadTasksFromSaveFile();
            for (List<String> savedTask : savedTasks) {
                String type = savedTask.get(0);
                Task taskObject;
                if (type.equals("D")) {
                    taskObject = new Deadline(savedTask.get(2), savedTask.get(3));
                } else if (type.equals("E")) {
                    String args = savedTask.get(2) + " /at " + savedTask.get(3);
                    taskObject = new Event(savedTask.get(2), savedTask.get(3));
                } else {
                    // type equals ("T")
                    taskObject = new Todo(savedTask.get(2));
                }
                tasks.add(taskObject);

                if (savedTask.get(1).equals("1")) {
                    // That means task was initially done
                    taskObject.markAsDone();
                }
            }
            // System.out.println("Tasks loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("There is no save file found");
        }
    }
}
