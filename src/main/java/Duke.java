import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // Attributes
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Duke.runDuke();
    }

    // Carry out Add, List, Done commands if entered by user
    // Terminates when user gives exit signal
    private static void runDuke() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String nextInput = scanner.nextLine();
            // Validation check
            if (nextInput.equals("bye")) {
                Duke.stopDuke();
                break;
            }
            // Handle List command if any
            if (nextInput.equals("list")) {
                // fflush
                System.out.println("");
                Duke.handleCommandList();
                continue;
            }

            // Check and Handle Done command
            // Possible source of error
            String[] doneCommandArgs = nextInput.split(" ");
            if (doneCommandArgs[0].equals("done")) {
                handleCommandDone(Integer.parseInt(doneCommandArgs[1]));
                continue;
            }

            // Handle Add Command
            Task t = new Task(nextInput);
            Duke.handleCommandAdd(t);
        }
    }

    // Print a closing message before stopping Duke
    private static void stopDuke() {
        String closingMessage = "Bye. Hope to see you again soon!";
        System.out.println("    " + closingMessage);
    }

    private static void handleCommandList() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task s : Duke.taskList) {
            System.out.println("    " + counter + ". " + s);
            counter++;
        }
    }

    private static void handleCommandAdd(Task newTask) {
        Duke.taskList.add(newTask);
        System.out.println("    added: " + newTask.getDescription());
    }

    private static void handleCommandDone(int TaskNumber) {
        Duke.taskList.get(TaskNumber - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + "    " + Duke.taskList.get(TaskNumber - 1));

    }
}
