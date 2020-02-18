import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates a Duke instance with a path to a save file.
     * @param filePath The save file location relative to the project root
     */
    public Duke() {
        this.ui = new Ui();
    }

    /**
     * The main method of the Duke class.
     * @param args Arguments to be passed into Duke
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Starts the Duke program after initialisation.
     */
    public void run() {
        Ui.printWelcomeMessage();

        String saveLocation = ui.readCommand();
        if (saveLocation.equals("")) {
            saveLocation = "data/duke.txt";
        }
        this.storage = new Storage(saveLocation);
        this.tasks = new TaskList(storage.loadTasks());

        Ui.printMessage("How may I help you?");

        boolean isByeCommand = false;
        while (!isByeCommand) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isByeCommand = c.isByeCommand();
            } catch (DukeException ex) {
                ui.printMessage(ex.getMessage());
            }
        }

        Ui.printMessage("Bye! Hope you visit again soon!");
    }

    public String getResponse(String input) {
        return String.format("Duke repeats: %s", input);
    }
}
