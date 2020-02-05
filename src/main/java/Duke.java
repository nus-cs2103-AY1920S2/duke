import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke object which initializes the Storage, TaskList and Ui.
     */
    public Duke() {
        String homeDir = System.getProperty("user.home");
        String fileName = "duke.txt";
        Path path  = Paths.get(homeDir, "duke", "data", fileName);

        try {
            storage = new Storage(path);
            tasks = storage.load();
            ui = new Ui(tasks, storage);
        } catch (DukeException error) {
            System.out.println(error);
            tasks = new TaskList();
        }
    }


    String getResponse(String input) {
        return ui.getOutput(input);
    }

}
