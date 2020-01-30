import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        sayHello();

        // Load the file
        String currDir = System.getProperty("user.dir");
        Path filePath =  Paths.get(currDir, "list.txt");
        File file = new File(filePath.toString());
        try {
            // create the file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> taskListString = new ArrayList<>(lines);
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
                    printTaskList(taskListString);
                    break;
                case "done":
                    // Read the task number as the next element of splitInput
                    int taskNumber = Integer.parseInt(splitInput[1]);
                    markTaskAsDone(taskListString, taskNumber, filePath);
                    break;
                case "delete":
                    int taskNumberDelete = Integer.parseInt(splitInput[1]);
                    deleteTask(taskListString, taskNumberDelete, filePath);
                case "todo":
                case "deadline":
                case "event":
                    addTaskToList(taskListString, splitInput, filePath);
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

    /*
    public static Task taskFromString(String taskString) {
        Task taskToReturn = null;
        String[] strArr = taskString.split("-");
        ArrayList<String> temp = new ArrayList<>();
        for (String s: strArr) {
            if (!s.equals("")) {
                temp.add(s);
            }
        }
        String type = temp.get(0);
        boolean isDone = temp.get(1).equals("\u2713");

        switch (type){
        case "T":
            // case for ToDo
            StringBuilder sbt = new StringBuilder();
            for (int i = 2; i < temp.size(); i++) {
                sbt.append(temp.get(i));
                if (!(i == temp.size() - 1)) {
                    sbt.append(" ");
                }
            }
            String descT = sbt.toString();
            taskToReturn = new ToDo(descT);
            break;
        case "D":
            // case for Deadline
            StringBuilder sbd = new StringBuilder();

        }

        if (isDone) {
            taskToReturn.setDone();
        }
        return taskToReturn;
    }
    */

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

    public static void printTaskList(ArrayList<String> taskList) {
        // Print out the list
        System.out.println("\t____________________________________________________________");
        if (taskList.size() == 0 || taskList.get(0).equals("")) {
            System.out.println("\t Your task list is empty!");
        } else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                // Print task number first
                System.out.print("\t " + (i + 1) + ".");
                // Get the task and print it
                String currTask = taskList.get(i);
                System.out.println(currTask);
            }
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void updateFile(ArrayList<String> taskList, Path filePath) {
        try {
            File file = new File(filePath.toString());
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String s: taskList) {
                bw.write(s);
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void markTaskAsDone(ArrayList<String> taskList, int taskNumber, Path filePath) {
        // Get the task from the list
        String requestedTask = taskList.get(taskNumber - 1);
        // Mark the task as done
        //requestedTask.setDone();
        StringBuilder sb = new StringBuilder(requestedTask);
        sb.setCharAt(4,'\u2713');
        String updatedTask = sb.toString();
        taskList.set(taskNumber - 1, updatedTask);

        System.out.println("\t____________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t" + updatedTask);
        System.out.println("\t____________________________________________________________");
        // Update the file
        updateFile(taskList, filePath);
    }

    public static void deleteTask(ArrayList<String> taskList, int taskNumber, Path filePath) {
        // Get the task from the list
        String taskToDelete = taskList.get(taskNumber - 1);
        taskList.remove(taskToDelete);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t  " + taskToDelete);
        System.out.println("\t Now you have " + (taskList.size() + 1) + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
        // Update the file
        updateFile(taskList, filePath);

    }
    public static void addTaskToList(ArrayList<String> taskList, String[] splitInput, Path filePath) throws DukeException {
        // Now, we must check what kind of task is added, it will be the first element of splitInput
        // Must check if the input is valid
        String type = splitInput[0];
        String taskString = "";
        try {
            String task = splitInput[1];
            switch (type) {
            case "todo":
                // Create a ToDo task
                ToDo todo = new ToDo(task);
                taskString = todo.toString();
                printTaskAddSuccess(todo, taskList.size());
                break;
            case "deadline":
                Deadline deadline = new Deadline(task);
                taskString = deadline.toString();
                printTaskAddSuccess(deadline, taskList.size());
                break;
            case "event":
                Event event = new Event(task);
                taskString = event.toString();
                printTaskAddSuccess(event, taskList.size());
                break;
            default:
                break;
            }
            StringBuilder sb = new StringBuilder(taskString);
            sb.append("\n");
            taskList.add(sb.toString());
            updateFile(taskList, filePath);
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
        System.out.println("\t Now you have " + (taskListSize + 1) + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    private static void flagWrongCommand() throws DukeException {
        String sorryStr = "Sorry! You've entered a wrong command, please try again!\n";
        String helpStr = "\t List of commands: \n" + "\t  todo\n" + "\t  event\n"
                    + "\t  deadline\n" + "\t  list\n";
        throw new DukeException(sorryStr + helpStr);
    }
}

