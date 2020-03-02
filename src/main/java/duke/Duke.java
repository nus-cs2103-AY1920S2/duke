package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

/**
 * Duke class calls the Ui, Storage and TaskList class to gather and update the result of the user command.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Empty constructor so that Launcher works.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * Initiate the Duke bot.
     * create UI, duke.storage.Storage, duke.task.TaskList.
     * @param filePath filePath stores the txt file used to load data.
     */
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Chat bot function, run infinitely until user types "bye".
     */
    public void runChatBot() {
        this.ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseMessage(fullCommand);
                CommandResult commandResult = c.execute(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            Command c = Parser.parseMessage(input);
            CommandResult commandResult = c.execute(tasks, ui, storage);
            System.out.flush();
            System.setOut(old);
            if (commandResult.toString().equals("bye")) {
                Platform.exit();
            }
        } catch (AssertionError e) {
            ui.showError("AssertError");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return baos.toString();

    }

    /**
     * Prints a logo of the name Duke.
     * @param args Set up main function
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path path = java.nio.file.Paths.get(home, "Desktop", "GitHub", "duke","data","data.txt");
        new Duke(path).runChatBot();
    }

}
