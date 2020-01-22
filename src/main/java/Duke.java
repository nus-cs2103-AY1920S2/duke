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

        String[] tasks = new String[100];
        int tasksIdx = 0;

        // commands: list, bye
        // any other input will be added to tasks
        String input = getUserInput();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList(tasks, tasksIdx - 1);
            } else {
                tasks[tasksIdx] = input;
                tasksIdx++;

                System.out.format("added: %s%n%n", input);
            }

            input = getUserInput();
        }

        System.out.println("Have a nice day!");
    }

    public static void printList(String[] list, int lastIdx) {
        for (int i = 0; i <= lastIdx; i++) {
            System.out.format("%d. %s%n", i + 1, list[i]);
        }
        System.out.println();
    }
}
