package duke.main;

import duke.exception.DukeException;
import duke.task.TaskList;

public class Duke {

    Storage storage;
    TaskList taskList;

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

    public static void main(String[] args) {
        try {
            new Duke("data/duke.txt").run();
        } catch (DukeException e) {
            Ui.print(e.getMessage());
        }
    }

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
        storage.save(taskList);
    }
}
