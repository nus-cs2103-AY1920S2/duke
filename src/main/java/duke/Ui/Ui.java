package duke.Ui;

import duke.command.IllegalCommandException;

public class Ui {

    public static String showWelcome() {
        String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
        return welcomeMessage;
    }

    public String showError() {
        return new IllegalCommandException().getMessage();
    }
}
