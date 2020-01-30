package bot;

/**
 * Class that represents the UI of 4LC3N-BOT
 */
public class Ui {
    private static final String VERSION = "4LC3N v0.1.0";
    private static final String LOAD =
            "=======================================================\n"
            + "                  LOADING ... ... ...\n"
            + "=======================================================";
    private static final String GREETINGS = "      _.-'''''-._\n"
            + "    /=_.-~-~-~-._=\\      .-.  _\n"
            + "   :    _     _    :     | | / )\n"
            + "  /    (o)   (o)    \\    | |/ /\n"
            + "  |   _ _ _ _ _ _   |   _|__ /_\n"
            + "  |  \\           /  |  / __)-' )\n"
            + "   \\  '.       .'  /   \\  `(.-')\n"
            + "    '.  `'---'`  .'     > ._>-'\n"
            + "      '-._____.-'      / \\/";
    private static final String INITIAL_MESSAGE =
            "4LC3N-BOT initialised.\nGreetings, humans!";
    private static final String AWAITING_MESSAGE = "\n> ENTER your input:";
    private static final String DONE_MESSAGE = "You have completed: ";
    private static final String ERROR_MESSAGE = "      _.-'''''-._  \n"
            + "    /=_.-~-~-~-._=\\\n"
            + "   :               :\n"
            + "  /    (X)   (X)    \\\n"
            + "  |                 |\n"
            + "  |     .-----.     |\n"
            + "   \\    '_ _ _'    /\n"
            + "    '.           .'\n"
            + "      '-._____.-'";
    private static final String DELETED_MESSAGE =
            "Garbage cleared successfully."
            + "\n\n      _.-'''''-._  \n"
            + "    /=_.-~-~-~-._=\\\n"
            + "   :    _     _    :\n"
            + "  /    (o)   (o)    \\\n"
            + "  |                 |\n"
            + "  |  \\           /  |\n"
            + "   \\  '.       .'  /\n"
            + "    '.  `'---'`  .'\n"
            + "      '-._____.-'\n\n"
            + "Take one last look at what I deleted:";
    private static final String FAILED_TO_FIND_MESSAGE =
            "Sorry, could not find anything matching that!";
    private static final String FOUND_TASK_MESSAGE =
            "I have found these tasks!\n";
    private static final String STORE_MESSAGE_ONE = "I have stored this task in my memory. Use"
            + " \"list\" to retrieve it!\nTotal of ";
    private static final String STORE_MESSAGE_TWO = " tasks stored";
    private static final String GOODBYE_MESSAGE =
            "\nGoodbye! You will be missed"
            + "\n      _.-'''''-._  \n"
            + "    /=_.-~-~-~-._=\\\n"
            + "   :    _     _    :\n"
            + "  /    (o)   (o)    \\\n"
            + "  |           `     |\n"
            + "  |     .-----.     |\n"
            + "   \\   :       :   /\n"
            + "    '.           .'\n"
            + "      '-._____.-'";

    public static final String LOAD_FROM_DISK_FAIL_MESSAGE =
            "Could not find local storage";
    public static final String HELP_MESSAGE =
            "Here is a list of words that I understand:\n\n"
            + "bye                        | Terminates the bot\n"
            + "\n"
            + "deadline <text> /by <date> | Creates a new deadline with\n"
            + "                           | description <text> and date\n"
            + "                           | <date>.\n"
            + "                           |\n"
            + "                           | Date can be given in\n"
            + "                           | this format: \n"
            + "                           |\n"
            + "                           | DD-MM-YYYY-tttt\n"
            + "                           | where tttt is the time in\n"
            + "                           | 24-hour format\n"
            + "                           |\n"
            + "                           | or this format: DD-MM-YYYY\n"
            + "\n"
            + "delete <number>            | Deletes task on the list\n"
            + "\n"
            + "done <number>              | Marks a task as done\n"
            + "\n"
            + "event <text> /at <date>    | Creates a new event with\n"
            + "                           | description <text> and date\n"
            + "                           | <date>.\n"
            + "                           |\n"
            + "                           | Date can be given in\n"
            + "                           | this format: \n"
            + "                           |\n"
            + "                           | DD-MM-YYYY-tttt\n"
            + "                           | where tttt is the time in\n"
            + "                           | 24-hour format\n"
            + "                           |\n"
            + "                           | or this format: DD-MM-YYYY\n"
            + "\n"
            + "exit                       | Terminates the bot\n"
            + "\n"
            + "find <word>                | Finds tasks with that word\n"
            + "                           | in the description\n"
            + "\n"
            + "help                       | Shows this help message\n"
            + "\n"
            + "list                       | Shows the list of tasks\n"
            + "\n"
            + "todo <text>                | Creates a new to-do with\n"
            + "                           | description <text>\n"
            + "\n"
            + "search <date>              | Finds tasks with that date\n"
            + "                           |\n"
            + "                           | Date can be given in\n"
            + "                           | this format: \n"
            + "                           |\n"
            + "                           | DD-MM-YYYY-tttt\n"
            + "                           | where tttt is the time in\n"
            + "                           | 24-hour format\n"
            + "                           |\n"
            + "                           | or this format: DD-MM-YYYY\n"
            + "\n";

    /**
     * Displays a greeting message
     */
    public void showGreetings() {
        System.out.println(Ui.GREETINGS);
    }

    /**
     * Displays the version message
     */
    public void showVersion() {
        System.out.println(Ui.VERSION);
    }

    /**
     * Displays the loading message
     */
    public void showLoading() {
        System.out.println(Ui.LOAD);
    }

    /**
     * Displays the initial message
     */
    public void showInitial() {
        System.out.println(Ui.INITIAL_MESSAGE);
    }

    /**
     * Displays the awaiting message
     */
    public void showAwaiting() {
        System.out.println(Ui.AWAITING_MESSAGE);
    }

    /**
     * Displays the done message
     * (task completion)
     */
    public void showDone() {
        System.out.println(Ui.DONE_MESSAGE);
    }

    /**
     * Displays an error message
     *
     * @param e The Exception that caused
     *          the error
     */
    public void showError(Exception e) {
        System.err.println(e.getMessage());
        System.err.println(Ui.ERROR_MESSAGE);
    }

    /**
     * Displays the deleted message
     * (task deletion)
     */
    public void showDeleted() {
        System.out.println(Ui.DELETED_MESSAGE);
    }

    /**
     * Displays the failed to find message
     * (search found nothing)
     */
    public void showFailedToFind() {
        System.out.println(Ui.FAILED_TO_FIND_MESSAGE);
    }

    /**
     * Displays the found task message
     * (search found something)
     */
    public void showFoundTask() {
        System.out.println(Ui.FOUND_TASK_MESSAGE);
    }

    /**
     * Displays the load from disk fail message
     * (Could not find local storage)
     */
    public void showDiskLoadFail() {
        System.err.println(Ui.LOAD_FROM_DISK_FAIL_MESSAGE);
    }

    /**
     * Prints a default message for storing a Task
     *
     * @param storeSize Number of Tasks already in
     *                  the Storage
     */
    public void showTaskStoreMessage(int storeSize) {
        System.out.println(Ui.STORE_MESSAGE_ONE + storeSize
                + Ui.STORE_MESSAGE_TWO);
    }

    /**
     * Prints the help message to the interface
     */
    public void showHelpMessage() {
        System.out.println(Ui.HELP_MESSAGE);
    }

    /**
     * Displays the goodbye message
     */
    public void showGoodbye() {
        System.out.println(Ui.GOODBYE_MESSAGE);
    }
}
