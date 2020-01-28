// java imports
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
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
        Ui ui = new Ui();
        Storage storage = new Storage();
        Scanner sc = new Scanner(System.in);

        ui.printFormattedOutput("Hello! I'm Duke\n    What can I do for you?");

        ArrayList<Task> list = new ArrayList<>();

        // Convert from save file
        try {
            storage.readSaveFile(list);
        } catch (FileNotFoundException ex) {
            ui.printFormattedOutput("No save file found. Creating a new one...");
        }

        int numberOfTasks = list.size();

        // Input logic
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String action = input.split(" ")[0];
            try {
                switch (action) {
                    case "list":
                        ui.printList(list);
                        break;
                    case "done":
                        int taskNumber = Integer.parseInt(input.split(" ")[1]);
                        if (taskNumber < 1 || taskNumber > numberOfTasks) {
                            throw new InvalidTaskNumberException(numberOfTasks);
                        }
                        list.get(taskNumber - 1).markAsDone();
                        ui.printDone(list.get(taskNumber - 1));
                        break;
                    case "delete":
                        taskNumber = Integer.parseInt(input.split(" ")[1]);
                        if (taskNumber < 1 || taskNumber > numberOfTasks) {
                            throw new InvalidTaskNumberException(numberOfTasks);
                        }
                        Task deleteTask = list.get(taskNumber - 1);
                        list.remove(taskNumber - 1);
                        numberOfTasks--;
                        ui.printDelete(deleteTask, numberOfTasks);
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
                            ui.printNewTask(newTodo, numberOfTasks);
                        } catch (EmptyDescriptionException ex) {
                            ui.printFormattedOutput(ex.toString());
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
                            ui.printNewTask(newTask, numberOfTasks);
                        } catch (EmptyDescriptionException ex) {
                            ui.printFormattedOutput(ex.toString());
                        } catch (EmptyTimeException ex) {
                            String message = ex.toString()
                                    + "\n    Type in the time/date or press enter to stop creating task";
                            ui.printFormattedOutput(message);
                            System.out.print(ex.stringifyFields());
                            input = sc.nextLine();
                            if (!input.equals("")) {
                                String[] fields = ex.getFields();
                                Task newTask = action.equals("event")
                                        ? new Event(fields[0], input)
                                        : new Deadline(fields[0], input);
                                list.add(newTask);
                                numberOfTasks++;
                                ui.printNewTask(newTask, numberOfTasks);
                            } else {
                                ui.printFormattedOutput("Stopped creating task.");
                            }
                        }
                        break;
                    case "date":
                        String[] fields = input.split(" ");
                        try {
                            LocalDate date = LocalDate.parse(fields[1]);
                            searchDateTask(list, date);
                        } catch (DateTimeException ex) {
                            ui.printFormattedOutput("Sorry, I don't recognize this date format. " +
                                    "Try to follow this format: 2020-12-31");
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            ui.printFormattedOutput("Please input a date!");
                        }
                        break;
                    default:
                        throw new InvalidActionException();
                }
                storage.save(list);
            } catch (InvalidActionException ex) {
                ui.printFormattedOutput(ex.toString());
            } catch (InvalidTaskNumberException ex) {
                ui.printFormattedOutput(ex.toString());
            } catch (IOException ex) {
                ui.printFormattedOutput(ex.toString());
            } catch (DateTimeException ex) {
                ui.printFormattedOutput("You have entered an invalid time/date format.\n    " +
                        "Please follow the following format: 23:59 2020-12-31\n    " +
                        "You may input '-' to omit either the time or date");
            }

            input = sc.nextLine();
        }

        ui.printFormattedOutput("Bye. Hope to see you again soon!");
    }

    public static void searchDateTask(ArrayList<Task> list, LocalDate date) {
        ArrayList<Task> dateTasks = new ArrayList<>();
        Ui ui = new Ui();

        for (Task t : list) {
            if (t instanceof Event || t instanceof Deadline) {
                if (date.equals(((DateTask) t).getDate())) {
                    dateTasks.add(t);
                }
            }
        }
        ui.printList(dateTasks);
    }
}