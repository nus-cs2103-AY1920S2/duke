import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


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

        while (true) {
            String input = sc.nextLine();

            try {
                String command = getCommand(input);
                if (command.equals("bye")) {
                    break;
                }
                doInstructions(input);
            } catch (DukeException e) {
                System.out.printf("Error: %s\n\n", e.getMessage());
            }
        }
        System.out.println("Bye! Hope you don't come back!");
    }

    public static void doInstructions(String input) throws DukeException {
        Task[] list = new Task[100];
        int numTasks = 0;
        String command = getCommand(input);

        if (command.equals("list")) {
            printList(list, numTasks);
        } else if (command.equals("done")) {
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            // check for
            // 1. invalid task num
            // 2. empty task num
            // 3. if task is already done?
            // implement ui class? if have time to print out done statement
            list[taskNum - 1].markAsDone();
            System.out.println(delimiter());
            System.out.println("I've finally done this task:");
            System.out.println(list[taskNum - 1].toString());
            System.out.println(delimiter() + "\n");
        } else {
            Task newTask;
            // check for
            // 1. no description
            // 2. no time
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

    public static String getCommand(String input) throws DukeException {
        // if command doesnt exist
        String command = input.split(" ")[0];
        List<String> commandList = new ArrayList<>();
        commandList = Arrays.asList("bye", "list", "done", "todo", "deadline", "event");
        if (!commandList.contains(command)) {
            throw new DukeException("No such command");
        } else {
            return command;
        }
    }

    public static String getDescription(String input) throws DukeException {
        String description = input.split("/")[0];
        String[] descArr = description.split(" ");
        if (descArr.length == 1) {
            throw new DukeException("☹ Why did you not provide any description?");
        }
        String[] removedCommand = Arrays.copyOfRange(descArr, 1, descArr.length);
        return String.join(" ", removedCommand);
    }

    public static String getTime(String input) throws DukeException {
        String[] tryGetTime = input.split("/");
        if (tryGetTime.length == 1) {
            throw new DukeException("☹ Why did you not provide the time?");
        }
        String time = tryGetTime[1];
        String[] timeArr = time.split(" ");
        // remove verb
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
