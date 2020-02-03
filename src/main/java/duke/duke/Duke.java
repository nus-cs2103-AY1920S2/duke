package duke.duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;

/**
 * The type Duke.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList = new TaskList();


    /**
     * The constant breakChecker.
     */
    public static boolean breakChecker = false;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DukeException the duke exception
     * @throws IOException   the io exception
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run("");
    }

    /**
     * Instantiates a new Duke.
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
    }

    /**
     * Main class which runs the logic of the program after the input is gotten
     * From the user through the javafx.
     * @param input input string from the user.
     * @return The output after the string is formatted.
     * @throws DukeException when the user enters a wrong message.
     * @throws IOException when the user enters a wrong input.
     */
    public String run(String input) throws DukeException, IOException {
        String returned = "";
        ui.showWelcome();

        String fullCommand = ui.readCommand(input);
        Command c = Parser.parse(fullCommand, input);
        returned = c.execute(storage, ui, taskList);
        //            if (breakChecker) {
        //                break;
        //            }
        //        }
        return returned;
    }


}