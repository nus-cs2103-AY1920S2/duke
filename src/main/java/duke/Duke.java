package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import duke.exceptions.IncorrectArgumentException;
import duke.exceptions.InvalidCommandException;

/**
 * Duke class represents the main chatbot entity; it contains classes that manage different components of the chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Parser parser;

    /**
     * Public constructor initializes Duke by loading the TaskList from the files in the save directory
     * @param filePath
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(storage, ui, tasks);
        try {
            storage.loadFromFile(tasks);
            System.out.println("    Tasklist loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("    No prior tasklist found...");
        } catch (IOException e) {
            System.out.println("    Existing tasklist cannot be read...");
        } catch (ClassNotFoundException e) {
            System.out.println("    Existing tasklist cannot be read...");
        }
    }
    
    public static void main(String[] args) {
        Path saveDir = Paths.get(System.getProperty("user.dir"), "data.duke");
        new Duke(saveDir).run();
    }

    /** Starts Duke and begins reading commands from input. */
    private void run() {       
        ui.out("Hello! I'm Duke", "What can I do for you?");
        boolean isShutdown = false;
        while (!isShutdown) {
            String line = ui.getLine();
            try {
                isShutdown = parser.parse(line);
                storage.saveToFile(tasks);
            } catch(InvalidCommandException | IncorrectArgumentException e) {
                ui.out(e.getMessage());
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
