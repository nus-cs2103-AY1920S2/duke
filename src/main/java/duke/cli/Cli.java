package duke.cli;

import java.util.Scanner;

/**
 * The user interface of the application. Displays messages to the user.
 */
public class Cli {

    private String botName;
    private Scanner in;

    public Cli(String botName) {
        this.botName = botName;
        this.in = new Scanner(System.in);
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String comment = "Your personal assistant chatbot.\n";
        System.out.println("Hello from\n" + logo + comment);
    }

    /**
     * Displays a response message from the Duke bot.
     * @param message The response message.
     */
    public void showMessage(String message) {
        System.out.println(botName + ": " + message);
    }

    /**
     * Displays the prompt before user enters command.
     */
    public void showPrompt() {
        System.out.print("You: ");
    }

    /**
     * Displays a new line.
     */
    public void newLine() {
        System.out.println();
    }

    /**
     * Reads a line of command from the input.
     * @return the String of the command read.
     */
    public String readCommand() {
        return in.nextLine().strip();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        in.close();
    }
}