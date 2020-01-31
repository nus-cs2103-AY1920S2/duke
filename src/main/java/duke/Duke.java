package duke;

import duke.commands.Parser;
import duke.commands.Storage;
import duke.commands.TaskList;
import duke.commands.Ui;

/**
 * the class containing the main function to run the programme.
 */
public class Duke {

    private static Ui ui = new Ui();
    public static TaskList taskList = new TaskList();
    private static Storage storage = new Storage("duke.txt");
    private static Parser parser = new Parser();

    /**
     * runs the programme.
     * @param args arguments
     */
    public static void main(String[] args) {
        ui.start();
        storage.retrieveInfo();
        parser.parse();
        storage.updateInfo();
    }
}
