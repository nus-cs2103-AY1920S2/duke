import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readInput() {

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
                    ui.sayBye();
                    break;
                case "list":
                    ui.printTaskList(taskListString);
                    break;
                case "done":
                    // Read the task number as the next element of splitInput
                    int taskNumber = Integer.parseInt(splitInput[1]);
                    ui.markTaskAsDone(taskListString, taskNumber, filePath);
                    break;
                case "delete":
                    int taskNumberDelete = Integer.parseInt(splitInput[1]);
                    ui.deleteTask(taskListString, taskNumberDelete, filePath);
                case "todo":
                case "deadline":
                case "event":
                    ui.addTaskToList(taskListString, splitInput, filePath);
                    break;
                default:
                    ui.flagWrongCommand();
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
    public void sayHello() {
        // Print welcome message
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

    }
    public void sayBye() {
        // Print goodbye message
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public void printTaskList(ArrayList<Task> taskList) {
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
                Task currTask = taskList.get(i);
                System.out.println(currTask);
            }
        }
        System.out.println("\t____________________________________________________________");
    }

    public void updateFile(ArrayList<Task> taskList, Path filePath) {
        try {
            File file = new File(filePath.toString());
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task t: taskList) {
                String taskString = t.toString();
                bw.write(taskString);
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void markTaskAsDone(ArrayList<Task> taskList, int taskNumber, Path filePath) {
        // Get the task from the list
        Task requestedTask = taskList.get(taskNumber - 1);
        // Mark the task as done
        requestedTask.setDone();
        //StringBuilder sb = new StringBuilder(requestedTask);
        //sb.setCharAt(4,'\u2713');
        //String updatedTask = sb.toString();
        //taskList.set(taskNumber - 1, updatedTask);

        System.out.println("\t____________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done:");
        //System.out.println("\t" + updatedTask);
        System.out.println("\t" + requestedTask);
        System.out.println("\t____________________________________________________________");
        // Update the file
        updateFile(taskList, filePath);
    }

    public void deleteTask(ArrayList<Task> taskList, int taskNumber, Path filePath) {
        // Get the task from the list
        Task taskToDelete = taskList.get(taskNumber - 1);
        taskList.remove(taskToDelete);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t  " + taskToDelete);
        System.out.println("\t Now you have " + (taskList.size() + 1) + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
        // Update the file
        updateFile(taskList, filePath);

    }

    public void addTaskToList(ArrayList<Task> taskList, String[] splitInput, Path filePath) throws DukeException {


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

    public void printTaskAddSuccess(Task task, int taskListSize) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + (taskListSize + 1) + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    public void flagWrongCommand() throws DukeException {
        String sorryStr = "Sorry! You've entered a wrong command, please try again!\n";
        String helpStr = "\t List of commands: \n" + "\t  todo\n" + "\t  event\n"
                + "\t  deadline\n" + "\t  list\n";
        throw new DukeException(sorryStr + helpStr);
    }
}
