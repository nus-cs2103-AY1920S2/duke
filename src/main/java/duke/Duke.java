package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tags.TagList;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Class for the Duke program.
 *
 * @author Firzan Armani
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private TagList tags;
    private Ui ui;
    private static final String FILE_PATH = "data/duke.txt";

    public boolean isLoadedFromStorage = false;
    public int noLoadedTasks = 0;

    /**
     * Duke constructor.
     * Attempts to load tasks from duke.txt specified in filePath into a TaskList instance.
     * Creates an empty TaskList instance if the file cannot be found.
     *
     * @param filePath Path to the duke.txt containing the saved tasks.
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            tags = new TagList(tasks);
            this.isLoadedFromStorage = true;
            this.noLoadedTasks = tasks.getSize();
        } catch (DukeException e) {
            tasks = new TaskList();
            tags = new TagList();
        }
    }

    /**
     * Method to run the Duke program sequence.
     */
    public void run() {
        ui.showWelcome();
        if (this.isLoadedFromStorage) {
            ui.dukePrompt("Boss, I've got my notebook ready with " + tasks.getSize() + " tasks in it");
        }
        boolean isActive = true;
        while (isActive) {
            try {
                String fullCommand = ui.readUserInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, tags, ui, storage);
                if (c.isExit()) {
                    isActive = false;
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method. Entry point for the Duke program.
     *
     * @param args Command-line arguments. Unused.
     */
    public static void main(final String[] args) {
        new Duke(FILE_PATH).run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, tags, ui, storage);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }

        String dukeResponse = consoleOutput.toString();
        if (dukeResponse.contains("Duke: ")) {
            dukeResponse = dukeResponse.replace("Duke: ", "");
        }
        System.setOut(System.out);
        return dukeResponse;
    }

}
