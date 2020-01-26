import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    protected static Scanner scanner;
    protected static List<String> inputs;

    public static void main(String[] args) {
        printWelcomeMessage();

        scanner = new Scanner(System.in);
        inputs = new ArrayList<String>();

        while (true) {
            processInput(scanner);
        }
    }

    public static void printWelcomeMessage() {
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.printWithIndentation("Hello! I'm Duck");
        ProgramOutputHandler.printWithIndentation("What can I do for you?");
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.nextLine();
    }

    public static void printGoodbyeMessage() {
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.printWithIndentation("Bye. Hope to see you again soon!");
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.nextLine();
    }

    public static void echo(String input) {
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.printWithIndentation(input);
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.nextLine();
    }

    private static void listPastInputs() {
        ProgramOutputHandler.printLineSeperator();

        for (int i = 0; i < inputs.size(); ++i) {
            ProgramOutputHandler.printWithIndentation(i + 1 + ". " + inputs.get(i));
        }

        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.nextLine();
    }

    public static void processInput(Scanner sc) {
        String input = sc.nextLine();

        // Exit application
        if (input.equals("bye")) {
            printGoodbyeMessage();
            System.exit(0);
            return;
        }

        // List inputs
        if (input.equals("list")) {
            listPastInputs();
            return;
        }

        // Add input to list
        inputs.add(input);
        echo("added: " + input);
    }
}
