package duke.Ui;

public class Ui {

    public static String showWelcomeMessage() {
        String welcomeMessage = " Hello! I'm Alfred.\n"
                + " What can I do for you, master?\n\n"
                + " When in doubt, type \"help\"";
        return welcomeMessage;
    }

    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String showErrorMessage(Exception e) {
        return e.getMessage();
    }
}
