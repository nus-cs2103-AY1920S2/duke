package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeIoException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeIoException e) {
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
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "code", "duke", "data", "duke.txt");
        new Duke(path).run();
    }
}
