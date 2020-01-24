import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Print welcome message
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        // Create list
        ArrayList<Task> taskList = new ArrayList<>();

        boolean endInput = false;

        // Create Scanner object
        Scanner sc = new Scanner(System.in);
        while (!endInput) {
            // Read user input
            String input = sc.nextLine();
            String[] splitInput = input.split(" ");
            // Get the command
            String cmd = splitInput[0];

            switch (cmd) {
            case "bye":
                endInput = true;
                break;
            case "list":
                // Print out the list
                System.out.println("\t____________________________________________________________");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    // Print task number first
                    System.out.print("\t" + (i + 1) + ".");
                    // Get the task and print it
                    Task currTask = taskList.get(i);
                    System.out.println(currTask);
                }
                System.out.println("\t____________________________________________________________");
                break;
            case "done":
                // Read the task number as the next element of splitInput
                int taskNumber = Integer.parseInt(splitInput[1]);

                // Get the task from the list
                Task requestedTask = taskList.get(taskNumber - 1);

                // Mark the task as done
                requestedTask.setDone();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + requestedTask);
                System.out.println("\t____________________________________________________________");
                break;
            default:
                // If the cmd is none of the above, add the task into list
                Task task = new Task(input);
                taskList.add(task);
                System.out.println("\t____________________________________________________________");
                System.out.println("\tadded: " + input);
                System.out.println("\t____________________________________________________________");
                break;
            }

        }

        // Exited the loop, print goodbye message
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");


    }
}
