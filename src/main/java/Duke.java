import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printHorizontalLine();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printHorizontalLine();
        System.out.println();

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            switch (input) {
            case "list":
                printHorizontalLine();
                printIndented("list");
                printHorizontalLine();
                break;

            case "blah":
                printHorizontalLine();
                printIndented("blah");
                printHorizontalLine();
                break;

            default:
                break;
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
