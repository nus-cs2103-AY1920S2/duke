import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File.*;

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

    // constructor for chat-bot to initialise the file that was saved, if does not exist, then create new one

    /**
     * Function to run chat-bot and terminate when needed.
     * @param sc scanner object that gets the input.
     */
    public void runChatBot(Scanner sc) {
        String inputCommand;
        boolean continueRunning = true;
        this.ui.greetUser();
        ArrayList<Task> tasks = this.storage.getTaskFromStorage();
        this.taskLists = new TaskList(tasks);
        while (sc.hasNextLine()) {
            inputCommand = sc.nextLine();
            continueRunning = parser.respondToUser(inputCommand, this.ui, this.taskLists);
            if (!continueRunning) {
                // update file with updated tasklist
                storage.writeToFile(this.taskLists.getTaskList());
                break;
            }
        }
    }

}
