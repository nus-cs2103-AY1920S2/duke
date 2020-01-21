import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();

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
                for (int i = 0; i < inputs.size(); i++) {
                    printIndented(i + 1 + ". " + inputs.get(i));
                }
                printHorizontalLine();
                break;

            case "blah":
                printHorizontalLine();
                printIndented("blah");
                printHorizontalLine();
                break;

            default:
                inputs.add(input);
                printHorizontalLine();
                printIndented("added: " + input);
                printHorizontalLine();
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
