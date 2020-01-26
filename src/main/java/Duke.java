import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTasks = 0;

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
//        Task[] list = new Task[100];
        String command = getCommand(input);

        if (command.equals("list")) {
            printList(numTasks);
        } else if (command.equals("done")) {
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            // check for
            // 1. invalid task num
            // 2. empty task num
            // 3. if task is already done?
            // implement ui class? if have time to print out done statement
            tasks.get(taskNum - 1).markAsDone();
            System.out.println(delimiter());
            System.out.println("I've finally done this task:");
            System.out.println(tasks.get(taskNum - 1).toString());
            System.out.println(delimiter() + "\n");
        } else if (command.equals("delete")) {
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            String desc = tasks.get(taskNum - 1).toString();
            tasks.remove(taskNum - 1);
            numTasks -= 1;
            System.out.println(delimiter());
            System.out.println("Do you really want to remove this? Fine. I've removed:\n    " + desc);
            System.out.printf("Now you have %d tasks in your list\n", numTasks);
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

            tasks.add(newTask);
            System.out.println(tasks);
            numTasks += 1;
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
        commandList = Arrays.asList("bye", "list", "done", "todo", "deadline", "event", "delete");
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

    public static void printList(int numTasks) {
        System.out.println(delimiter());
        System.out.println("Here are the tasks that you will never complete: ");
        for (int i = 0; i < numTasks; i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
        System.out.println(delimiter() + "\n");
    }
}
