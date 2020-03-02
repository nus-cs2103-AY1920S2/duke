package dude.component;

import dude.command.Command;
import dude.command.CommandExecutionException;
import dude.parser.Parser;
import dude.parser.ParsingException;

public class Duke {

    private IStorage storage;
    private TaskList tasks;
    private IUserInterface ui;

    /**
     * Initializes Duke chat bot with injected user interface, task list and storage services.
     *
     * @param storage object that manages persistence of Duke's tasks.
     * @param tasks initial tasks that Duke starts with.
     * @param ui object that manages interaction with user
     */
    public Duke(IStorage storage, TaskList tasks, IUserInterface ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Takes user input and responds to commands appropriately.
     * Closes app when "bye" is given as input.
     */
    public void serve() {
        try {
            String msg = ui.readCommand();
            Command command = Parser.parse(msg);
            command.execute(tasks, ui, storage);
            if (command.isExit()) {
                ui.close();
            }
        } catch (ParsingException e) {
            ui.respondParsingError(e.getMessage(), e.getUsageMsg());
        } catch (CommandExecutionException e) {
            ui.respondError(e.getMessage());
        }
    }
}