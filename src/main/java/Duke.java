import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Loads the tasks from Path.
     * @param filePath Path containing the text file to be read from.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = storage.load();

        } catch (IOException e) {
            System.out.println("File loading error");
            tasks = new TaskList();
        }
    }

    /**
     * Starts the program.
     */
    public void run() {
        tasks = ui.takeInput(tasks);
        try {
            storage.save(tasks);

        } catch (IOException e) {
            System.out.println("File saving error");
        }
    }

    /**
     * Main method.
     * @param args Main method.
     */
    public static void main(String[] args) {
        String homeDir = System.getProperty("user.home");
        String fileName = "duke.txt";
        Path path = Paths.get(homeDir, "duke", "data", fileName);

        new Duke(path).run();
    }
}
