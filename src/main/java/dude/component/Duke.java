package dude.component;

import dude.command.Command;
import dude.command.CommandExecutionException;

import javafx.application.Platform;

public class Duke {

    private IStorage storage;
    private TaskList tasks;
    private IUserInterface ui;

    /**
     * Initializes dude.component.Duke chatbot's main components.
     * UI - greets user, initializes resources to obtain user input.
     * TextStorage - contains file object to read previous data from.
     * TaskList - Is initialized with tasks from saved session from storage.
     *
     * @param mainWindow the main window of the GUI app. Exposes input/output to GUI class.
     */
    public Duke(MainWindow mainWindow) {
        this.ui = new Gui(mainWindow);
        this.storage = new TextStorage();
        this.tasks = this.storage.restoreSession(this.ui);
    }

    /**
     * Saves the current TaskList by calling saveSession from the IStorage class used.
     *
     * @throws SecurityException if a security violation occurs while attempting to create the directory or save file.
     */
    public void saveState() {
        this.storage.saveSession(ui, tasks);
    }

    /**
     * Takes user input and responds to commands appropriately.
     * Closes app when "bye" is given as input.
     */
    public void serve() {
        try {
            String msg = ui.readCommand();
            Command command = Parser.parse(msg);
            command.execute(tasks, ui);
            if (command.isExit()) {
                Platform.exit();
            }
        } catch (ParsingException e) {
            ui.respondParsingError(e.getMessage(), e.getUsageMsgs());
        } catch (CommandExecutionException e) {
            ui.respond(e.getMessage());
        }
    }
}