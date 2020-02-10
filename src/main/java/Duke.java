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
        }
        assert tasks != null : "TaskList should be instantiated.";
    }


    String getResponse(String input) {
        String output = ui.getOutput(input);
        assert !output.isEmpty() : "The output should not be empty.";
        return output;
    }

}
