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
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private final Parser parser;

    /**
     * Initialises Duke.
     */
    public Duke(boolean withGui) {
        storage = new Storage(PATH);
        ui = new Ui(withGui);
        parser = new Parser();

        if (withGui) {
            ui.showWelcomeGui();
            ui.getDukeVoice().playVoice();
        } else {
            ui.showWelcome();
        }

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
     * Gets the current UI for text output.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Generates Response for GUI.
     */
    public String getResponse(String input) {
        ui.resetGuiOutput();
        Command c = parser.parse(input);
        c.execute(tasks, ui, storage);

        if (c.isExit()) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ie) {
                        System.exit(0);
                    }
                    System.exit(0);
                }
            }).start();
        }

        ui.getDukeVoice().playVoice();
        return ui.getGuiOutput();
    }

    /**
     * Main method for command prompt.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(false);
        duke.run();
    }

    private void run() {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        System.exit(0);
    }
}