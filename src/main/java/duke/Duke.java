package duke;

import duke.tasks.TaskList;
import duke.ui.Gui;
import duke.storage.Storage;
import duke.exceptions.DukeException;
import duke.commands.Command;
import duke.parser.Parser;

import java.util.Optional;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class of this application.
 */
public class Duke extends Application {
    private TaskList tasks;
    private Gui ui;
    private Storage storage;
    private boolean isRunning = true;

    /**
     * Constructs a new `Duke` instance.
     * This sets up the UI, and loads task data from file.
     * For the command-line frontend, this attaches a `Scanner` to standard input.
     */
    public Duke() {
        //ui = new Cli();
        ui = new Gui(this);
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
    
    /**
     * Parses and processes a command string, returning the repsonse text.
     * @param command Command string
     * @return Response text
     */
    public String processCommand(String command) {
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
        String response = ui.endMessage();

        //We have to move the cleanup code here because `Gui` doesn't use `Duke#run()`
        if (!isRunning) {
            cleanup();
        }

        return response;
    }
    
    protected void cleanup() {
        try {
            storage.save(tasks.getTaskState());
        } catch (DukeException e) {
            ui.startMessage();
            ui.showError(e);
            ui.endMessage();
        }
        
        ui.close();
    }
    
    /**
     * Runs the main loop of the command-line application.
     * This function is not called in the graphical front-end.
     *
     * <p>First greets the user, then reads command lines, responding to each.
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
        
        cleanup();
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
    
    @Override
    public void start(Stage stage) {
        ui.start(stage);
    }

    @Override
    public void stop() {
        cleanup();
    }
}
