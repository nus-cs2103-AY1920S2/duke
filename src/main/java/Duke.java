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

        sayHello();

        // Create list
        ArrayList<Task> taskList = new ArrayList<>();

        boolean endInput = false;

        // Create Scanner object
        Scanner sc = new Scanner(System.in);

        while (!endInput) {
            // Read user input
            String input = sc.nextLine();
            //separate the first word, which will be the command, from the rest of the line
            String[] splitInput = input.split(" ", 2);
            // Get the command
            String cmd = splitInput[0];

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
                markTaskAsDone(taskList, taskNumber);
                break;
            default:
                // If the cmd is none of the above, add the task into list
                addTaskToList(taskList, splitInput);
                break;
            }

        }

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
    }

    public static void printTaskList(ArrayList<Task> taskList) {
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
    }

    public static void markTaskAsDone(ArrayList<Task> taskList, int taskNumber) {
        // Get the task from the list
        Task requestedTask = taskList.get(taskNumber - 1);

        // Mark the task as done
        requestedTask.setDone();
        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + requestedTask);
        System.out.println("\t____________________________________________________________");
    }

    public static void addTaskToList(ArrayList<Task> taskList, String[] splitInput) {
        // Now, we must check what kind of task is added, it will be the first element of splitInput
        String type = splitInput[0];
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
        default: break;
        }


    }

    private static void printTaskAddSuccess(Task task, int taskListSize) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + taskListSize + " tasks in the list");
        System.out.println("\t____________________________________________________________");
    }

}
