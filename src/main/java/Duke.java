import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            FileUtils.readFile(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        printHorizontalLine();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printHorizontalLine();
        System.out.println();

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals(("list"))) {
                printHorizontalLine();
                printIndented("Here are the tasks in your list: ");
                for (int i = 0; i < tasks.size(); i++) {
                    printIndented(String.format("%d.%s", i + 1, tasks.get(i)));
                }
                printHorizontalLine();
            } else if (getCommand(input, 4).equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).doTask();

                printHorizontalLine();
                printIndented("Nice! I've marked this task as done: ");
                printIndented(" " + tasks.get(index));
                try {
                    FileUtils.doTask(index);
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }

                printHorizontalLine();
            } else if (getCommand(input, 6).equals("delete")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                printHorizontalLine();
                printIndented("Noted. I've removed this task: ");
                printIndented(" " + tasks.remove(index));
                try {
                    FileUtils.deleteTask(index);
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }

                printHorizontalLine();
            } else if (getCommand(input, 4).equals("todo") ||
                    getCommand(input, 8).equals("deadline") ||
                    getCommand(input, 5).equals("event")) {
                try {
                    String typeRemoved = input.strip().split(" ", 2)[1];
                    String type = input.strip().split(" ", 2)[0];

                    Task task = type.equals("todo")
                            ? new ToDo(typeRemoved)
                            : type.equals("deadline")
                            ? new Deadline(typeRemoved)
                            : new Event(typeRemoved);
                    tasks.add(task);

                    printHorizontalLine();
                    printIndented("Got it. I've added this task: ");
                    printIndented(" " + task);
                    printIndented(String.format("Now you have %d tasks in the list.", tasks.size()));

                    FileUtils.addTask(input);

                    printHorizontalLine();
                } catch (IndexOutOfBoundsException e) {
                    printHorizontalLine();
                    printIndented("☹ OOPS!!! The description of a " +
                            input.split(" ")[0] + " cannot be empty.");
                    printHorizontalLine();
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else {
                printHorizontalLine();
                printIndented("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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

    public static String getCommand(String input, int indexLast) {
        return input.substring(0, Math.min(indexLast, input.length()));
    }
}
