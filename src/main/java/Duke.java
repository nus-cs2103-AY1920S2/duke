import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    // attributes
    public TaskStorage taskStorage;

    public Duke(String filePath) {
        try {
            this.taskStorage = new TaskStorage(filePath);
        } catch (FileNotFoundException e) {
            System.out.println(filePath);
            System.out.println("File not found");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Duke duke = new Duke("./data/Storage.txt");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        duke.runDuke();
    }

    // Carry out Add, List, Done commands if entered by user
    // Terminates when user gives exit signal
    private void runDuke() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String nextInput = scanner.nextLine();
            // Validation check
            if (nextInput.equals("bye")) {
                stopDuke();
                break;
            }
            // Handle List command if any
            if (nextInput.equals("list")) {
                handleCommandList();
                continue;
            }

            // Check and Handle Done command
            // Possible source of error
            String[] commandArgs = nextInput.split(" ");

            if (commandArgs[0].equals("done")) {
                // Todo: Add exception handling
                handleCommandDone(Integer.parseInt(commandArgs[1]));
                continue;
            }

            if (commandArgs[0].equals("delete")) {
                // Todo: Add exception handling
                handleCommandDelete(Integer.parseInt(commandArgs[1]));
                continue;
            }

            // Handle Add Command
            Task t = null;
            try {
                t = TaskDispatch.dispatchTaskFromInput(commandArgs);
            } catch (IllegalArgumentException e1){
                System.out.println("    " + e1.getMessage());
                continue;
            } catch (IllegalCommandException e2) {
                System.out.println("    " + e2.getMessage());
                continue;
            }

            handleCommandAdd(t, nextInput);
        }
    }

    // Print a closing message before stopping Duke
    private static void stopDuke() {
        String closingMessage = "Bye. Hope to see you again soon!";
        System.out.println("    " + closingMessage);
    }

    // Todo: abstract away the following logic from main via new interface for command handlers

    private void handleCommandList() {
        System.out.println("    Here are the tasks in your list:");
        int counter = 1;
        for (Task task : this.taskStorage.getTaskList()) {
            System.out.println("    " + counter + ". " + task);
            counter++;
        }
    }

    private void handleCommandAdd(Task newTask, String nextInput) {
        this.taskStorage.addToTaskList(newTask, nextInput);
        System.out.println("    Got it. I've added this task:\n"
                + "      " + newTask);
        System.out.println("    Now you have " + this.taskStorage.getTaskList().size() + " tasks in the list.");
    }

    private void handleCommandDone(int taskNumber) {
        this.taskStorage.markTaskAsDone(taskNumber);
        System.out.println("Nice! I've marked this task as done:\n"
                + "    " + this.taskStorage.getTaskList().get(taskNumber - 1));
    }

    private void handleCommandDelete(int taskNumber) {
        System.out.println("    Noted. I've removed this task:\n"
                + "    " + this.taskStorage.getTaskList().get(taskNumber - 1));
        this.taskStorage.deleteFromTaskList(taskNumber);
        System.out.println("    Now you have " + this.taskStorage.getTaskList().size() + " tasks in the list.");
    }
}
