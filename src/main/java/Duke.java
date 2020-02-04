import command.Controller;
import command.DukeException;
import command.Storage;
import command.UI;
import java.util.Scanner;

/**
 * The project is a product named Duke, a Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 */
public class Duke {

    /**
     * Start the chat bot by first retrieving saved file from hard disk then
     * obtaining input from the user. Saves the task list into the storage file
     * when user terminates the programme.
     * @param args user input.
     */
    public void start() {
        Storage.readFromFile();
        Storage.readFromFile();
        Storage.saveFile();
    }
}

