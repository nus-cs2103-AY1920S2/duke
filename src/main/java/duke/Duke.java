package duke;

import duke.Ui.Ui;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.TaskStorage;

import java.io.FileNotFoundException;

/**
 * Represents the Chat Bot feature of the application.
 * Comprises of a User Interface, and Storage for app data.
 */
public class Duke {
    /**
     * The Storage object with in-memory and persistently stored data.
     */
    private TaskStorage taskStorage;

    /**
     * The Ui object that gives feedback to the user.
     */
    private Ui ui;

    /**
     * Initialises a Duke object.
     * The Duke constructor takes in no parameters.
     */
    public Duke() {
        try {
            this.ui = new Ui();
            this.taskStorage = new TaskStorage("./data/Storage.txt");

            assert this.ui != null : "ui instance in duke is null";
            assert this.taskStorage != null : "taskStorage instance in duke is null";

        } catch (FileNotFoundException e) {
            System.out.println("./data/Storage.txt");
            System.out.println("File not found");
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.ui, this.taskStorage);
        } catch (Exception e) {
            return this.ui.showErrorMessage(e);
        }
    }
}
