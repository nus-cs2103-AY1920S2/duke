package dukeApp.duke;
import dukeApp.files.*;
import dukeApp.files.Storage;
import dukeApp.files.TaskList;
import dukeApp.files.Ui;
import javafx.application.Application;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static Duke d;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {}

    public Duke(String filePath) {
        storage = new Storage(filePath);

        try {
            ui = new Ui();
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getWelcome() {
        return "Hello I am Pusheen, your personal task manager! What can I do for you?\n";
    }

    /**
     * Run the app by reading user commands and writing to file to save changes
     *
     */
    public String run(String input) throws DukeException {
        String msg = ui.input(tasks, input);
        try {
            storage.appendToFile(tasks.aList);
        } catch (IOException e) {
            msg = "Something went wrong: " +e.getMessage();
        }
        return msg;
    }

    public static void main(String[] args) throws DukeException {
        d = new Duke("data\\duke.txt");
        Application.launch(Main.class, args);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        input = d.run(input);
        return input;
    }
}