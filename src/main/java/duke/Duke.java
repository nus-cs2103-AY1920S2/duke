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
            return output.toString();
        } catch (DukeException e) {
            return e.toString();
        }
    }
}