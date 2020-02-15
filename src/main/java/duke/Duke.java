package duke;

import java.io.IOException;

/**
 * Main class for running the Duke program.
 */
public class Duke {
    private Parser parser;
    private String welcomeMessage = "____          _                               \n"
            + "|  _  \\ _    _| | _____                   \n"
            + "| |  |  |  |  |  | |/ / __ \\              \n"
            + "| |_ |  |  |_|  |  <   __/                 \n"
            + "|____/ \\__,_|_|\\_\\___|                  \n"
            + "Hello! What can I do for you?";

    /**
     * Initializes the Duke program.
     * Any IO exceptions or read/write operations errors will be printed to the standard error.
     */
    public Duke() {
        try {
            Storage storage = new Storage("data/tasks.txt");
            TaskList tasks = new TaskList(storage.load());
            parser = new Parser(tasks, storage);
        } catch (IOException | DukeException e) {
            System.err.println(e);
        }
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
