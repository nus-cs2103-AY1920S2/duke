package bot.gui;

import bot.Ui;

import javafx.collections.ObservableList;

import javafx.scene.Node;
import javafx.scene.text.Font;

/**
 * A class that is used by the internal bot classes,
 * in order to send messages to the JavaFX GUI
 */
public class GraphicalUi extends Ui {
    private static final String GREETINGS = "Hello world!";
    private static final String GREETINGS_MESSAGE = "Hello! I love you! <3";
    private static final String LOAD = "LOADING ... ... ...";
    private static final String DELETED_MESSAGE =
            "Garbage cleared successfully."
            + "\nTake one last look at what I deleted:\n";
    private static final String INITIAL_MESSAGE =
            "4LC3N-BOT initialised.\nGreetings, humans!";
    private static final String AWAITING_MESSAGE = "\n> ENTER your input:";
    private static final String DONE_MESSAGE = "You have completed:\n";
    private static final String NOT_DONE_MESSAGE =
            "Status of task has been set to not done:\n";
    private static final String FAILED_TO_FIND_MESSAGE =
            "Sorry, could not find anything matching that!";
    private static final String NO_TASKS_MESSAGE =
            "You have no tasks to show!";
    private static final String FOUND_TASK_MESSAGE =
            "I have found these tasks!\n";
    private static final String STORE_MESSAGE_ONE = "I have stored this task in my memory. Use"
            + " \"list\" to retrieve it!\nTotal of ";
    private static final String STORE_MESSAGE_TWO = " tasks stored";
    private static final String GOODBYE_MESSAGE = "Goodbye! You will be missed";
    private static final String LOAD_FROM_DISK_FAIL_MESSAGE =
            "Could not find local storage";
    private static final String THANKS_MESSAGE =
            Ui.THANKS_MESSAGE;
    private static final String ALIAS_MESSAGE_ONE =
            Ui.ALIAS_MESSAGE_ONE;
    private static final String ALIAS_MESSAGE_TWO =
            Ui.ALIAS_MESSAGE_TWO;
    private static final String ALIAS_MESSAGE_THREE =
            Ui.ALIAS_MESSAGE_THREE;
    private static final String HELP_MESSAGE =
            "Here is a list of words that I understand:\n\n"
            + "alias    | Allows the command <cmd>\n"
            + "<cmd>    | to be substituted by the\n"
            + "<name>   | word <name> with the\n"
            + "         | exact same usage\n"
            + "\n"
            + "bye      | Terminates the bot\n"
            + "\n"
            + "deadline | Creates a new deadline with\n"
            + "<text>   | description <text> and date\n"
            + "/by      | <date>.\n"
            + "<date>   |\n"
            + "         | Date can be given in\n"
            + "         | this format: \n"
            + "         |\n"
            + "         | DD-MM-YYYY-tttt\n"
            + "         | where tttt is the time in\n"
            + "         | 24-hour format\n"
            + "         |\n"
            + "         | or this format: DD-MM-YYYY\n"
            + "\n"
            + "delete   | Deletes task on the list\n"
            + "<n>      | with index n\n"
            + "\n"
            + "done     | Marks a task with index\n"
            + "<n>      | n as done\n"
            + "\n"
            + "event    | Creates a new event with\n"
            + "<text>   | description <text> and date\n"
            + "/at      | <date>.\n"
            + "<date>   |\n"
            + "         | Date can be given in\n"
            + "         | this format: \n"
            + "         |\n"
            + "         | DD-MM-YYYY-tttt\n"
            + "         | where tttt is the time in\n"
            + "         | 24-hour format\n"
            + "         |\n"
            + "         | or this format: DD-MM-YYYY\n"
            + "\n"
            + "ex/exi/  | Terminates the bot.\n"
            + "exit     | \"i\" and \"it\" optional\n"
            + "\n"
            + "find     | Finds tasks with that word\n"
            + "<word>   | in the description\n"
            + "\n"
            + "hello    | Hello!\n"
            + "\n"
            + "help     | Shows this help message\n"
            + "\n"
            + "list     | Shows the list of tasks\n"
            + "\n"
            + "notdone  | Marks a task with index\n"
            + "<n>      | n as NOT done\n"
            + "\n"
            + "search   | Finds tasks with that date\n"
            + "<date>   |\n"
            + "         | Date can be given in\n"
            + "         | this format: \n"
            + "         |\n"
            + "         | DD-MM-YYYY-tttt\n"
            + "         | where tttt is the time in\n"
            + "         | 24-hour format\n"
            + "         |\n"
            + "         | or this format: DD-MM-YYYY\n"
            + "\n"
            + "thanks   | It's good to be\n"
            + "         | appreciated!\n"
            + "\n"
            + "todo     | Creates a new to-do with\n"
            + "<text>   | description <text>\n"
            + "\n";

