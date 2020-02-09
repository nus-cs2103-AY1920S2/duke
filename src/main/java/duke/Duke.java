package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Duke provides a todo list functionality.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for duke.Duke
     * @param filePath provide a hardcoded directory path to the text file to be used as a database
     */
    public Duke(String filePath) {
        ui = new duke.Ui();
        storage = new duke.Storage(filePath);
        try {
            tasks = new duke.TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception when loading database");
            ui.showLoadingError();
            tasks = new duke.TaskList();
        }
    }

    /**
     * Runs the todo list.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                duke.Command c = duke.Parser.parse(fullCommand); //throws duke.DukeException
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (duke.DukeException e) {
                ui.showError(e.getErrorMessage());
            } finally {
                ui.showLine();
            }
        }

        // saving tasks
        try {
            ui.showSavingTasks();
            ui.showLine();
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("error saving tasks");
            e.printStackTrace();
        }
    }

    /**
     * Entry point for the JVM
     * @param args default main method signature
     */
    public static void main(String[] args) {
        new Duke("C:\\Users\\Pang Jia Da\\Desktop\\CS2103\\duke\\data\\duke.txt").run();
    }
}