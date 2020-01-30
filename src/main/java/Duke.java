import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Duke
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Duke {
    private static Parser parser;
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage);
            parser = new Parser(tasks, ui);
            storage.loadTasks();
            run();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static void run() throws IOException {
        ui.run();
        parser.parseInput(ui.getInput());
    }

    /**
     * The main method is where Duke introduces itself.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        new Duke("../data/duke.txt");
    }
}
