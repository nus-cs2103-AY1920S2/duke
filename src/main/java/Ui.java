/**
 * Class Ui to handle the user interface of our ChatBot.
 * Currently, Ui is mainly responsible for System.out.println statements to our output.
 */
public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
    }

    public String showWelcomeMessage() {
        return "Hello from\n" + LOGO + "I am here to help you with anything you need!";
    }

    public String showLineOfUnderscores() {
        return "____________________________________________________________";
    }

    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String showEmptyDescription(String type) {
        return "☹ OOPS!!! The description of a " + type + " cannot be empty.";
    }

    public String showInvalidRemoval() {
        return "This item is not valid to remove.";
    }

    public String showCommandNotFound() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public String showMissingParemeters() {
        return "☹ OOPS!!! Either filter criterion (date/month/year) or its value is missing. Please try again.";
    }
}
