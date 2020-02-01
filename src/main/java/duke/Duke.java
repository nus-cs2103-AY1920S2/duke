package duke;

import duke.entity.*;
import duke.entity.command.Command;
import duke.handler.Ui;

import duke.exception.DirectoryNotFoundException;
import duke.handler.Storage;
import duke.parser.CommandParser;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private CommandParser commandParser;

    public Duke() {
        ui = new Ui();
        commandParser = new CommandParser(ui);
        Path path = Paths.get("src", "main", "java", "memory");
        try {
            storage = new Storage(path, "TaskList.txt");
            tasks = new TaskList(storage.loadTaskFromMemory());
        } catch (FileNotFoundException e) {
            //System.out.println("No saved tasks found!");
            tasks = new TaskList();
        } catch (DirectoryNotFoundException e) {
            //System.out.println("Directory not found! No saved tasks found!");
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();

    }

    public void run() {

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = commandParser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            ui.showLine();
        }


    }
}