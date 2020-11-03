package duke;

import java.util.concurrent.Semaphore;
import java.io.IOException;
import java.io.FileNotFoundException;

import duke.ui.Ui;
import duke.ui.TextUi;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.storage.TextStorage;
import duke.commands.CommandHandler;
import duke.exceptions.DukeException;

/**
 * Logic for Duke. Uses TextUi by default.
 */
public class Duke {
    private Semaphore inputLock;
    private Ui ui = new TextUi();

    /**
     * Runs the main loop of Duke.
     */
    public void run() {
        ui.showGreeting();

        // Setup in-memory and disk storage
        String filePath = "data/tasks.txt";
        Storage storage = new TextStorage(filePath);
        TaskList tasks = loadTasks(storage);

        activate(new CommandHandler(tasks, ui, storage));

        saveTasks(tasks, storage);
        ui.shutDown();
    }

    /**
     * Registers a semaphore for Duke to synchronise with other threads.
     */
    public void addSemaphore(Semaphore sem) {
        inputLock = sem;
    }

    /**
     * Configures Duke to use a particular Ui.
     */
    public void useUi(Ui ui) {
        this.ui = ui;
    }

    private TaskList loadTasks(Storage storage) {
        TaskList tasks = new TaskList();
        try {
            tasks.add(storage.load());
            ui.showReply("Save file loaded!");
        } catch (FileNotFoundException e) {
            ui.showError("Save file not found!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return tasks;
    }

    private void saveTasks(TaskList tasks, Storage storage) {
        try {
            storage.save(tasks.getAllTasks());
            ui.showReply("Save Success! See you next time!");
        } catch (IOException e) {
            ui.showError("Save Failure :-(. Try again next time!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    private void activate(CommandHandler handler) {
        while (handler.isActive()) {
            if (inputLock != null) {
                try {
                    inputLock.acquire();
                } catch (InterruptedException e) {
                    ui.showError("Interrupted!");
                }
            }
            handler.handleCommand(ui.getInput());
        }
    }
}