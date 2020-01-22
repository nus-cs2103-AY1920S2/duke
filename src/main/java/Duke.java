import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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

    public static String getUserInput() {
        System.out.print("> ");

        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static void runDuke() {
        printGreeting();

        // index 0 -> task #1
        String[] taskList = new String[100];
        Boolean[] isDoneList = new Boolean[100];
        int currTaskIdx = 0;

        // commands: bye, list, done
        // any other input will be added to tasks
        String input = getUserInput();
        while (!input.equals("bye")) {
            String command = input.split(" ")[0];

            if (command.equals("list")) {
                printTasks(taskList, isDoneList, currTaskIdx - 1);
            } else if (command.equals("done")) {
                int doneTaskNum = Integer.parseInt(input.split(" ")[1]);
                isDoneList[doneTaskNum - 1] = true;
                printMarkAsDone(taskList[doneTaskNum - 1]);
            } else {
                taskList[currTaskIdx] = input;
                isDoneList[currTaskIdx] = false;
                currTaskIdx++;

                System.out.format("added: %s%n%n", input);
            }

            input = getUserInput();
        }

        System.out.println("Have a nice day!");
    }

    public static void printTasks(String[] taskList, Boolean[] isDoneList, int lastIdx) {
        for (int i = 0; i <= lastIdx; i++) {
            System.out.format("%d.[%s] %s%n", i + 1, isDoneList[i] ? "✔" : "✘", taskList[i]);
        }
        System.out.println();
    }

    public static void printMarkAsDone(String task) {
        System.out.println("Noted. I have marked this task as done:");
        System.out.format("    [✔] %s%n%n", task);
    }
}
