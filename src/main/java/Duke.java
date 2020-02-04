import java.util.Optional;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class of this application.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private boolean isRunning = true;

    /**
     * Constructs a new `Duke` instance.
     * This sets up a `Scanner` to standard input, and loads task data from file.
     */
    public Duke() {
        ui = new Cli();
        storage = new Storage();
        
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.startMessage();
            ui.showSaveNotFoundMessage(storage.STORAGE_SAVE_PATH);
            ui.endMessage();
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.startMessage();
            ui.showError(e);
            ui.endMessage();
            tasks = new TaskList();
        }
    }
    
    private void processCommand(String command) {
        ui.startMessage();
        try {
            Optional<Command> c = new Parser(command).parse();
            if (c.isPresent()) {
                Command cmd = c.get();
                cmd.execute(tasks, ui, storage);
                isRunning = isRunning && !cmd.isExit();
            } else {
                ui.showUnknownCommandMessage(command);
            }
        } catch (DukeException e) {
            ui.showError(e);
        }
        ui.endMessage();
    }
    
    /**
     * Runs the main loop of the command-line application.
     * First greets the user, then reads command lines, responding to each.
     * If an exit command is entered, it is processed,
     * then the goodbye message is displayed and the program exits from the loop.
     */
    public void run() {
        ui.startMessage();
        ui.showGreeting();
        ui.endMessage();
        
        while (isRunning) {
            String command = ui.readCommandString();
            processCommand(command);
        }
        
        try {
            storage.save(tasks.getTaskState());
        } catch (DukeException e) {
            ui.startMessage();
            ui.showError(e);
            ui.endMessage();
        }
    }
    
    /**
     * Saves the tasks in the internal `TaskList` to a file.
     * Displays an error message on failure.
     */
    public void saveTasks() {
        try {
            storage.save(tasks.getTaskState());
        } catch (DukeException e) {
            ui.startMessage();
            ui.showError(e);
            ui.endMessage();
        }
    }
    
    /*
    @Override
    public void start(Stage stage) {
        ui.start(stage);
    }
    */
}
