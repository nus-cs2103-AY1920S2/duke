import java.util.Optional;
import java.io.FileNotFoundException;

/**
 * Main class of this application.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Construct a new `Duke` instance.
     * This sets up a `Scanner` to standard input, and loads task data from file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showSaveNotFoundMessage(storage.savePath);
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }
    
    /**
     * Main loop of the application.
     * First greets the user, then reads command lines, responding to each.
     * If an exit command is entered, it is processed,
     * then the goodbye message is printed and the program exits from the loop.
     */
    private void run() {
        ui.greet();
        
        boolean running = true;
        while (running) {
            String commandString = ui.readCommandString();
            
            ui.showLine();
            try {
                Optional<Command> c = new Parser(commandString).parse();
                if (c.isPresent()) {
                    Command cmd = c.get();
                    cmd.execute(tasks, ui, storage);
                    running = running && !cmd.isExit();
                } else {
                    ui.showUnknownCommandMessage(commandString);
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
            ui.showLine();
        }
        
        try {
            storage.save(tasks.getTaskState());
        } catch (DukeException e) {
            ui.showLine();
            ui.showError(e);
            ui.showLine();
        }
    }
    
    public static void main(String[] args) {
        new Duke().run();
    }
}
