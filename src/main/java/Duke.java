import java.io.IOException;
import java.util.Scanner;

/**
 *  Duke Class.
 *  The main class to run the whole code.
 *
 * @author Amos Cheong
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String FilePath;

    public Duke(String filePath) {
        FilePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), ui);
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }


    /**
     * Find out the type of command and execute it.
     * @param input The line input by the user.
     */
    public static void execcommand(String input, TaskList tasks, Ui ui) {
        String[] arguments = input.split(" ", 2);

        DukeCommand.valueOf(arguments[0].toUpperCase()).execute(input, tasks, ui);
    }

    /**
     * A method to get the file path from the Duke object.
     * @return String The path of the file or the file location.
     */
    public String getFilePath() {
        return this.FilePath;
    }

    /**
     * Runs the whole code.
     */
    public void run() {
        // Greet the user
        ui.greetUser();

        Scanner sc = new Scanner(System.in);

        // Exits when the user types 'bye'
        String line = sc.nextLine();
        while (!line.equals("bye")) {
            // Check for any errors in the command
            // by passing command to Parser object
            // If no errors, method checkForError()
            // returns true
            if (new Parser(line, ui).checkForError(tasks))
                // Assumption : To add things into the list, the user has to
                // type a [command][description]. For example, to add
                // "read book" into the list, the user has to type
                // "todo read book" instead of just typing "read book"
                // as typing "read book" will cause the code to throw an exception
                execcommand(line, tasks, ui);
            // Wait for command
            line = sc.nextLine();
        }

        try {
            storage.store(tasks);
        } catch (IOException ioex) {
            ui.showErrorMessage(ioex.getMessage());
        }

        // Say 'bye' to the user
        ui.exit();
    }
    /**
     * The main method of the class Duke.
     * @param args Unused.
     * @return Nothing.
     **/
    public static void main(String[] args) {
        new Duke("C:\\Users\\SOHNB101\\Documents\\myduke\\duke\\data\\duke.txt").run();
    }
}