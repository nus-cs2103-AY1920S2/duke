// java imports
import java.util.Scanner;
import java.io.FileNotFoundException;

// packages imports
import tasks.TaskList;
import ui.Ui;

/**
 * Main class of the chat bot program.
 */
public class Duke {
    /** User interface class with formatted outputs. */
    private Ui ui;
    /** Allows for persistent data. */
    private Storage storage;
    /** List to store all tasks. */
    private TaskList taskList;

    /** Creates a bot with personalize user interface, storage, and task list.
     * Will create a new save file is there is no existing one.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        try {
            storage.readSaveFile(taskList);
        } catch (FileNotFoundException ex) {
            ui.printFormattedOutput("No saved task list found. Creating a new one...");
        }
    }

    /**
     * Main function that abstracts the chat bot's functionalities.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(taskList, storage, ui, sc);

        ui.printFormattedOutput("Hello! I'm Duke\n    What can I do for you?");

        // Input-Response logic
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            parser.parse(input);
            input = sc.nextLine();
        }

        ui.printFormattedOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Entry point of the program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}