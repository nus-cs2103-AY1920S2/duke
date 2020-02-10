package duke;

/**
 * Main class for running the Duke program.
 */
public class Duke {
    private Parser parser;
    private String welcomeMessage = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! What can I do for you?";

    /**
     * Initializes the Duke program.
     *
     * @throws DukeException Any read/write operations errors resulting from storage.load() will be thrown.
     */
    public Duke() throws DukeException {
        Storage storage = new Storage("data/tasks.txt");
        TaskList tasks = new TaskList(storage.load());
        parser = new Parser(tasks, storage);
    }

    /**
     * Processes user input and return a response.
     *
     * @param input User input.
     * @return Response to the user.
     */
    public String getResponse(String input) {
        String response;
        try {
            response = parser.parse(input);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
