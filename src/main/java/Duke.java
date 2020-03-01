import java.util.Scanner;

/**
 * CS2103 Individual Project.
 * Class for Duke chat bot with main class.
 * This program is a chat bot which manages a list of tasks. Upon exit, the tasks will
 * be saved to a data file.
 * @author Chiang Shun De
 */

public class Duke {

    private Storage storage;
    private Ui ui;
    private String effect;
    private TaskList tasks;

    /**
     * Constructor method to create an instance of Duke.
     * @param filePath the String representation of the file location
     */
    public Duke(String filePath) {
        this.effect = "************************************************************";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Handles the main operations of Duke.
     * A scanner will scan the user input and this method will return the corresponding
     * action. Once the operations has been completed, a file will be saved based on the
     * current list.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greet(this.effect);
        while(!ui.checkFinished()) {
            String userInput = sc.nextLine();
            assert userInput.length() > 0 : "The input is not valid.";
            Parser parser = new Parser(userInput);
            parser.verifyCommand(this.tasks);
            this.tasks = ui.handleInput(userInput, tasks);
        }
        ui.farewell();
        storage.saveCurrentList(this.tasks.getTaskArray());
    }

    /**
     * The main method for Duke.
     * The method creates an instance of Duke based on the file location and starts the
     * operations of Duke.
     */
    public static void main(String[] args) {
        System.out.println("booting up...");
        new Duke("data/data.txt").run();
    }
}


