import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private static void hLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        hLine();
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        hLine();
    }

    private static void printWithHLine(String message) {
        hLine();
        System.out.println(message);
        hLine();
    }

    private static void printTasks(ArrayList<String> tasks) {
        hLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        hLine();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printTasks(tasks);
            } else {
                printWithHLine("added: " + input);
                tasks.add(input);
            }

            input = scanner.nextLine();
        }

        printWithHLine("Bye. Hope to see you again soon!");
    }

}