import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static File listFile;
    static ArrayList<Task> taskList;
    public static void main(String[] args) {
        sayHello();

        // Load the file

        String currDir = System.getProperty("user.dir");
        Path filePath =  Paths.get(currDir, "list.txt");
        String fileString = loadFileString(filePath);
        String[] lines = fileString.split("\\r?\\n");
        //taskList = populateTaskList(lines);
        boolean endInput = false;

        // Create Scanner object to read for user input
        Scanner sc = new Scanner(System.in);

        // Only read and write to the file whenever there is a modification to the task
        while (sc.hasNextLine()) {

            // Read user input
            String input = sc.nextLine();
            //separate the first word, which will be the command, from the rest of the line
            String[] splitInput = input.split(" ", 2);
            // Get the command
            String cmd = splitInput[0];

            try {
                switch (cmd) {
                case "bye":
                    endInput = true;
                    sayBye();
                    break;
                case "list":
                    printTaskList(taskList);
                    break;
                case "done":
                    // Read the task number as the next element of splitInput
                    int taskNumber = Integer.parseInt(splitInput[1]);
                    markTaskAsDone(taskList, taskNumber, listFile);
                    break;
                case "delete":
                    int taskNumberDelete = Integer.parseInt(splitInput[1]);
                    deleteTask(taskList, taskNumberDelete);
                case "todo":
                case "deadline":
                case "event":
                    //hi
                    addTaskToList(taskList, splitInput);
                    break;
                default:
                    flagWrongCommand();
                    break;
                }
            } catch (DukeException dukeErr) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t " + dukeErr.getMessage());
                System.out.println("\t____________________________________________________________");
            }


            if (endInput) {
                break;
            }

        }

        sc.close();

    }

    public static String loadFileString(Path filePath) {
        String text = "";
        try  {
            text = new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }


    public static void sayHello() {
        // Print welcome message
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

    }
    public static void sayBye() {
        // Print goodbye message
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        // Print out the list
        System.out.println("\t____________________________________________________________");
        if (taskList.size() == 0) {
            System.out.println("\t Your task list is empty!");
        } else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                // Print task number first
                System.out.print("\t " + (i + 1) + ".");
                // Get the task and print it
                Task currTask = taskList.get(i);
                System.out.println(currTask);
            }
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void markTaskAsDone(ArrayList<Task> taskList, int taskNumber, File listFile) {
        // Get the task from the list
        Task requestedTask = taskList.get(taskNumber - 1);
        String taskBeforeDone = requestedTask.toString();
        // Mark the task as done
        requestedTask.setDone();
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t" + requestedTask);
        System.out.println("\t____________________________________________________________");

        // Update the file
        try {
            FileReader fr = new FileReader(listFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == (taskNumber - 1)) {
                    //replace the cross with a tick
                    line.replace("\u2718", "\u2713");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteTask(ArrayList<Task> taskList, int taskNumber) {
        // Get the task from the list
        Task taskToDelete = taskList.get(taskNumber - 1);
        taskList.remove(taskToDelete);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t  " + taskToDelete);
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
        //update file
        try {
            FileOutputStream fo = new FileOutputStream(listFile);
        }
    }
    public static void addTaskToList(ArrayList<Task> taskList, String[] splitInput) throws DukeException {
        // Now, we must check what kind of task is added, it will be the first element of splitInput
        // Must check if the input is valid
        String type = splitInput[0];
        try {
            String task = splitInput[1];
            switch (type) {
            case "todo":
                // Create a ToDo task
                ToDo todo = new ToDo(task);
                taskList.add(todo);
                printTaskAddSuccess(todo, taskList.size());
                break;
            case "deadline":
                Deadline deadline = new Deadline(task);
                taskList.add(deadline);
                printTaskAddSuccess(deadline, taskList.size());
                break;
            case "event":
                Event event = new Event(task);
                taskList.add(event);
                printTaskAddSuccess(event, taskList.size());
                break;
            default:
                break;
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            // Exception will be thrown when the task is given with empty body
            String errStringFront = "Sorry! The description of a ";
            String addString = "";
            switch(type) {
            case "todo":
                addString = "todo cannot be empty!";
                break;
            case "deadline":
                addString = "deadline cannot be empty!";
                break;
            case "event":
                addString = "event cannot be empty!";
                break;
            default:
                break;
            }

            String errString = errStringFront + addString;
            throw new DukeException(errString);
        }

    }

    private static void printTaskAddSuccess(Task task, int taskListSize) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + taskListSize + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    private static void flagWrongCommand() throws DukeException {
        String sorryStr = "Sorry! You've entered a wrong command, please try again!\n";
        String helpStr = "\t List of commands: \n" + "\t  todo\n" + "\t  event\n"
                    + "\t  deadline\n" + "\t  list\n";
        throw new DukeException(sorryStr + helpStr);
    }
}

