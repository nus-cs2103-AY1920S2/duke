import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Duke, representing a personal assistant chat bot that helps to track the user's task list.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the chat bot, Duke, while users key in various command inputs.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showDateError();
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexError();
            }
        }
    }

    /**
     * Main method which initiates the start of the system to run Duke.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
