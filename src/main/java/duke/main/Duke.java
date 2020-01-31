package duke.main;

import duke.exception.DukeException;
import duke.task.TaskList;

public class Duke {

    Storage storage;
    TaskList taskList;

    /**
     * Duke Object that forms the backbone of the program and more.
     *
     * @param filepath  is the User's working directory for file reading/saving
     * @throws DukeException    when multiple exceptions are caught (e.g. unfilled secondary input)
     */
    public Duke(String filepath) throws DukeException {
        //Try to read form saved data file and restore index, if not create a list to save later
        try {
            storage = new Storage(filepath);
            taskList = new TaskList(storage);
        } catch (DukeException e) {
            Ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * main Method that begins the program.
     *
     * @param args  are multiple inputs received from User's input
     */
    public static void main(String[] args) {
        try {
            new Duke("data/duke.txt").run();
        } catch (DukeException e) {
            Ui.print(e.getMessage());
        }
    }

    /**
     * run Method that executes the actual program.
     *
     * @throws DukeException    when multiple exceptions are caught (e.g. unfilled secondary input)
     */
    public void run() throws DukeException {
        //Duke Setup
        boolean dukeRunning = true;

        //Welcome Text
        Ui.welcome();

        //Main Program now in Switch, might need to turn cases into separate methods soon
        while (dukeRunning) {
            String input = Ui.getInput();
            dukeRunning = Parser.parseCommand(input, taskList);
        }

        //Save new data to file before exiting
        storage.save(taskList);
    }
}
