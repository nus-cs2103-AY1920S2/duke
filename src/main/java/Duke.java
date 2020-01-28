import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * Duke provides a todo list functionality.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke
     * @param filePath provide a hardcoded directory path to the text file to be used as a database
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception when loading database");
            ui.showLoadingError();
            tasks = new TaskList();
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
                Command c = Parser.parse(fullCommand); //throws DukeException
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
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