import java.util.ArrayList;
import dukebot.*;
import dukebot.command.Command;
import dukebot.command.Parser;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;

/**
 * Main class.
 */
public class Duke {
    private static final String PATH = "./dukeStore.txt";

    private void run() {

        Storage storage = new Storage(PATH);
        ArrayList<Task> savedTasks = storage.loadFromFile();
        TaskList tasks = new TaskList(savedTasks);
        Ui ui = new Ui();

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        System.exit(0);
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }
}