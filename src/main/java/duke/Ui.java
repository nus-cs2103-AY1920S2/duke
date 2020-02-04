package duke;

import java.util.Scanner;

public class Ui {

    private String botName;
    private Scanner in;

    public Ui(String botName) {
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

    public void showMessage(String message) {
        System.out.println(botName + ": " + message);
    }

    public void showPrompt() {
        System.out.print("You: ");
    }

    public void newLine() {
        System.out.println();
    }

    public String readCommand() {
        return in.nextLine().strip();
    }

    public void close() {
        in.close();
    }
}