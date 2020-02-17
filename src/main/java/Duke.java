import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Duke is a chat bot program that builds a to do list. Current functions include:
 * list, delete, done, todo, deadline, event, bye.
 *
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for main class for the program.
     * @param filePath relative path of the file that the data of to do list is saved in.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public Duke() {
        String filePath = "./data/duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main driver for Duke program.
     */
    public void run() {
        ui.welcomeMessage();
        tasks.showCurrentTasks();
        ui.showLineBreak();
        boolean isFinished = false;
        while (!(isFinished)) {
                String inputFromUser = ui.handleInput();
                Command c = Parser.parse(inputFromUser);
                if (c == null) {
                    System.out.println("Input something into me!");
                }
                tasks.runCommand(c);
                if (c.getCommand().equals("bye")) {
                    isFinished = true;
                }
                this.storage.save(tasks);
        }
        ui.terminateMessage();
    }

    /**
     * Shows the welcome message for the GUI
     * @return String Welcome Message in the GUI
     */
    public String getWelcomeMessage() {
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        ui.welcomeMessage();
        tasks.showCurrentTasks();
        ui.showLineBreak();

        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }


    /**
     * Shows the current tasks in Duke
     * @return String of current tasks in duke
     */
    public String getCurrentTasksInDuke() {
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        tasks.showCurrentTasks();
        ui.showLineBreak();

        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }


    public String getResponse(String input) {
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
            Command c = Parser.parse(input);
        assert (c != null) : "Input something correct into me! todo deadline (/by) event (/at) find list delete done" ;
        tasks.runCommand(c);
        this.storage.save(tasks);
        System.out.flush();
        System.setOut(old);
        return baos.toString();

    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}