    public static final String VERSION = Ui.VERSION;

    private ObservableList<Node> chatWindow;

    public GraphicalUi(ObservableList<Node> window) {
        this.chatWindow = window;
    }

    @Override
    public void showGreetings() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.GREETINGS));
    }

    @Override
    public void showVersion() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.VERSION));
    }

    @Override
    public void showLoading() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.LOAD));
    }

    @Override
    public void showInitial() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.INITIAL_MESSAGE));
    }

    @Override
    public void showAwaiting() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.AWAITING_MESSAGE));
    }

    @Override
    public void showDone(String doneTask) {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.DONE_MESSAGE + doneTask));
    }

    @Override
    public void showNotDone(String undoneTask) {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.NOT_DONE_MESSAGE + undoneTask));
    }

    @Override
    public void showError(Exception e) {
        this.chatWindow.add(ChatBox.getBotBox(e.getMessage()));
    }

    @Override
    public void showDeleted(String deletedItem) {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.DELETED_MESSAGE
                + deletedItem));
    }

    @Override
    public void showFailedToFind() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.FAILED_TO_FIND_MESSAGE));
    }

    @Override
    public void showFoundTask(String tasks) {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.FOUND_TASK_MESSAGE + tasks));
    }

    @Override
    public void showDiskLoadFail() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.LOAD_FROM_DISK_FAIL_MESSAGE));
    }

    @Override
    public void showTaskStoreMessage(int storeSize) {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.STORE_MESSAGE_ONE
                + storeSize + GraphicalUi.STORE_MESSAGE_TWO));
    }

    @Override
    public void showNoTasksMessage() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.NO_TASKS_MESSAGE));
    }

    @Override
    public void showCustomMessage(String message) {
        this.chatWindow.add(ChatBox.getBotBox(message));
    }

    @Override
    public void showHelpMessage() {
        ChatBox helpBox = ChatBox.getBotBox(GraphicalUi.HELP_MESSAGE);
        helpBox.getText().fontProperty().set(Font.font("Monospaced", 11));
        this.chatWindow.add(helpBox);
    }

    @Override
    public void showHelloMessage() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.GREETINGS_MESSAGE));
    }

    @Override
    public void showGoodbye() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.GOODBYE_MESSAGE));
    }

    @Override
    public void showThanksMessage() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.THANKS_MESSAGE));
    }

    @Override
    public void showAliasMessage(String originalName, String aliasedName) {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.ALIAS_MESSAGE_ONE + aliasedName
                + GraphicalUi.ALIAS_MESSAGE_TWO + originalName
                + GraphicalUi.ALIAS_MESSAGE_THREE));
    }

    /**
     * Shows the user's input as a ChatBox aligned
     * to the right of the interface
     *
     * @param userInput String represent raw text
     *                  of user input
     */
    public void showUserChat(String userInput) {
        this.chatWindow.add(ChatBox.getUserBox(userInput));
    }
}
