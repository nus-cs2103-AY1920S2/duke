import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    /**
     * main method for Duke.
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.buildTaskList();
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
            storage.updateStorage(tasks);
        }
    }

    public void run() {
        ui.welcomeUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.divider("");
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, storage, ui);
                storage.updateStorage(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.divider("");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
