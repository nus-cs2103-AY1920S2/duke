import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Parser parser;

    /**
     * Initializes Duke and loads the TaskList from the files in the save directory
     * @param filePath
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(storage, ui, tasks);
        try {
            storage.loadFromFile(tasks);
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

    /** Starts Duke */
    public void run() {       
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
