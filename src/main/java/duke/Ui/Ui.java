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
