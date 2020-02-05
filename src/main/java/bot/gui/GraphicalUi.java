package bot.gui;

import bot.Ui;

import javafx.collections.ObservableList;

import javafx.scene.Node;

/**
 * A class that is used by the internal bot classes,
 * in order to send messages to the JavaFX GUI
 */
public class GraphicalUi extends Ui {
    private static final String GREETINGS = "Hello world!";
    private static final String GREETINGS_MESSAGE = "Hello! I love you! <3";
    private static final String VERSION = Ui.VERSION;
    private static final String LOAD = "LOADING ... ... ...";
    private static final String DELETED_MESSAGE =
            "Garbage cleared successfully."
            + "\nTake one last look at what I deleted:\n";
    private static final String INITIAL_MESSAGE =
            "4LC3N-BOT initialised.\nGreetings, humans!";
    private static final String AWAITING_MESSAGE = "\n> ENTER your input:";
    private static final String DONE_MESSAGE = "You have completed: ";
    private static final String NOT_DONE_MESSAGE =
            "Status of task has been set to not done:";
    private static final String FAILED_TO_FIND_MESSAGE =
            "Sorry, could not find anything matching that!";
    private static final String FOUND_TASK_MESSAGE =
            "I have found these tasks!\n";
    private static final String STORE_MESSAGE_ONE = "I have stored this task in my memory. Use"
            + " \"list\" to retrieve it!\nTotal of ";
    private static final String STORE_MESSAGE_TWO = " tasks stored";
    private static final String GOODBYE_MESSAGE = "Goodbye! You will be missed";
    private static final String LOAD_FROM_DISK_FAIL_MESSAGE =
            "Could not find local storage";
    private static final String HELP_MESSAGE = Ui.HELP_MESSAGE;


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
    public void showDone() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.DONE_MESSAGE));
    }

    @Override
    public void showNotDone() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.NOT_DONE_MESSAGE));
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
    public void showFoundTask() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.FOUND_TASK_MESSAGE));
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
    public void showHelpMessage() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.HELP_MESSAGE));
    }

    @Override
    public void showHelloMessage() {
        this.chatWindow.add(ChatBox.getBotBox(GraphicalUi.GREETINGS_MESSAGE));
    }

    @Override
    public void showGoodbye() {
        this.chatWindow.add(ChatBox.getBotBox(GOODBYE_MESSAGE));
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
