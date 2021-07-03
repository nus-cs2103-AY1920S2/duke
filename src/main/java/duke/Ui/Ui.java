package duke.Ui;

/**
 * Scans input from the user and prints text feedback to the user.
 */
public class Ui {

    /**
     * Returns welcome message to be displayed on app start up.
     * @return welcome message.
     */
    public static String showWelcomeMessage() {
        String welcomeMessage = " Hello! I'm Alfred.\n"
                + " What can I do for you, master?\n\n"
                + " When in doubt, type \"help\"";
        return welcomeMessage;
    }

    /**
     * Returns goodbye message to be displayed on app closure.
     * @return goodbye message.
     */
    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the error message to be displayed to the user.
     * @param exception
     * @return Error Message.
     */
    public String showErrorMessage(Exception exception) {
        return exception.getMessage();
    }

    /**
     * Returns help message to be displayed when user types help command.
     * @return Help Message.
     */
    public String showHelpMessage() {
        String helpMessage = " Commands Available:\n"
                + "  >\"list\" - Lists all the current Tasks\n"
                + "  >\"done\" - Marks a Task as complete\n"
                + "  >\"delete\" - Removes an existing Task\n"
                + "  >\"priority\" - Sorts Tasks by priority\n"
                + "  >\"todo\" - Adds a Todo type Task\n"
                + "  >\"event\" - Adds a Event type Task\n"
                + "  >\"deadline\" - Ads a Deadline type Task\n\n"
                + " Still clueless? Type a Command for details!\n"
                + " Still clueless? Open the user guide!";
        return helpMessage;

    }
}
