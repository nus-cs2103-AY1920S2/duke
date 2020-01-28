import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {

    private static final String INDENT = "    ";
    private static final String HOR_LINE = "___________________________" +
            "_________________________________";
    private static final String FILE_PATH = "data/tasks.txt";

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        File f = new File("data/tasks.txt");

        System.out.println(INDENT + HOR_LINE);
        System.out.println(INDENT + "Hello! I'm Duke!");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(INDENT + HOR_LINE);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // Exit Duke
                System.out.println(INDENT + HOR_LINE);
                System.out.println(INDENT + "Bye! Come back soon!");
                System.out.println(INDENT + HOR_LINE);
                break;
            } else if (userInput.equals("list")) {
                // Print out list of all tasks
                printList(tasks);
            } else if (userInput.contains("done")) {
                // Mark one task as complete
                try {
                    completeTask(tasks, userInput);
                } catch (DukeException e) {
                    e.print();
                } catch (IOException e) {
                    System.out.println("Write failed");
                }
            } else if (userInput.contains("delete")) {
                // Remove task
                try {
                    deleteTask(tasks, userInput);
                } catch (DukeException e) {
                    e.print();
                } catch (IOException e) {
                    System.out.println("Write failed");
                }
            } else {
                // Add new task
                try {
                    addTask(tasks, userInput);
                } catch (DukeException e) {
                    e.print();
                } catch (IOException e) {
                    System.out.println("Write failed");
                }
            }
        }
    }

    private static void addTask(ArrayList<Task> tasks, String userInput) throws DukeException, IOException {
        Task t;
        if (userInput.contains("todo")) {
            // add Todo task
            if (userInput.equals("todo")) throw new DukeException("insufficient details");
            String taskDescription = userInput.substring(5).trim(); // removes todo word
            t = new Todo(taskDescription);
        } else if (userInput.contains("deadline")) {
            // add Deadline task
            if (userInput.equals("deadline")) throw new DukeException("insufficient details");
            String taskDescription = userInput.substring((9)); // removes deadline word
            int slashIdx = taskDescription.indexOf("/");
            if (slashIdx == -1) throw new DukeException("wrong format");

            String taskTitle = taskDescription.substring(0, slashIdx).trim();
            String deadline = taskDescription.substring(slashIdx + 4).trim();

            try {
                t = new Deadline(taskTitle, parseDate(deadline));
            } catch (ParseException e) {
                t = null;
                System.out.println(INDENT + "Please enter deadline in the following format: YYYY-MM-DD");
            }
        } else if (userInput.contains("event")) {
            // add Event task
            if (userInput.equals("event")) throw new DukeException("insufficient details");
            String taskDescription = userInput.substring(6); // removes event word
            int slashIdx = taskDescription.indexOf("/");
            if (slashIdx == -1) throw new DukeException("wrong format");

            String taskTitle = taskDescription.substring(0, slashIdx).trim();
            String dateTime = taskDescription.substring(slashIdx + 4).trim();

            try {
                t = new Event(taskTitle, parseDateTime(dateTime));
            } catch (DateTimeParseException e) {
                t = null;
                System.out.println(INDENT + "Please enter deadline in the following format: YYYY-MM-DD HHMM");
            }
        } else {
            t = null;
            throw new DukeException("Unrecognized");
        }

        if (t != null) {
            tasks.add(t);
            try {
                saveTasks(tasks);
            } catch (IOException e) {
                System.out.println("Write error");
            }
            System.out.println(INDENT + HOR_LINE);
            System.out.println(INDENT + "This task has been added successfully:");
            System.out.println(INDENT + "  " + t);
            System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list");
            System.out.println(INDENT + HOR_LINE);
            saveTasks(tasks);
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            fw.write(formatTaskForSaving(task) + System.lineSeparator());
        }
        fw.close();
    }

    private static String formatTaskForSaving(Task t) {
        String toAdd = "";
        int isDone = 0;
        if (t.isDone()) {
            isDone = 1;
        }

        if (t instanceof Todo) {
            toAdd += "T | " + isDone + " | " + t.getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            toAdd += "D | " + isDone + " | " + d.getDescription() + " | " + d.getDeadline();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            toAdd += "E | " + isDone + " | " + e.getDescription() + " | " + e.getDateTime();
        }

        return toAdd;
    }

    private static void deleteTask(ArrayList<Task> tasks, String userInput) throws DukeException, IOException {
        if (userInput.equals("delete")) {
            throw new DukeException(userInput);
        }
        int taskNumber = Character.getNumericValue(userInput.charAt(7));
        if (taskNumber > tasks.size()) {
            throw new DukeException("List size");
        }
        Task t = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        System.out.println(INDENT + HOR_LINE);
        System.out.println(INDENT + "This task has successfully been deleted:");
        System.out.println(INDENT + "  " + t);
        System.out.println(INDENT + "You have " + tasks.size() + " tasks remaining.");
        System.out.println(INDENT + HOR_LINE);
        saveTasks(tasks);
    }

    private static void completeTask(ArrayList<Task> tasks, String userInput) throws DukeException, IOException {
        if (userInput.equals("done")) {
            throw new DukeException(userInput);
        }
        int taskNumber = Character.getNumericValue(userInput.charAt(5));
        if (taskNumber > tasks.size()) {
            throw new DukeException("List size");
        }
        Task t = tasks.get(taskNumber - 1);
        t.markAsDone();
        System.out.println(INDENT + HOR_LINE);
        System.out.println(INDENT + "This task is marked as completed:");
        System.out.println(INDENT + "  " + t);
        System.out.println(INDENT + HOR_LINE);
        saveTasks(tasks);
    }

    private static void printList(ArrayList<Task> tasks) {
        System.out.println(INDENT + HOR_LINE);
        System.out.println(INDENT + "This is your list of tasks:");

        if (tasks.size() == 0) {
            System.out.println(INDENT + "Your list is currently empty.");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(INDENT + (i+1) + "." + t);
        }
        System.out.println(INDENT + HOR_LINE);
    }

    private static Date parseDate(String date) throws ParseException {
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return simpleDateFormat.parse(date);
    }

    private static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        String dateTimePattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return localDateTime;
    }
}