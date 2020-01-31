package dukebot;

import dukebot.command.Command;
import dukebot.command.Parser;
import dukebot.exception.DukeException;
import dukebot.gui.DialogBox;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Duke {
    private static final String PATH = "./dukeStore.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        storage = new Storage(PATH);
        ui = new Ui(true);
        ui.showWelcomeGui();
        try {
            ArrayList<Task> taskArrayList = storage.loadFromFile();
            tasks = new TaskList(taskArrayList);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
            ArrayList<Task> taskArrayList = new ArrayList<>();
            tasks = new TaskList(taskArrayList);
            try {
                storage.saveToFile(tasks);
            } catch (DukeException g) {
                ui.sayLine(g.getErrorLineName());
            }
        }
    }

    /**
     *  Gets the current UI output
     */
    public String getUiOutput() {
        return ui.getGuiOutput();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ui.resetGuiOutput();
        Command c = Parser.parse(input);
        c.execute(tasks, ui, storage);
        if (c.isExit()) {
            // need to set timeout here somehow
            System.exit(0);
        }
        return ui.getGuiOutput();
    }

    /**
     * Main method for command prompt.
     */
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Storage storage = new Storage(PATH);
        Ui ui = new Ui();
        ui.showWelcome();

        TaskList tasks;
        try {
            ArrayList<Task> taskArrayList = storage.loadFromFile();
            tasks = new TaskList(taskArrayList);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
            ArrayList<Task> taskArrayList = new ArrayList<>();
            tasks = new TaskList(taskArrayList);
            try {
                storage.saveToFile(tasks);
            } catch (DukeException g) {
                ui.sayLine(g.getErrorLineName());
            }
        }

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        System.exit(0);
    }
}