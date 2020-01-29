import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(Path filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = storage.load();

        } catch (IOException e){
            System.out.println("File loading error");
            tasks = new TaskList();
        }
    }

    public void run() {
        tasks = ui.takeInput(tasks);
        try {
            storage.save(tasks);

        } catch (IOException e ) {
            System.out.println("File saving error");
        }
    }

    public static void main(String[] args) {
        String homeDir = System.getProperty("user.home");
        String fileName = "duke.txt";
        Path path = Paths.get(homeDir, "duke", "data", fileName);

        new Duke(path).run();
    }
}
