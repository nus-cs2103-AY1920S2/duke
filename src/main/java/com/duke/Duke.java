package com.duke;

import com.duke.command.Command;
import com.duke.tag.TagList;
import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Parser;
import com.duke.util.Storage;
import com.duke.util.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Driver class of the Duke program. To start a new session,
 * instantiate a new <code>Duke</code> object with the data file path and call run();
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private TagList tags;

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
            tasks = new TaskList(storage.loadTaskList());
            tags = storage.loadTags(tasks);
        } catch (IOException e) {
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
                c.execute(tasks, ui, storage, tags);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Handles a user input command through duke and returns a message to be displayed on GUI.
     * @param input The input string of user command.
     * @return The message to be displayed on GUI.
     */
    private String runOnGui(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "command fails to be meaningful";
            String message = c.executeOnGui(tasks, ui, storage, tags);
            assert message != null : "output message is empty";
            message = ui.getLine() + "\n" + message + "\n";
            return message;
        } catch (DukeException e) {
            String message = ui.getErrorMessage(e.getMessage());
            assert message != null : "output message is empty";
            message = ui.getLine() + "\n" + message + "\n";
            return message;
        }
    }

    public static void main(String[] args) {
        new Duke("../duke/data/duke.txt").run();
    }

    /**
     * Obtains the output string to be displayed on GUI of a particular user command.
     * @param input The input string of user command.
     * @return The message to be displayed on GUI.
     */
    public String getResponse(String input) {
        return runOnGui(input);
    }

    /**
     * Obtains the output string to be displayed on GUI of the welcoming message when initiating Duke.
     * @return The welcoming message to be displayed on GUI.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

}