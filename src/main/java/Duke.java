import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.*;

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

    /**
     * Constructs a Duke instance.
     * @param filePath The file path for the file to be opened and its data to be read or for new data to be written.
     */
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

    /**
     * The method that calls the driver method run in Ui.
     */
    private static void run()  {
        ui.run();
        parser.parseInput(ui.getInput());
    }

    /**
     * The method that creates and instance of Duke.
     * @param args The arguments from the command line.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt");
    }
}
