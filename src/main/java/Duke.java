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

    private static void printTasks(ArrayList<Task> tasks) {
//        hLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
//        hLine();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String input = scanner.nextLine();
        String[] inputsplit = input.split(" ");

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printTasks(tasks);
            } else if (inputsplit[0].equals("done")) {
                int doneId = Integer.parseInt(inputsplit[1]);
                tasks.get(doneId - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        tasks.get(doneId - 1).toString());

            } else {
                System.out.println("added: " + input);
                tasks.add(new Task(input));
            }

            input = scanner.nextLine();
            inputsplit = input.split(" ");
        }

        printWithHLine("Bye. Hope to see you again soon!");
    }

}