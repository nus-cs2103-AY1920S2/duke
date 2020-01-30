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
     * A constructor to initialise Storage, TaskList and Ui class
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

    /**
     * Execute the start-up, message shown, and main functions of the chatbot
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command currentCommand = Parser.parse(fullCommand);
                currentCommand.execute(tasks, ui, storage);
                isExit = currentCommand.isExit();
            } catch (DukeException dukeEx) {
                ui.showStandardError(dukeEx);
            } catch (DateTimeException dateEx) {
                ui.showDateTimeException();
            } catch (IndexOutOfBoundsException indexEx) {
                ui.showIndexOutOfBoundException(tasks.getSize());
            }
        }
    }

    /**
     * Create a Duke object which is used to invoke start-up of Chatbot.
     *
     * @param args Unused.
     */

    public static void main(String[] args) {
        new Duke("data" + System.getProperty("file.separator") + "duke.txt").run();
    }
}