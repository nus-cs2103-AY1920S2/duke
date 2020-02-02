import java.io.*;

/**
 * The Duke program implements an application that
 * is a neckbeard chatbot which also acts as a todo
 * list which can have todos, deadlines and events
 *
 * @author  Benjamin Fheng
 * @version 1.0
 * @since   2020-01-28
 */

public class Duke {

    Ui ui;
    Storage storage;
    TaskList tasks;

    /**
     * Class constructor which creates the Ui, storage and tasklist
     * Creates an ArrayList of tasks from storage if any, also
     * creating the Ui to handle the user inputs
     *
     * @param filePath which takes in the stored list of tasks
     * @throws FileNotFoundException If there is no task text file
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList();
            storage.readFile(tasks.taskList);

        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Runs the program by showing the welcome message
     * and opening up a scanner to inputs in the Ui class
     * to handle user inputs
     *
     */

    public void run() {
        ui.showWelcome();
        ui.readCommands();

    }



    public static void main(String[] args) {

        new Duke("data/tasks.txt").run();

    }


}
