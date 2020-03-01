import commands.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Duke with its UI and file and load tasks into storage.
     *
     * @throws IOException if there are file exceptions.
     */
    public Duke() throws IOException {
        ui = new Ui();
        assert this.ui != null : "Ui should be instantiated";
        storage = new Storage("data/duke.txt");
        assert this.storage != null : "Storage should be instantiated";
        try {
            tasks = new TaskList(storage.loadExistingFileTasks());
            assert this.tasks != null : "Tasks should be instantiated";
            assert this.tasks.getTaskListSize() != 0 : "Tasks should should have been loaded";
        } catch (FileNotFoundException | AssertionError ex) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Duke responds in GUI.
     *
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Parser parser = new Parser(input);
            Command c = parser.parse();
            if (c == null) {
                Ui.printUnknownCommand();
            }
            if (c.isExit()) {
                System.exit(0);
            }
            response = c.execute(tasks, ui, storage);
        } catch (DukeException | IOException | DateTimeException | AssertionError | IndexOutOfBoundsException ex) {
            response = ui.showCommandError(ex.getMessage());
        }
        return response;
    }
}
