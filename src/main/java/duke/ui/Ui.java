package duke.ui;

import java.util.Scanner;

public class Ui {
    private final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n" + "|____/ \\__._|_|\\_\\___|\n";
    private final String logoDivider = "++++++++++++++++++++++\n";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(logoDivider + logo + "\n" + logoDivider);
        this.dukePrompt(new String[]{"Hey boss! Duke here, at your service.", "What do you need me to do?"});
        System.out.println();
    }

    public void dukePrompt(String prompt) {
        System.out.println("Duke: " + prompt);
    }

    public void dukePrompt(String[] prompts) {
        for (int i = 0; i < prompts.length; i++) {
            if (i == 0) {
                System.out.println("Duke: " + prompts[i]);
            } else {
                System.out.println("      " + prompts[i]);
            }
        }
    }

    public String readUserInput() {
        System.out.printf("\nYou:  ");
        String line = scanner.nextLine();
        System.out.println();
        return line;
    }

    public void showError(String errorMessage) {
        dukePrompt(errorMessage);
    }
}