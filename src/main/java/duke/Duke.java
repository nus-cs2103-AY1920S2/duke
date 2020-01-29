import java.io.File;
import java.io.IOException;

import command.Command;

import dukeexception.DukeException;

import parser.Parser;

import storage.Storage;

import task.TaskList;

import ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            File file = new File("data");
            if (!file.exists()) {
                //create new data dir and duke.file
                new File("data").mkdir();
            }
            new File(filePath).createNewFile();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException io) {
                ui.showSavingError();
            } finally {

                ui.showLine();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}