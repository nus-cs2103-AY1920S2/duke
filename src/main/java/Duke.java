import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        printHorizontalLine();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printHorizontalLine();
        System.out.println();

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals(("list"))) {
                printHorizontalLine();
                for (int i = 0; i < tasks.size(); i++) {
                    printIndented(String.format("%d.%s", i + 1, tasks.get(i)));
                }
                printHorizontalLine();
            } else if (input.equals(("blah"))) {
                printHorizontalLine();
                printIndented("Here are the tasks in your list: ");
                printIndented("blah");
                printHorizontalLine();
            } else if (input.contains("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).doTask();

                printHorizontalLine();
                printIndented("Nice! I've marked this task as done: ");
                printIndented(" " + tasks.get(index));
                printHorizontalLine();
            } else {
                tasks.add(new Task(input));
                printHorizontalLine();
                printIndented("added: " + input);
                printHorizontalLine();
            }

            input = sc.nextLine();
        }

        printHorizontalLine();
        printIndented("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printIndented(String text) {
        System.out.println("     " + text);
    }

    public static void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }
}
