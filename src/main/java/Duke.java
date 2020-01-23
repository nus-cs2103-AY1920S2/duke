import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        printGreeting();
        runDuke();
    }

    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hi, I'm Duke!");
        System.out.println("What can I do for you?\n");
    }

    public static void runDuke() {
        Scanner sc = new Scanner(System.in);

        System.out.print("> ");
        String input = sc.nextLine();

        String command = extractCommand(input);  // commands: bye, list, done, todo, deadline, event

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                Task.printTasks();
            } else if (command.equals("done")) {
                int doneTaskNum = Integer.parseInt(extractFirstParam(input));
                Task doneTask = Task.tasks[doneTaskNum - 1];
                doneTask.markAsDone();
                
                Task.printMarkedAsDone(doneTask);
            } else if (command.equals("todo")) {
                String description = extractDescription(input);
                Task todo = new Todo(description);

                Task.addTask(todo);
                Task.printAddedTask(todo);
            } else if (command.equals("deadline")) {
                String description = extractDescription(input);
                String by = extractTime(input);

                Task deadline = new Deadline(description, by);
                Task.addTask(deadline);
                Task.printAddedTask(deadline);
            } else if (command.equals("event")) {
                String description = extractDescription(input);
                String at = extractTime(input);

                Task event = new Event(description, at);
                Task.addTask(event);
                Task.printAddedTask(event);
            } else {
                System.out.println("\tI'm sorry! I don't recognize that command. Please try again.\n");
            }

            System.out.print("> ");
            input = sc.nextLine();
            command = extractCommand(input);
        }

        System.out.println("\tHave a nice day!");
    }

    public static String extractCommand(String input) {
        return input.split(" ")[0];
    }

    public static String extractDescription(String input) {
        String descPortion = input.split(" /")[0];
        String[] descPortionArr = descPortion.split(" ");
        String[] descArr = Arrays.copyOfRange(descPortionArr, 1, descPortionArr.length);

        return String.join(" ", descArr);
    }

    public static String extractTime(String input) {
        String timePortion = input.split(" /")[1];
        String[] timePortionArr = timePortion.split(" ");
        String[] timeParamArr = Arrays.copyOfRange(timePortionArr, 1, timePortionArr.length);

        return String.join(" ", timeParamArr);
    }

    public static String extractFirstParam(String input) {
        return input.split(" ")[1];
    }
}
