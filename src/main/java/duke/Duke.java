package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.InvalidCommandException;
import duke.task.Storage;
import duke.task.TaskList;
import javafx.scene.control.Label;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class for duke.
 * Hold Storage, TaskList and Ui object for application
 * Runs the application until the exit command is called.
 */
public class Duke {
    /** Storage object to deal with the save file. */
    private Storage storage;
    /** TaskList object to store the task list. */
    private TaskList tasks;
    /** Ui object to interact with the user. */
    private String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";

    /**
     * Constructs Duke with the default save directory.
     */
    public Duke() {
        String workingDir = System.getProperty("user.dir");
        Path savePath = Paths.get(workingDir, "data", "duke.txt");
        storage = new Storage(savePath);

        //load the tasks from save file
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Gets the program's response based on the user's input.
     *
     * @param input The user's input.
     * @return The program's response.
     */
    public String getResponse(String input) {
        try {
            //parse command
            Command command = Parser.parseCommand(input);

            //execute command
            return command.execute(tasks, storage);
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the welcome message of the program.
     *
     * @return The welcome message of the program.
     */
    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
