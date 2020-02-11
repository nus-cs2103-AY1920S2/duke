package duke.util;

import java.util.Scanner;

public class Ui {
    private static final String SEPERARION_LINE = "_".repeat(50) + "\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private String formatMessage(String message) {
        return SEPERARION_LINE + message + SEPERARION_LINE;
    }

    public void showMessage(String message) {
        System.out.println(formatMessage(message));
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello! I'm\n" + logo + "\nWhat can I do for you?\n";
        showMessage(greetings);
    }

    public void showFarewell() {
        String message = "Hope to see you next time! xD\n";
        showMessage(message);
    }

    public void showLoadingError() {
        String message = "Failed to load the existing tasks... can you check if the file is still there? :p\n";
        showMessage(message);
    }

    public void showError(String message) {
        showMessage(message);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }
}
