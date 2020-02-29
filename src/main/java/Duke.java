import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();

    /**
     * Creates a new instance of the class Duke.
     * @param filePath This is the path of the input file.
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage("/duke/out/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    String getResponse(String input) {
        Parser.addList(tasks);
        return Parser.parse(input);
    }

    public void saveFile() {
        storage.writeBack("/duke/out/duke.txt", tasks);
    }
}
