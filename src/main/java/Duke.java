import duke.command.Command;
import duke.task.TaskList;
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
    Duke() {
        storage = new Storage();
        taskList = new TaskList();
        storage.load(taskList);
    }

    /**
     * Duke's exit behaviour involves UI output
     * and final storage saving.
     * @return void
     */
    private void exit() {
        Ui.sayBye();
        storage.saveTaskListToFile(taskList);
    }

    /**
     * Duke's behaviour loop after set-up to before exit.
     * @return void
     */
    private void loop() {
        boolean is_exiting = false;
        Command c;
        do {
            String fullCommand = Ui.readCommand();
            c = Parser.parse(fullCommand);
            if (c != null) {
                c.execute(taskList, storage);
                is_exiting = c.isExit();
            }
        } while (!is_exiting);

        exit();
    }
}
