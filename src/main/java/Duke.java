import javafx.application.Application;

import java.util.Scanner;

public class Duke {

    protected static Scanner scanner;

    public static void main(String[] args) {
        printWelcomeMessage();

        scanner = new Scanner(System.in);

        while (true)  {
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

    public static void processInput(Scanner sc) {
        String input = sc.nextLine();
        if (input.equals("bye")) {
            printGoodbyeMessage();
            System.exit(0);
            return;
        }

        echoInput(input);
    }

    public static void echoInput(String input) {
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.printWithIndentation(input);
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.nextLine();
    }
}
