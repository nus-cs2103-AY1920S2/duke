package duke.program;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.interaction.Ui;
import duke.interaction.Parser;

/**
 * The Duke program implements an application that manipulates a collection of tasks.
 * This class contains overall behaviour functions like <code>loop</code> and <code>exit</code>.
 * This class contains the <code>main</code> function, as well.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;

    /**
     * Application's <code>main</code> method initializes a Duke object,
     * greets the user via the UI and calls the behavioural <code>loop</code> method.
     */
    public static void main(String[] args) {
        // Init Duke
        Duke d = new Duke();
        // Greet user on start
        Ui.greet();
        // Duke's behaviour loop
        d.loop();
    }

    /**
     * Duke's constructor initializes Storage and TaskList
     * and loads user data into the new task list.
     */
    public Duke() {
        storage = new Storage();
        taskList = new TaskList();
        storage.load(taskList);
    }

    /**
     * Duke's exit behaviour involves UI output
     * and final storage saving.
     */
    private void exit() {
        Ui.sayBye();
        storage.saveTaskListToFile(taskList);
    }

    /**
     * Duke's behaviour loop after set-up to before exit.
     */
    private void loop() {
        boolean isExiting = false;
        Command c;
        do {
            String fullCommand = Ui.readCommand();
            try {
                c = Parser.parse(fullCommand);
                c.execute(taskList, storage);
                isExiting = c.isExit();
            } catch (DukeException.InvalidCommand invalidCommand) {

            }
        } while (!isExiting);

        exit();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c;
        String output = "";
        boolean isExiting = false;

        try {
            c = Parser.parse(input);
            output = c.executeWithBotResponse(taskList, storage);
            isExiting = c.isExit();

            if (isExiting) {
                // TODO: Find a more graceful way to exit?
                System.exit(0);
            }
        } catch (DukeException.InvalidCommand invalidCommand) {
            output = invalidCommand.getMessage();
        }

        return output;
    }
}
