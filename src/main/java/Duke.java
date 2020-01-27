import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Duke {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
//        ArrayList<Task> tasks = new ArrayList<>();

        String projectRoot = System.getProperty("user.dir");
        Path taskFile = Paths.get(projectRoot, "data/duke.txt");
        ArrayList<Task> tasks = getTasksFromFile(taskFile);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");

        printMessage("Greetings! I'm Duke!\n\tHow may I help you?");

        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = readNextInput();
                String[] inputSplit = input.split(" ", 2);

                Command cmd = Command.getCommand(inputSplit[0]);

                switch (cmd) {
                    case BYE:
                        printMessage("Bye! Hope you visit again soon!");
                        isRunning = false;
                        break;
                    case LIST:
                        displayList(tasks);
                        break;
                    case DONE:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! Missing command parameters!");
                        } else {
                            int index = Integer.valueOf(inputSplit[1]) - 1;
                            if (index < 0 || index >= tasks.size()) {
                                throw new OutOfRangeException();
                            } else {
                                Task task = tasks.get(index);
                                task.markAsDone();
                                printMessage("Nice! I've marked this task as done:\n\t" + task.toString());
                                saveTasksToFile(taskFile, tasks);
                            }
                        }
                        break;
                    case DELETE:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! Missing command parameters!");
                        } else {
                            int index = Integer.valueOf(inputSplit[1]) - 1;
                            if (index < 0 || index >= tasks.size()) {
                                throw new OutOfRangeException();
                            } else {
                                Task taskToDelete = tasks.get(index);
                                tasks.remove(index);
                                printMessage("Noted! I've removed this task:\n\t\t" + taskToDelete.toString() +
                                        "\n\tNow you have " + tasks.size() + " tasks in the list.");
                                saveTasksToFile(taskFile, tasks);
                            }
                        }
                        break;
                    case DEADLINE:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! The description of a deadline cannot " +
                                    "be empty!");
                        } else {
                            String[] arguments = inputSplit[1].split(" /by ", 2);
                            if (arguments.length < 2) {
                                throw new InsufficientArgumentsException("☹ OOPS!!! Missing deadline parameters!");
                            } else {
                                try {
                                    Task newTask = new Deadline(arguments[0], LocalDate.parse(arguments[1]));
                                    tasks.add(newTask);
                                    printMessage("Got it! I've added the task:\n\t\t" + newTask.toString() +
                                            "\n\tNow you have " + tasks.size() + " tasks in the list.");
                                } catch (DateTimeParseException ex) {
                                    printMessage("☹ OOPS!!! Please enter a valid date: YYYY-MM-DD");
                                }

                                saveTasksToFile(taskFile, tasks);
                            }
                        }
                        break;
                    case EVENT:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! The description of an event cannot" +
                                    " be empty");
                        } else {
                            String[] arguments = inputSplit[1].split(" /at ", 2);
                            if (arguments.length < 2) {
                                throw new InsufficientArgumentsException("☹ OOPS!!! Missing event parameters!");
                            } else {
                                try {
                                    Task newTask = new Event(arguments[0], LocalDate.parse(arguments[1]));
                                    tasks.add(newTask);
                                    printMessage("Got it! I've added the task:\n\t\t" + newTask.toString() +
                                            "\n\tNow you have " + tasks.size() + " tasks in the list.");
                                } catch (DateTimeParseException ex) {
                                    printMessage("☹ OOPS!!! Please enter a valid date: YYYY-MM-DD");
                                }

                                saveTasksToFile(taskFile, tasks);
                            }
                        }
                        break;
                    case TODO:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! The description of a to-do cannot be " +
                                    "empty");
                        } else {
                            Task newTask = new Todo(inputSplit[1]);
                            tasks.add(newTask);
                            printMessage("Got it! I've added the task:\n\t\t" + newTask.toString() +
                                    "\n\tNow you have " + tasks.size() + " tasks in the list.");
                            saveTasksToFile(taskFile, tasks);
                        }
                        break;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (DukeException ex) {
                printMessage(ex.getMessage());
            }
        }
    }

    public static String readNextInput() {
        return sc.nextLine();
    }

    public static void printMessage(String msg) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + msg);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void displayList(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                result.append(String.format("%d. %s\n\t", i + 1, tasks.get(i)));
            } else {
                result.append(String.format("%d. %s", i + 1, tasks.get(i)));
            }
        }

        printMessage(result.toString());
    }

    public static ArrayList<Task> getTasksFromFile(Path file) {

        ArrayList<Task> retrievedTasks = new ArrayList<>();
        try {
            if (!Files.exists(file)) {
                Files.createDirectories(file.getParent());
                Files.createFile(file);
            } else {
                List<String> lines = Files.readAllLines(file);

           /*
            T | 1 | read book
            D | 0 | return book | June 6th
            E | 0 | project meeting | Aug 6th 2-4pm
            */

                for (String line : lines) {
                    String[] lineSplit = line.split(" \\| ");

                    if (lineSplit[0].equals("T")) {
                        retrievedTasks.add(new Todo(lineSplit[2], parseTaskStatusFromFile(lineSplit[1])));
                    } else if (lineSplit[0].equals("D")) {
                        retrievedTasks.add(new Deadline(lineSplit[2], LocalDate.parse(lineSplit[3]),
                                parseTaskStatusFromFile(lineSplit[1])));
                    } else if (lineSplit[0].equals("E")) {
                        retrievedTasks.add(new Event(lineSplit[2], LocalDate.parse(lineSplit[3]),
                                parseTaskStatusFromFile(lineSplit[1])));
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return retrievedTasks;
    }

    public static void saveTasksToFile(Path file, ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.generateSaveFileEntry());
        }

        try {
            Files.write(file, result.toString().getBytes());
        } catch (FileNotFoundException ex) {
            System.err.println("Task file not found.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static boolean parseTaskStatusFromFile(String status) {
        return status.equals("1") ? true : false;
    }
}
