package dukeApp.duke;
import dukeApp.files.*;

import dukeApp.files.Storage;
import dukeApp.files.TaskList;
import dukeApp.files.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
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

    /**
     * Run the app by reading user commands and writing to file to save changes
     *
     */
    public void run() throws DukeException {
        ui.input(tasks);
        try {
            storage.appendToFile(tasks.aList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " +e.getMessage());
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) throws DukeException {
        final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        new Duke("data\\duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "dukeApp.duke.Duke heard: " + input;
    }
}