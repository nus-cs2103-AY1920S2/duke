package duke.duke;

import duke.exception.DukeException;
import duke.exception.FindException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.command.Command;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The type Duke.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList = new TaskList();

    /**
     * Instantiates a new Duke.
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/test.txt");
    }

    /**
     * Main class which runs the logic of the program after the input is gotten
     * From the user through the javafx.
     *
     * @param input input string from the user.
     * @return The output after the string is formatted.
     * @throws DukeException when the user enters a wrong message.
     * @throws IOException   when the user enters a wrong input.
     */
    public String run(String input) throws DukeException, FindException, IOException {
        String fullCommand = ui.readCommand(input);
        Command c = Parser.parseCommand(fullCommand, input);
        return c.execute(storage, ui, taskList);
    }


}