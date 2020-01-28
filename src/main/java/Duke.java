// java imports
import java.util.List;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

// packages imports
import exceptions.EmptyDescriptionException;
import exceptions.EmptyTimeException;
import exceptions.InvalidActionException;
import exceptions.InvalidTaskNumberException;
import tasks.*;

public class Duke {
    private static String SAVE_FILE = "save_file.txt";

    public static void main(String[] args) {

        printFormattedOutput("Hello! I'm Duke\n    What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        // Convert from save file
        try {
            readSaveFile(list);
        } catch (FileNotFoundException ex) {
            printFormattedOutput("No save file found. Creating a new one...");
        }

        int numberOfTasks = list.size();

        // Input logic
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String action = input.split(" ")[0];
            try {
                switch (action) {
                    case "list":
                        printList(list);
                        break;
                    case "done":
                        int taskNumber = Integer.parseInt(input.split(" ")[1]);
                        if (taskNumber < 1 || taskNumber > numberOfTasks) {
                            throw new InvalidTaskNumberException(numberOfTasks);
                        }
                        list.get(taskNumber - 1).markAsDone();
                        printDone(list.get(taskNumber - 1));
                        break;
                    case "delete":
                        taskNumber = Integer.parseInt(input.split(" ")[1]);
                        if (taskNumber < 1 || taskNumber > numberOfTasks) {
                            throw new InvalidTaskNumberException(numberOfTasks);
                        }
                        Task deleteTask = list.get(taskNumber - 1);
                        list.remove(taskNumber - 1);
                        numberOfTasks--;
                        printDelete(deleteTask, numberOfTasks);
                        break;
                    case "todo":
                        try {
                            String[] fields = input.split("todo ");
                            if (fields.length < 2) {
                                throw new EmptyDescriptionException("todo");
                            }
                            Task newTodo = new Todo(fields[1]);
                            list.add(newTodo);
                            numberOfTasks++;
                            printNewTask(newTodo, numberOfTasks);
                        } catch (EmptyDescriptionException ex) {
                            printFormattedOutput(ex.toString());
                        }
                        break;
                    case "event":
                    case "deadline":
                        try {
                            String[] fields = input.split(action + " ");
                            if (fields.length < 2) {
                                throw new EmptyDescriptionException(action);
                            }

                            fields = action.equals("event")
                                    ? fields[1].split(" /at ")
                                    : fields[1].split(" /by ");
                            if (fields.length < 2) {
                                throw new EmptyTimeException(action, fields);
                            }

                            Task newTask = action.equals("event")
                                    ? new Event(fields[0], fields[1])
                                    : new Deadline(fields[0], fields[1]);

                            list.add(newTask);
                            numberOfTasks++;
                            printNewTask(newTask, numberOfTasks);
                        } catch (EmptyDescriptionException ex) {
                            printFormattedOutput(ex.toString());
                        } catch (EmptyTimeException ex) {
                            String message = ex.toString()
                                    + "\n    Type in the time/date or press enter to stop creating task";
                            printFormattedOutput(message);
                            System.out.print(ex.stringifyFields());
                            input = sc.nextLine();
                            if (!input.equals("")) {
                                String[] fields = ex.getFields();
                                Task newTask = action.equals("event")
                                        ? new Event(fields[0], input)
                                        : new Deadline(fields[0], input);
                                list.add(newTask);
                                numberOfTasks++;
                                printNewTask(newTask, numberOfTasks);
                            } else {
                                printFormattedOutput("Stopped creating task.");
                            }
                        }
                        break;
                    case "date":
                        String[] fields = input.split(" ");
                        try {
                            LocalDate date = LocalDate.parse(fields[1]);
                            searchDateTask(list, date);
                        } catch (DateTimeException ex) {
                            printFormattedOutput("Sorry, I don't recognize this date format. " +
                                    "Try to follow this format: 2020-12-31");
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            printFormattedOutput("Please input a date!");
                        }
                        break;
                    default:
                        throw new InvalidActionException();
                }
                save(list);
            } catch (InvalidActionException ex) {
                printFormattedOutput(ex.toString());
            } catch (InvalidTaskNumberException ex) {
                printFormattedOutput(ex.toString());
            } catch (IOException ex) {
                printFormattedOutput(ex.toString());
            } catch (DateTimeException ex) {
                printFormattedOutput("You have entered an invalid time/date format.\n    " +
                        "Please follow the following format: 23:59 2020-12-31\n    " +
                        "You may input '-' to omit either the time or date");
            }

            input = sc.nextLine();
        }

        printFormattedOutput("Bye. Hope to see you again soon!");
    }

    // Reading from and writing to save file

    public static void save(ArrayList<Task> list) throws IOException {
        FileWriter file = new FileWriter(SAVE_FILE, false);
        BufferedWriter writer = new BufferedWriter(file);

        String text = "";
        for (Task t : list) {
            text += t.toSaveFormat() + "\n";
        }

        writer.write(text);
        writer.close();
    }

    public static void readSaveFile(List<Task> list) throws FileNotFoundException {
        FileReader file = new FileReader(SAVE_FILE);
        BufferedReader reader = new BufferedReader(file);

        try {
            String text = reader.readLine();

            while (text != null) {
                String[] fields = text.split(" \\| ");
                Task newTask;

                // Create corresponding specific task
                if (fields[0].equals("T")) {
                    newTask = new Todo(fields[2]);
                } else if (fields[0].equals("E")) {
                    newTask = new Event(fields[2], fields[3]);
                } else {
                    newTask = new Deadline(fields[2], fields[3]);
                }

                // Set isDone status
                if (newTask != null && Integer.parseInt(fields[1]) == 1) {
                    newTask.markAsDone();
                }

                list.add(newTask);
                text = reader.readLine();
            }
        } catch (IOException ex) {
            printFormattedOutput("Corrupted Task");
        }
    }

    public static void searchDateTask(ArrayList<Task> list, LocalDate date) {
        ArrayList<Task> dateTasks = new ArrayList<>();
        for (Task t : list) {
            if (t instanceof Event || t instanceof Deadline) {
                if (date.equals(((DateTask) t).getDate())) {
                    dateTasks.add(t);
                }
            }
        }
        printList(dateTasks);
    }

    // Print formatters

    private static String bar = "    **************************************************************\n";

    public static void printFormattedOutput(String output) {
        System.out.println(bar + "    " + output + "\n" + bar);
    }

    public static void printList(ArrayList<Task> list) {
        System.out.print(bar);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i));
        }
        System.out.println(bar);
    }

    public static void printNewTask(Task task, int sizeOfList) {
        System.out.print(bar);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + sizeOfList + " tasks on the list.");
        System.out.println(bar);
    }

    public static void printDone(Task task) {
        System.out.print(bar);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println(bar);
    }

    public static void printDelete(Task task, int sizeOfList) {
        System.out.print(bar);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + sizeOfList + " tasks on the list.");
        System.out.println(bar);
    }
}