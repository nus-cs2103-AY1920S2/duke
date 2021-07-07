import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * Main driver class for Duke.
 */
public class Duke {
    static TaskList dl;

    /**
     * Driver method for Duke.
     * Retrieves list from stored data if available, else start a new list.
     * The TaskList is then parsed with using input.
     */
    void init() {
        try {
            dl = Storage.retrieveTasks();
        } catch (FileNotFoundException | ParseException e) {
            dl = new TaskList();
        }
    }

    private String executeCommand(String input) {
        assert input != null;
        return Parser.parse(input);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        assert input != null;
        return executeCommand(input);
    }
}