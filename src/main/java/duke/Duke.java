package duke;

import java.io.IOException;
import java.io.FileNotFoundException;

import duke.ui.Ui;
import duke.ui.Gui;
import duke.ui.TextUi;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.storage.TextStorage;
import duke.commands.CommandHandler;
import duke.exceptions.DukeException;

public class Duke {
    
    public static void main(String[] args) {
        Ui ui = new TextUi();
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}