/**
 * Main class to run Duke program
 * <p>This program acts as a scheduler that can add tasks to be done, tasks with a deadline and
 * upcoming events </p>
 *
 * @param filePath - File path where schedule is saved. File path is relative to src folder
 */

package duke;

import duke.storage.Storage;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.parser.Parser;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke() {
        String filePath = "data/tasks.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        StringBuilder output = new StringBuilder();

        try {
            output.append(Parser.parse(input, tasks, storage));

            // Exit system one second after Exit command.
            // //@@author jadetayy-reused
            if (output.toString().contains("Bye")) {
                Executors.newSingleThreadScheduledExecutor()
                        .schedule(() -> System.exit(0), 1, TimeUnit.SECONDS);
            }
            
            return output.toString();
        } catch (DukeException e) {
            return e.toString();
        }
    }
}