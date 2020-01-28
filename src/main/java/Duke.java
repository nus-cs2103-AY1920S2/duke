import java.io.File;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath Path of the file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //if file is empty
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the code.
     *
     * @throws IOException Throws IOException.
     */
    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(0,fullCommand,0);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main function.
     *
     * @param args String array.
     * @throws IOException Throws IOException.
     */
    public static void main(String[] args) throws IOException {
        String filePath = new File("").getAbsolutePath();
        int ind = filePath.lastIndexOf("/");
        String path = filePath.substring(0, ind + 1);
        path = path.concat("duke.txt");
        new Duke(path).run();
    }
}