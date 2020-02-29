import java.io.FileNotFoundException;

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private static final String FILEPATH = "tasks.txt";

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        storage = new Storage(FILEPATH);

        try {
            taskList = storage.getTaskFromMemory();
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }

        parser = new Parser(storage, taskList);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parseAndExecute(input);
    }
}