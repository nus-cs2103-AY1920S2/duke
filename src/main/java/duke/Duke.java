package duke;

import java.io.File;
import java.io.IOException;

import duke.command.Command;
import duke.task.TaskList;
import duke.exception.DukeException;

/**
 * Main class for the Duke chatbot application.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private String fileName;
    private Parser parser;
    private Ui ui;

    /**
     * Constructs a fresh Duke instance with a supplied file storing the task list.
     * @param fileName Path to the file storing the task list.
     */
    public Duke(String fileName) {
        this.fileName = fileName;
        storage = new Storage(fileName);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser();
        ui = new Ui();
        File file = new File(this.fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            Ui.printLines("File creation failed.");
        }
    }

    public String getResponse(String input) {
        assert input.length() > 0 : "Input cannot be empty";

        String result = "";

            try {
                Command command = parser.parseCommand(input);
                result += command.execute(tasks, ui, storage);
            } catch (DukeException e) {
                result = e.getMessage();
                // result = "Sorry, invalid command. Try again with the following:\ntodo, deadline, event";
            } catch (ArrayIndexOutOfBoundsException e) {
                result = "Sorry, invalid syntax or command. Please try again!";
            }

        return result;
    }
}