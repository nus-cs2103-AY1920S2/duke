package hakunamatata;

import static java.lang.System.exit;

import hakunamatata.command.Command;
import hakunamatata.task.TaskList;

/**
 * Represents a hakunaMatata program, which is a task list keeper.
 * It allows users to add, delete and update their tasks' status.
 *
 * @author Wang Yuting
 */
public class HakunaMatata {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Create a new hakunaMatata with given file path.
     */
    public HakunaMatata(String filePath) {
        isExit = false;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.getErrorMessage(new HakunaMatataException("loadingError"));
            tasks = new TaskList();
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Executes the hakunaMatata program.
     */
    public String run(String input) {
        String response = "";
        if (!isExit) {
            try {
                String fullCommand = input;
                Command c = Parser.parse(fullCommand);
                response = c.execute(tasks, ui, storage);
                this.isExit = c.isExit();
            } catch (HakunaMatataException e) {
                response = ui.getErrorMessage(e);
            }
        } else {
            exit(0);
        }
        return response;
    }


}
