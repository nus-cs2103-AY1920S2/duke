import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

//        System.out.println("Hello from\n" + logo);

        printFormattedOutput("Hello! I'm Duke\n    What can I do for you?");

        // Chat logic
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Task[] list = new Task[100];
        int numberOfTasks = 0;

        while (!input.equals("bye")) {
            String action = input.split(" ")[0];

            switch (action) {
                case "list":
                    printList(list, numberOfTasks);
                    break;
                case "done":
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    if (taskNumber < 1 || taskNumber > numberOfTasks) {
                        printFormattedOutput("Please input a valid task number between 1 - " + numberOfTasks);
                        continue;
                    }
                    list[taskNumber - 1].markAsDone();
                    printDone(list[taskNumber - 1]);
                    break;
                case "todo":
                    Task newTodo =  new Todo(input.split("todo ")[1]);
                    list[numberOfTasks] = newTodo;
                    numberOfTasks++;
                    printNewTask(newTodo, numberOfTasks);
                    break;
                case "event":
                    String event = input.split("event ")[1];
                    String[] fields = event.split("/at ");
                    Task newEvent =  new Event(fields[0], fields[1]);
                    list[numberOfTasks] = newEvent;
                    numberOfTasks++;
                    printNewTask(newEvent, numberOfTasks);
                    break;
                case "deadline":
                    String deadline = input.split("deadline ")[1];
                    fields = deadline.split("/by ");
                    Task newDeadline =  new Deadline(fields[0], fields[1]);
                    list[numberOfTasks] = newDeadline;
                    numberOfTasks++;
                    printNewTask(newDeadline, numberOfTasks);
                    break;
                default:
                    printFormattedOutput("Please enter a valid action!");
                    break;
            }

            input = sc.nextLine();
        }

        printFormattedOutput("Bye. Hope to see you again soon!");

    }

    // Print formatters

    public static void printFormattedOutput(String output) {
        String bar = "    *****************************************************\n";

        System.out.println(bar + "    " + output + "\n" + bar);
    }

    public static void printList(Task[] list, int size) {
        String bar = "    *****************************************************\n";
        System.out.print(bar);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            System.out.println("    " + (i + 1) + ". " + list[i]);
        }
        System.out.println(bar);
    }

    public static void printNewTask(Task task, int sizeOfList) {
        String bar = "    *****************************************************\n";
        System.out.print(bar);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + sizeOfList + " tasks on the list.");
        System.out.println(bar);
    }

    public static void printDone(Task task) {
        String bar = "    *****************************************************\n";
        System.out.print(bar);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println(bar);
    }
}
