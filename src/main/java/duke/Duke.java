package duke;

import duke.commands.Parser;
import duke.commands.Storage;
import duke.commands.TaskList;
import duke.commands.Ui;

/**
 * the class containing the main function to run the programme.
 */
public class Duke {

    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui = new Ui();

    /**
     * runs the programme.
     * @param args arguments
     */
    public static void main(String[] args) {
        ui.start();
        taskList = new TaskList();
        storage = new Storage("duke.txt", taskList);
        storage.retrieveInfo();
        parser = new Parser(taskList);
        parser.parse();
        storage.updateInfo();
    }
}
