package duke;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.command.Command;


/**
 * Represents the main logic flow of the Duke Application.
 * A <code>Storage</code> object stores all list data into disk.
 * A <code>TaskList</code> object stores list of user tasks in RAM.
 * A <code>Ui</code> object handles input and output interaction with user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public Duke() {
    }

    /**
     * A loop that continuously seeks commands from user and executes them.
     * If bye command is received, the loop terminates.
     */
    public void run() {
        ui.printWelcomeMessageAndGetName();
        boolean isActive = true;

        while (isActive) {
            try {
                String instruction = ui.getInstruction();
                Command c = Parser.parse(instruction);
                isActive = c.execute(tasks, ui);
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        storage.save(tasks.getList());
    }

    public static void main(String[] args) {
        new Duke("data\\duke.txt").run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}