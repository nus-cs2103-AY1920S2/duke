import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ui {
    /**
     * Starts the user interface.
     * @throws IOException Thrown when I/O error occurs
     */
    public static void run() throws IOException {
        TaskList tasks = new TaskList();

        Storage.readFile(tasks);

        printHorizontalLine();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printHorizontalLine();
        System.out.println();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Parser parse = new Parser(input, false);

        while (parse.getCommand() != Command.BYE) {
            int index;

            switch (parse.getCommand()) {
            case LIST:
                printHorizontalLine();
                printIndented("Here are the tasks in your list: ");
                for (int i = 0; i < tasks.size(); i++) {
                    printIndented(String.format("%d.%s", i + 1, tasks.get(i)));
                }
                printHorizontalLine();
                break;
            case DONE:
                index = parse.getIndex();
                tasks.doTask(index);

                printHorizontalLine();
                printIndented("Nice! I've marked this task as done: ");
                printIndented(" " + tasks.get(index));

                Storage.doTask(index);

                printHorizontalLine();
                break;
            case DELETE:
                index = parse.getIndex();

                printHorizontalLine();
                printIndented("Noted. I've removed this task: ");
                printIndented(" " + tasks.remove(index));
                Storage.deleteTask(index);


                printHorizontalLine();
                break;
            case FIND:
                try {
                    String searchTerm = parse.getSearchTerm();
                    printHorizontalLine();
                    printIndented("Here are the matching tasks in your list:");
                    int number = 1;
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i).toString().contains(searchTerm)) {
                            printIndented(String.format("%d.%s", number++, tasks.get(i)));
                        }
                    }
                    printHorizontalLine();
                } catch (IndexOutOfBoundsException e) {
                    printHorizontalLine();
                    printIndented("☹ OOPS!!! The search term of a "
                            + parse.getCommandString() + " cannot be empty.");
                    printHorizontalLine();
                }
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    Task task = parse.createTask();
                    tasks.add(task);

                    printHorizontalLine();
                    printIndented("Got it. I've added this task: ");
                    printIndented(" " + task);
                    printIndented(String.format("Now you have %d tasks in the list.", tasks.size()));

                    Storage.addTask(input);

                    printHorizontalLine();
                } catch (IndexOutOfBoundsException e) {
                    printHorizontalLine();
                    printIndented("☹ OOPS!!! The description of a "
                            + parse.getCommandString() + " cannot be empty.");
                    printHorizontalLine();
                }
                break;
            default:
                printHorizontalLine();
                printIndented("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printHorizontalLine();
                break;
            }

            input = sc.nextLine();
            parse = new Parser(input, false);
        }

        printHorizontalLine();
        printIndented("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printIndented(String text) {
        System.out.println("     " + text);
    }

    private static void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }
}
