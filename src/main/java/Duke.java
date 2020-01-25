import java.util.ArrayList;
import dukebot.command.Command;
import dukebot.command.Parser;
import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.Ui;

/**
 * Main class.
 */
public class Duke {
    private static final String PATH = "./dukeStore.txt";

    private void run() {
        Storage storage = new Storage(PATH);
        Ui ui = new Ui();
        ui.showWelcome();

        TaskList tasks;
        try {
            ArrayList<Task> taskArrayList = storage.loadFromFile();
            tasks = new TaskList(taskArrayList);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
            ArrayList<Task> taskArrayList = new ArrayList<>();
            tasks = new TaskList(taskArrayList);
            try {
                storage.saveToFile(tasks);
            } catch (DukeException g) {
                ui.sayLine(g.getErrorLineName());
            }
        }

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