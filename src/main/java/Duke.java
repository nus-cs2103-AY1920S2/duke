import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        runDuke();
    }

    public static void greeting() {
        System.out.println(delimiter());
        System.out.println("Hello! I'm Duchess");
        System.out.println("What can I do for you?");
        System.out.println(delimiter() + "\n");
    }

    public static String delimiter() {
        return "___________________________________________________";
    }

    public static void runDuke() {
        greeting();

        Scanner sc = new Scanner(System.in);
        String input;
        Task[] list = new Task[100];
        int numTasks = 0;

        while (!(input = sc.nextLine()).equals("bye")) {
            String command = input.split(" ")[0];

            if (command.equals("list")) {
                printList(list, numTasks);
            } else if (command.equals("done")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                list[taskNum - 1].markAsDone();
                System.out.println(delimiter());
                System.out.println("I've finally done this task:");
                System.out.println(list[taskNum - 1].toString());
                System.out.println(delimiter() + "\n");
            } else {
                Task newTask;
                if (command.equals("todo")) {
                    newTask = new ToDo(getDescription(input));
                } else if (command.equals("deadline")) {
                    newTask = new Deadline(getDescription(input), getTime(input));
                } else { // (command.equals("event")) {
                    newTask = new Event(getDescription(input), getTime(input));
                }

                list[numTasks] = newTask;
                numTasks++;
                System.out.println(delimiter());
                System.out.println("Alrighty, you added:");
                System.out.println("  " + newTask.toString());
                System.out.printf("Now you have %d tasks in your list.\n", numTasks);
                System.out.println(delimiter() + "\n");
            }
        }
        System.out.println("See you soon!");
    }

    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    public static String getDescription(String input) {
        String description = input.split("/")[0];
        String[] descArr = description.split(" ");
        String[] removedCommand = Arrays.copyOfRange(descArr, 1, descArr.length);
        return String.join(" ", removedCommand);
    }

    public static String getTime(String input) {
        String time = input.split("/")[1];
        String[] timeArr = time.split(" ");
        String[] cleaned = Arrays.copyOfRange(timeArr, 1, timeArr.length);
        return String.join(" ", cleaned);
    }

    public static void printList(Task[] list, int numTasks) {
        System.out.println(delimiter());
        System.out.println("Here are the tasks that you will never complete: ");
        for (int i = 1; i <= numTasks; i++) {
            System.out.printf("%d. %s\n", i, list[i - 1]);
        }
        System.out.println(delimiter() + "\n");
    }
}
