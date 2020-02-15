package lcduke;

import java.io.FileNotFoundException;
import java.text.ParseException;

/** Ths creates a Duke object.
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private Ui ui;

    /** This is the constructor to create the Deadline Object.
     *
     * @param filePath File path of user's hard disk.
     */
    protected Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage.getStorageNo());

        //if there is a problem with reading file, it will create a new TaskList
        } catch (FileNotFoundException | ParseException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return ui.inputProcess(input, storage, tasks);
    }
}
