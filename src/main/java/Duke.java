import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.print("can't load file.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeEmptyDescriptionException e) {
                ui.print(e.getMessage());
            } catch (DukeNoKeywordException e) {
                ui.print(e.getMessage());
            } catch (IllegalArgumentException e) {
                ui.print(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "code", "duke", "data", "duke.txt");
        new Duke(path).run();
    }
}
