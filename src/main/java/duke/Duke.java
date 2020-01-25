package duke;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;

import duke.ui.Ui;
import duke.ui.TextUi;
import duke.tasks.Task;
import duke.storage.Storage;
import duke.storage.TextStorage;
import duke.commands.CommandHandler;
import duke.exceptions.DukeException;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new TextUi();
        String filePath = "data/tasks.txt";
        Storage storage = new TextStorage(filePath);
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        ui.showGreeting();
        try {
            tasks = storage.load();
            ui.showReply("Save file loaded!");
        } catch (FileNotFoundException e) {
            ui.showError("Save file not found!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        CommandHandler handler = new CommandHandler(tasks, ui);
        while (handler.isActive()) {
            handler.executeCmd(sc.nextLine());
        }
        try {
            storage.save(tasks);
            ui.showReply("Save Success! See you next time!");
        } catch (IOException e) {
            ui.showError("Save Failure :-(. Try again next time!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        sc.close();
    }
}
