import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

/**
 * Duke is a command line application that lets users store tasks they wish to track.
 * We have here our driver class which initialize other main components of the app.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * A constructor which takes instantiates other components.
     * @param filePath Path name of the file we want to load.
     */
    public Duke(String filePath) {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage(filePath);
        this.tasks = storage.load();
        this.parser = new Parser();
    }

    /**
     * Handles the printing of welcome and exit messages.
     * Running of app is abstracted by a method to keep things simple here.
     */
    public void run() {
        ui.showWelcome();
        runUntilExit();
        ui.showExit();
    }

    private void runUntilExit() {
        while (true) {
            String commandText = ui.getUserCommand();
            if (commandText.equals("bye")) {
                break;
            }
            parser.handleTaskCommand(commandText, tasks);
            ui.showLine();
        }
        this.storage.save(tasks);
    }

    /**
     * Main is the driver function that creates an instance of Duke for running.
     * @param args Command line arguments, simply here by convention.
     */
    public static void main(String[] args) {
        new Duke("data\\duke.txt").run();
    }
}
