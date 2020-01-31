package duke;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.io.FileNotFoundException;

import duke.ui.Ui;
import duke.ui.TextUi;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.storage.TextStorage;
import duke.commands.CommandHandler;
import duke.exceptions.DukeException;

public class Duke {
    private Semaphore inputLock;
    private Ui ui = new TextUi();

    public void run() {
        String filePath = "data/tasks.txt";
        Storage storage = new TextStorage(filePath);
        TaskList tasks = new TaskList();
        ui.showGreeting();
        try {
            tasks.add(storage.load());
            ui.showReply("Save file loaded!");
        } catch (FileNotFoundException e) {
            ui.showError("Save file not found!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        CommandHandler handler = new CommandHandler(tasks, ui);
        while (handler.isActive()) {
            try {
                inputLock.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.executeCmd(ui.getInput());
        }
        try {
            storage.save(tasks.getAllTasks());
            ui.showReply("Save Success! See you next time!");
        } catch (IOException e) {
            ui.showError("Save Failure :-(. Try again next time!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public void addSemaphore(Semaphore sem) {
        inputLock = sem;
    }

    public void useUi(Ui ui) {
        this.ui = ui;
    }
}