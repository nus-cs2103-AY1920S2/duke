import commands.Command;
import dukeexception.DukeException;
import dukeexception.LoadException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;


import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This programme implements an application that simulates like a chat bot.
 * Features include adding/deleting/searching Tasks, mark Tasks as done, listing Tasks
 * and exiting chat bot.
 */

public class Duke {

    Storage storage;
    TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     * Creates a new Duke object when called.
     */
    public Duke() {
        storage = new Storage("./data/tasks.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (LoadException e) {
            e.getMessage();
            tasks = new TaskList();
        }
    }


    /**
     * This method takes in user input to give necessary output.
     * @return response for the user input.
     */
    public String getResponse(String input) {
        try {
            if (input.equals("help")) {
                 return MainWindow.welcomeMsg();
            } else {
                Command c = Parser.parse(input);
                if (input.equals("bye")) {
                    Executors.newSingleThreadScheduledExecutor()
                            .schedule(() -> System.exit(0), 1, TimeUnit.SECONDS);
                }
                return c.execute(tasks, ui, storage);
            }
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }



}