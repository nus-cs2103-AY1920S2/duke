package duke.Ui;

public class Ui {

    public static String showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
        return welcomeMessage;
    }

    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String showErrorMessage(Throwable e) {
        return e.getMessage();
    }
}
