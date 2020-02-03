package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * Represents a Personal Assistant Chatbot named EXE that
 * helps a person to keep track of various things.
 *
 * @author Kenny Ho
 * @since 2020-01-20
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor to initialise duke.Storage, duke.task.TaskList and duke.Ui class
     * which is responsible for loading and saving tasks, containing
     * the task list with additional operations and
     * interaction with the user respectively.
     *
     * @param filePath Relative path of the storage text file used.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        } catch (IOException io) {
            ui.showStorageError();
        }
    }

    public Duke() {
        this("data" + System.getProperty("file.separator") + "duke.txt");
    }

    /**
     * Execute the start-up, message shown, and main functions of the chatbot
     */
    public void run() {
        System.out.println(ui.addBorder(ui.showWelcome()));
        String response = "";
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command currentCommand = Parser.parse(fullCommand);
                response = currentCommand.execute(tasks, ui, storage);
                isExit = currentCommand.isExit();
            } catch (DukeException dukeEx) {
                response = ui.showStandardError(dukeEx);
            } catch (DateTimeException dateEx) {
                response = ui.showDateTimeException();
            } catch (IndexOutOfBoundsException indexEx) {
                response = ui.showIndexOutOfBoundException(tasks.getSize());
            } finally {
                System.out.println(ui.addBorder(response));
            }
        }
    }

    /**
     * Create a duke.Duke object which is used to invoke start-up of Chatbot.
     *
     * @param args Unused.
     */

    public static void main(String[] args) {
        new Duke("data" + System.getProperty("file.separator") + "duke.txt").run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command currentCommand = Parser.parse(input);
            response = currentCommand.execute(tasks, ui, storage);
        } catch (DukeException dukeEx) {
            response = ui.showStandardError(dukeEx);
        } catch (DateTimeException dateEx) {
            response = ui.showDateTimeException();
        } catch (IndexOutOfBoundsException indexEx) {
            response = ui.showIndexOutOfBoundException(tasks.getSize());
        }

        return response;
    }

    public Ui getUi() {
        return this.ui;
    }
}