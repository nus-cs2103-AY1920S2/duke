import duke.pack.DukeException;
import duke.pack.Ui;
import duke.pack.Storage;
import duke.pack.Parser;
import duke.pack.TaskList;
import duke.pack.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        // following code from module website

        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        // following code from module website

        ui.greet();

        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.receiveInput();
                ui.showLine();
                Command comm = parser.parseCommand(command);
                comm.execute(tasks, ui, storage);
                isExit = comm.isExit();

            } catch (DukeException e) {
                ui.showError(e);

            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }

}
