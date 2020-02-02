package com.duke;

import com.duke.command.Command;
import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Parser;
import com.duke.util.Storage;
import com.duke.util.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

/**
 * Driver class of the Duke program. To start a new session,
 * instantiate a new <code>Duke</code> object with the data file path and call run();
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiate a new <code>Duke</code> object with the data file path
     * that stores the task information.
     *
     * @param filePath the file path of the file that stores the task information.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Starts a Duke session.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String runOnGui(String input) {
        try {
            Command c = Parser.parse(input);
            ui.showGoodbye();
            String message = c.executeOnGui(tasks, ui, storage);
            message = ui.getLine() + "\n" + message + "\n";
            return message;
        } catch (DukeException e) {
            String message = ui.gerErrorMessage(e.getMessage());
            message = ui.getLine() + "\n" + message + "\n";
            return message;
        }
    }

    public static void main(String[] args) {
        new Duke("../duke/data/duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return runOnGui(input);
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

}