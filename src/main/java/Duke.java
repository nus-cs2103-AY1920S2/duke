import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * The main class for the Duke chat bot.
 * Instantiates a storage, tasks and ui object for the runtime of the program.
 */
public class Duke {

    public Storage storage;
    public TaskList tasks;
    public Ui ui;

    /**
     * Instantiates a Duke instance with a path to a save file.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        this.tasks = new TaskList(storage.loadTasks());
    }

    /**
     * The main method of the Duke class.
     *
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

    /**
     * Retrieves a response from the Duke chat bot after sending it a command.
     * @param input command sent to Duke
     * @return the response from Duke
     */
    public String getResponse(String input) {
        String response;

        // Change stdout for duke
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException dukeException) {
            // Display error message
            System.out.print(dukeException.getMessage());
        }

        response = output.toString();
        response = response.trim();

        // Reset stdout for duke
        System.setOut(System.out);
        return response;
    }

    public String printWelcome() {
        return Ui.printWelcomeMessage();
    }
}
