package duke.ui;

import java.util.Scanner;

/**
 * Displays output to the user in text (CLI).
 */
public class TextUi implements Ui {
    private String lineBreak = "===========================================================\n";
    private Scanner sc = new Scanner(System.in);

    public void showReply(String reply) {
        System.out.print(formatReply(reply));
    }

    public void showError(String error) {
        System.out.print(formatReply(error));
    }

    /**
     * Displays a text greeting to the user.
     */
    public void showGreeting() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("\n");
        sb.append(formatReply("Hello! I'm Duke\nGive me a moment while I locate your save file..."));
        System.out.print(sb.toString());
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void shutDown() {
        sc.close();
    }

    private String formatReply(String str) {
        String[] lines = str.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append("> ");
            sb.append(line);
            sb.append("\n");
        }
        sb.append(lineBreak);
        return sb.toString();
    }
}