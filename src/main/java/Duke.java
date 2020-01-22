import java.util.Scanner;

public class Duke {

    /** The main method is where the chat-bot is created and executed. */
    public static void main(String[] args) {
        displayLogo();
        greet();

        Scanner sc = new Scanner(System.in);
        inputCommands(sc);

        exit();
    }

    /** Outputs the program's logo in the chat-bot. */
    private static void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(indent("Hello from\n" + logo));
    }

    /**
     * The main loop for handling user commands. Stops looping when the given
     * exit command is the next input.
     *
     * @param sc the scanner accepting user input
     */
    public static void inputCommands(Scanner sc) {
        // Terminates the chat-bot if true
        boolean exit = false;

        while (!exit && sc.hasNext()) {
            String command = sc.next();
            System.out.println();

            // Handle different commands
            switch (command) {
            case "bye":
                exit = true; // Terminate the program
                break;
            default:
                echo(command); // Repeat the user's command
                break;
            }
        }
    }

    /** Adds a four-space indent to all lines of a given text. */
    private static String indent(String text) {
        return indent(text, 4);
    }

    /** Adds an indent to all lines of a given text. */
    private static String indent(String text, int indentWidth) {
        String indent = " ".repeat(indentWidth); // Change this number for different indent widths

        return text.replaceAll("(?m)^", indent);
    }

    /** Outputs an indented line in the chat-bot. */
    private static void drawLine() {
        String lineSymbol = "-";
        int lineWidth = 60;

        System.out.println(indent(lineSymbol.repeat(lineWidth)));
    }

    /**
     * Outputs the given message into the chat-bot. The message will be
     * sandwiched between two lines and indented.
     *
     * @param message the text to output into the chat-bot
     */
    public static void echo(String message) {
        drawLine();

        System.out.println(indent(message, 5));

        drawLine();
        System.out.println(); // New line below each chat-bot message
    }

    /** Welcomes the chat-bot user. */
    public static void greet() {
        String greeting = "Hey there! I'm Duke\n"
                + "Your word is my command!";

        echo(greeting);
    }

    /** Farewells the chat-bot user. */
    public static void exit() {
        String farewell = "Bye! Please give a review if you like this program!";

        echo(farewell);
    }
}