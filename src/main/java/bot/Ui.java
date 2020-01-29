package bot;

/**
 * Class that represents the UI of 4LC3N-BOT
 */
public class Ui {
    private static final String VERSION = "4LC3N v0.1.0";
    private static final String LOAD =
            "=======================================================\n" +
            "                  LOADING ... ... ...\n" +
            "=======================================================";
    private static final String GREETINGS = "      _.-'''''-._\n" +
            "    /=_.-~-~-~-._=\\      .-.  _\n" +
            "   :    _     _    :     | | / )\n" +
            "  /    (o)   (o)    \\    | |/ /\n" +
            "  |   _ _ _ _ _ _   |   _|__ /_\n" +
            "  |  \\           /  |  / __)-' )\n" +
            "   \\  '.       .'  /   \\  `(.-')\n" +
            "    '.  `'---'`  .'     > ._>-'\n" +
            "      '-._____.-'      / \\/";
    private static final String INITIAL_MESSAGE =
            "4LC3N-BOT initialised.\nGreetings, humans!";
    private static final String AWAITING_MESSAGE = "\n> ENTER your input:";
    private static final String DONE_MESSAGE = "You have completed: ";
    private static final String ERROR_MESSAGE = "      _.-'''''-._  \n" +
            "    /=_.-~-~-~-._=\\\n" +
            "   :               :\n" +
            "  /    (X)   (X)    \\\n" +
            "  |                 |\n" +
            "  |     .-----.     |\n" +
            "   \\    '_ _ _'    /\n" +
            "    '.           .'\n" +
            "      '-._____.-'";
    private static final String DELETED_MESSAGE =
            "Garbage cleared successfully." +
            "\n\n      _.-'''''-._  \n" +
            "    /=_.-~-~-~-._=\\\n" +
            "   :    _     _    :\n" +
            "  /    (o)   (o)    \\\n" +
            "  |                 |\n" +
            "  |  \\           /  |\n" +
            "   \\  '.       .'  /\n" +
            "    '.  `'---'`  .'\n" +
            "      '-._____.-'\n\n" +
            "Take one last look at what I deleted:";
    private static final String FAILED_TO_FIND_MESSAGE =
            "Sorry, could not find anything matching that!";
    private static final String FOUND_TASK_MESSAGE =
            "I have found these tasks!\n";
    private static final String GOODBYE_MESSAGE =
            "\nGoodbye! You will be missed" +
            "\n      _.-'''''-._  \n" +
            "    /=_.-~-~-~-._=\\\n" +
            "   :    _     _    :\n" +
            "  /    (o)   (o)    \\\n" +
            "  |           `     |\n" +
            "  |     .-----.     |\n" +
            "   \\   :       :   /\n" +
            "    '.           .'\n" +
            "      '-._____.-'";

    public static final String LOAD_FROM_DISK_FAIL_MESSAGE =
            "Could not find local storage";

    /**
     * Displays a greeting message
     */
    public void greetings() {
        System.out.println(Ui.GREETINGS);
    }

    /**
     * Displays the version message
     */
    public void version() {
        System.out.println(Ui.VERSION);
    }

    /**
     * Displays the loading message
     */
    public void load() {
        System.out.println(Ui.LOAD);
    }

    /**
     * Displays the initial message
     */
    public void initial() {
        System.out.println(Ui.INITIAL_MESSAGE);
    }

    /**
     * Displays the awaiting message
     */
    public void awaiting() {
        System.out.println(Ui.AWAITING_MESSAGE);
    }

    /**
     * Displays the done message
     * (task completion)
     */
    public void done() {
        System.out.println(Ui.DONE_MESSAGE);
    }

    /**
     * Displays an error message
     *
     * @param e The Exception that caused
     *          the error
     */
    public void error(Exception e) {
        System.err.println(e.getMessage());
        System.err.println(Ui.ERROR_MESSAGE);
    }

    /**
     * Displays the deleted message
     * (task deletion)
     */
    public void deleted() {
        System.out.println(Ui.DELETED_MESSAGE);
    }

    /**
     * Displays the failed to find message
     * (search found nothing)
     */
    public void failedToFind() {
        System.out.println(Ui.FAILED_TO_FIND_MESSAGE);
    }

    /**
     * Displays the found task message
     * (search found something)
     */
    public void foundTask() {
        System.out.println(Ui.FOUND_TASK_MESSAGE);
    }

    /**
     * Displays the load from disk fail message
     * (Could not find local storage)
     */
    public void diskLoadFail() {
        System.err.println(Ui.LOAD_FROM_DISK_FAIL_MESSAGE);
    }

    /**
     * Displays the goodbye message
     */
    public void goodbye() {
        System.out.println(Ui.GOODBYE_MESSAGE);
    }
}
