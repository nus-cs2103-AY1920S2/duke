/**
 * This class is the main logical control centre of the Duke program.
 * It manages both the Parser, which parses and responds to user input,
 * as well as the Storage, which loads from and writes to the save file.
 */
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    private final String SAVE_DIR = "./data/duke.txt";

    /**
     * Initialises the Duke program on startup
     * by loading the previously saved task list
     * onto the working list, if there exists one.
     * Otherwise, the working list starts empty.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage(SAVE_DIR);
        tasks = new TaskList();
        storage.load(tasks);
    }

    /**
     * Obtains the welcome message from the Parser.
     *
     * @return the welcome message as a String.
     */
    public String getWelcomeMessage() {
        return parser.getWelcomeMessage();
    }

    /**
     * Passes the user input into the Parser, and
     * returns the program output message as a String.
     * Also updates the save file with any changes.
     *
     * @param command The user's input command.
     * @return the program's output message.
     */
    public String getResponse(String command) {
        assert !command.equals("") : "Empty input";
        String response = parser.parseCommand(command, tasks);
        storage.save(tasks);
        return response;
    }
}