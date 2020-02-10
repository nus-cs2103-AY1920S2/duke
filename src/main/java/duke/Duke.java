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
     * Executes the start-up, message shown, and main functions of the chatbot
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
     * Returns the response user suppose to see upon keying in any input into the dialog
     * box. String returned will never be empty.
     * At this point all 3 objects ui, tasks and storage should not be null.
     *
     * @param input User's input into the dialog box
     * @return String object of the response according to user's input.
     */
    public String getResponse(String input) {
        assert ui != null;
        assert tasks != null;
        assert storage != null;
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

        assert !response.isEmpty();
        return response;
    }

    /**
     * Provide a method for other class to obtain Ui object of Duke class.
     * Ui object should never be null as it handles every display user suppose
     * to see on their screen.
     *
     * @return Ui object as a reference to handle Ui interactions with user.
     */
    public Ui getUi() {
        assert ui != null;
        return this.ui;
    }
}