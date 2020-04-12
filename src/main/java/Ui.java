/**
 * Class Ui to handle the user interface of our ChatBot.
 * Currently, Ui is mainly responsible for System.out.println statements to our output.
 */
public class Ui {

    public Ui() {
    }

    public String showWelcomeMessage() {
        return "Welcome to the Task Manager! \n"
               + "Fight your way up the bell curve using this efficient task planning application!";
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
        return "Sorry, I didn't get that. Please try again.";
    }

    public String showMissingParemeters() {
        return "OOPS!!! Either filter criterion (date/month/year) or its value is missing. Please try again.";
    }
}
