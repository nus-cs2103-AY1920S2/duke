import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.io.*;

public class Duke {

    static Scanner sc = new Scanner(System.in);
    static final String FILEPATH = "../../data/duke.txt";
    static Ui ui;
    static TaskList tasks;
    static Storage storage;

    static String taskToParse(TaskList tasks) {

        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < tasks.getSize(); i++) {
            s.append(tasks.getIndex(i).toParser() + "\n");
        }

        return s.toString();
    }

    static void printIntro() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Hello! I'm Duke\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n\n");
    }

    static void printReply(Task task) {
        System.out.print(
                "____________________________________________________________\n" +
                        "Got it! I've added the task: \n" + task.toString() + "\nNow you have " + tasks.getSize() +
                        " tasks in the list." +
                        "\n____________________________________________________________\n");
    }

    static void printGoodbye() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!" +
                        "\n____________________________________________________________\n");
    }

    static String stringToTime(String s) throws DukeException {
        try {
            // Convert DATE to expected format
            LocalDate d = LocalDate.parse(s.split(" ")[0]);
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            Date dateObj = sdf.parse(s.split(" ")[1]);
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + new SimpleDateFormat("HH:mm aa").format(dateObj);
        } catch (Exception e) {
            System.out.println("Please give a correct format (ie. yyyy-mm-dd hh:mm)");
            throw new DukeException(s);
        }
    }

    static void addTask(String input) throws DukeException, TodoException {
        try {
            if (input.toLowerCase().equals("list")) {
                tasks.printList();
            } else if (input.split(" ")[0].equals("done")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.markTaskDone(taskNumber);
                storage.saveFile(taskToParse(tasks));
            } else if (input.split(" ")[0].equals("todo")) {
                if (input.split(" ").length == 1) {
                    throw new TodoException(input);
                }
                Task task = new Todo(input.split(" ", 2)[1]);
                tasks.addTask(task);
                storage.saveFile(taskToParse(tasks));
                printReply(task);
            } else if (input.split(" ")[0].equals("deadline")) {
                Task task = new Deadline(input.split("/by ", 2)[0].split(" ", 2)[1], stringToTime(input.split("/by ", 2)[1]));
                tasks.addTask(task);
                storage.saveFile(taskToParse(tasks));
                printReply(task);
            } else if (input.split(" ")[0].equals("event")) {
                Task task = new Event(input.split("/at", 2)[0].split(" ", 2)[1], stringToTime(input.split("/at ", 2)[1]));
                tasks.addTask(task);
                storage.saveFile(taskToParse(tasks));
                printReply(task);
            } else if (input.split(" ")[0].equals("find")){
                tasks.search(input.split(" ", 2)[1]);
            } else {
                throw new DukeException(input);
            }
        } catch (TodoException e) {
            System.out.println(e.toString());
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    static void deleteTask(String input) {
        int pos = Integer.parseInt(input.split(" ")[1]);
        Task task = tasks.removeTask(pos - 1);
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this task: \n  "
                + task.toString() + "\n Now you have " + tasks.getSize() + " tasks in the list.\n"
                + "____________________________________________________________");
    }

    public void run() throws DukeException {
        printIntro();
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            if (input.split(" ")[0].toLowerCase().equals("delete")) {
                deleteTask(input);
                storage.saveFile(taskToParse(tasks));
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }
        printGoodbye();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke(FILEPATH).run();
    }
}