import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    static ArrayList<Task> taskList = new ArrayList<>();
    static String FILEPATH = "../../../data/duke.txt";
    static DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");

    // Simply adds a separator above and below an intended output.
    public static void replyUser(String output) {
        System.out.println("\n------------------------------------------");
        System.out.println(output);
        System.out.println("------------------------------------------\n");
    }

    // Add Task to list with specified type (i.e. "T", "D", "E").
    public static void addTask(String input, String type) throws DukeTaskException {
        String str = "\nCurrent number of task(s): ";

        if (type.equals("T")) {
            Todo todo = new Todo(input);
            taskList.add(todo);
            replyUser("The following to-do has been added:\n    " + todo.toString() + str + taskList.size());

        } else if (type.equals("D")) {
            String[] arr = input.split("/", 2);
            if (arr.length > 1) {
                String description = arr[0];
                String by = arr[1].split(" ", 2)[1];
                if (isLocalDate(by)) {
                    LocalDate deadlineDate = LocalDate.parse(by);
                    Deadline deadline = new Deadline(description, deadlineDate);
                    taskList.add(deadline);
                    replyUser("The following task has been added:\n    " + deadline.toString() + str + taskList.size());
                } else {
                    throw new DukeTaskException("Invalid date format detected. Please ensure date is in yyyy-mm-dd (e.g. 2019-01-30).");
                }
            } else {
                throw new DukeTaskException("\'/by\' field is missing.");
            }

        } else if (type.equals("E")) {
            String[] arr = input.split("/", 2);
            if (arr.length > 1) {
                String description = arr[0];
                String at = arr[1].split(" ", 2)[1];
                if (isLocalDate(at)) {
                    LocalDate eventDate = LocalDate.parse(at);
                    Event event = new Event(description, eventDate);
                    taskList.add(event);
                    replyUser("The following task has been added:\n    " + event.toString() + str + taskList.size());
                } else {
                    throw new DukeTaskException("Invalid date format detected. Please ensure date is in yyyy-mm-dd (e.g. 2019-02-28).");
                }
            } else {
                throw new DukeTaskException("\'/at\' field is missing.");
            }
        }
        // Overwrite stored file
        writeTaskToMemory(FILEPATH);
    }

    public static boolean isLocalDate(String input) {
        try {
            LocalDate.parse(input);
        } catch (DateTimeParseException err) {
            return false;
        }
        return true;
    }

    // Prints the user's task list.
    public static void showList() {
        if (taskList.isEmpty()) {
            replyUser("Your task list is empty.");
        } else {
            System.out.println("\n------------------------------------------");
            System.out.println("Here is your list of tasks: ");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                System.out.println(index + "." + task.toString());
            }
            System.out.println("------------------------------------------\n");
        }
    }

    // Mark the specified task as done.
    public static void markTaskAsDone(String userIndex) throws DukeArgumentException {
        int index = Integer.parseInt(userIndex) - 1;
        if (taskList.isEmpty()) {
            replyUser("There is no task in your list to be marked as done.");
        } else if (index < taskList.size()) {
            Task t = taskList.get(index);
            t.markAsDone();
            replyUser("As per requested, the following task has been marked as done:\n" + "    " + t.toString());
        } else {
            throw new DukeArgumentException("Please provide a number between 1 and " + taskList.size() + ".");
        }
        // Overwrite stored file
        writeTaskToMemory(FILEPATH);
    }

    // Delete the specified task from the list.
    public static void deleteTask(String userIndex) throws DukeArgumentException {
        int index = Integer.parseInt(userIndex) - 1;
        if (taskList.isEmpty()) {
            replyUser("There is no task in your list to be deleted.");
        } else if (index < taskList.size()) {
            Task t = taskList.get(index);
            taskList.remove(index);
            replyUser("As per requested, the following task has been deleted:\n" + "    " + t.toString() + "\nCurrent number of task(s): " + taskList.size());
        } else {
            throw new DukeArgumentException("Please provide a number between 1 and " + taskList.size() + ".");
        }
        // Overwrite stored file
        writeTaskToMemory(FILEPATH);
    }

    // Read and get Tasks from file.
    public static void getTaskFromMemory(String filepath) throws FileNotFoundException {
        File f = new File(filepath);
        Scanner fs = new Scanner(f);
        while (fs.hasNextLine()) {
            String currentLine = fs.nextLine();
            String[] input = currentLine.split(" ", 2);
            char command = input[0].charAt(1);
            boolean isDone = (input[0].charAt(4) == 'O');

            if (command == 'T') {
                Task t = new Todo(input[1]);
                if (isDone) {
                    t.markAsDone();
                }
                taskList.add(t);
            } else if (command == 'D') {
                int byStart = input[1].indexOf(" (by:");
                String description = input[1].substring(0, byStart);
                String by = input[1].substring(byStart + 6, input[1].length() - 1);
                LocalDate byDate = LocalDate.parse(by, DATEFORMAT);
                Task t = new Deadline(description, byDate);
                if (isDone) {
                    t.markAsDone();
                }
                taskList.add(t);
            } else if (command == 'E') {
                int atStart = input[1].indexOf(" (at:");
                String description = input[1].substring(0, atStart);
                String at = input[1].substring(atStart + 6, input[1].length() - 1);
                LocalDate atDate = LocalDate.parse(at, DATEFORMAT);
                Task t = new Event(description, atDate);
                if (isDone) {
                    t.markAsDone();
                }
                taskList.add(t);
            }
        }
    }

    // Write the current taskList to file.
    public static void writeTaskToMemory(String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath);
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < taskList.size(); i++) {
                Task current = taskList.get(i);
                sb.append(current.toString() + "\n");
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Booting up...\n" + logo);
        System.out.println("How can I serve you?\n");

        try {
            getTaskFromMemory(FILEPATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                String command = sc.nextLine();
                String[] commandArray = command.split(" ", 2);

                if (command.toLowerCase().equals("bye")) {
                    replyUser("I believe this is farewell, my friend.");
                    break;
                } else if (command.toLowerCase().equals("list")) {
                    showList();
                } else if (commandArray[0].toLowerCase().equals("done")) {
                    if (commandArray.length >= 2) {
                        markTaskAsDone(commandArray[1]);
                    } else {
                        throw new DukeArgumentException("Please specify which task to be marked as done.");
                    }
                } else if (commandArray[0].toLowerCase().equals("delete")) {
                    if (commandArray.length >= 2) {
                        deleteTask(commandArray[1]);
                    } else {
                        throw new DukeArgumentException("Please specify which task to be deleted.");
                    }
                } else if (commandArray[0].toLowerCase().equals("todo")) {
                    if (commandArray.length >= 2) {
                        addTask(commandArray[1], "T");
                    } else {
                        throw new DukeArgumentException("Missing field in todo command.");
                    }
                } else if (commandArray[0].toLowerCase().equals("deadline")) {
                    if (commandArray.length >= 2) {
                        addTask(commandArray[1], "D");
                    } else {
                        throw new DukeArgumentException("Missing field in deadline command.");
                    }
                } else if (commandArray[0].toLowerCase().equals("event")) {
                    if (commandArray.length >= 2) {
                        addTask(commandArray[1], "E");
                    } else {
                        throw new DukeArgumentException("Missing field in event command.");
                    }
                } else {
                    throw new DukeUnknownException("Apologies, I do not recognise this command.");
                }
            } catch (DukeException err) {
                replyUser(err.getMessage());
            }
        }
    }
}