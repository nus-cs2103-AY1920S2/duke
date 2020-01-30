import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.getCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt").toString()).run();
    }

}
