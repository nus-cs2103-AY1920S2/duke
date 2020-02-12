import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the ChatBot object that the user interacts with.
 * corresponds to UI, Tasklist, Storage, Parser classes that aids in its functions.
 */
public class ChatBot {
    // class for the chat-bot for the Duke Project


    private UI ui = new UI();
    private TaskList taskLists;
    private String fileLocation = "./Data/Tasks.txt"; //hard-coded relative file location of stored tasks
    private Storage storage = new Storage();
    private Parser parser = new Parser();
    private HistoryManager historyManager = new HistoryManager();


    public ChatBot() {

    }

    // constructor for chat-bot to initialise the file that was saved, if does not exist, then create new one
    public ChatBot(TaskList list) {
        this.taskLists = list;
    }

    /**
     * Function to run chat-bot and terminate when needed.
     * @param sc scanner object that gets the input.
     */
    public void runChatBot(Scanner sc) {
        String inputCommand;
        this.ui.greetUser();
        this.updateTaskList();
        while (sc.hasNextLine()) {
            inputCommand = sc.nextLine();
            Command command = parser.respondToUser(inputCommand);
            try {
                command.execute(this.ui, this.taskLists, this.storage, this.historyManager);
                if (command.getExitStatus()) {
                    return; //exit from chat-bot
                }
            } catch (DukeException e) {
                this.ui.prettyPrinting(e.toString());
            }
        }
    }

    public void updateTaskList() {
        ArrayList<Task> tasks = this.storage.getTaskFromStorage();
        this.taskLists = new TaskList(tasks);
    }

    public Parser getParser() {
        return this.parser;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public UI getUI() {
        return this.ui;
    }

    public TaskList getTaskLists() {
        return this.taskLists;
    }

    public HistoryManager getHistoryManager() {
        return this.historyManager;
    }
}
