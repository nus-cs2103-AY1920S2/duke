import java.util.Scanner;
import java.io.IOException;

/**
 * Represents Duke, the task tracking smart bot
 * @author Goh Boon Hee SHaun
 * @version 0.1
 */
public class Duke {

    /** Keeps of track of saved files */
    private Storage storage;
    /** A list to store tasks */
    private TaskList tasks;
    /** In charge of the interface the user sees */
    private Ui ui;
    /** Keeps of number of task left undone */
    public static int pendingTask = 0;
    /** To take in user input */
    public static Scanner sc = new Scanner(System.in);

    /**
     * Creates a Duke bot
     *
     * @param filePath where to save and load files when Duke closes
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println("error somewhere");
        }
    }

    /**
     * Runs the Duke bot's processes
     *
     * @throws IOException if buffer reads a NULL input
     */
    public void run() throws IOException {
        ui.printOpeningScreen();
        Parser parser = new Parser(tasks);
        String input = "";
        while ( ! (input = sc.nextLine()).equals ("bye")) {
            ui.commandBreak();
            parser.parse(input);
            ui.commandBreak();
        }
        storage.save(tasks);
        ui.closingScreen();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}