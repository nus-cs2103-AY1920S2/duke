import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The main class that runs the duke project.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor of an instance of Duke class.
     * It creates instances of Ui, Storage, and TaskList for processing.
     *
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage("data/duke.txt");
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The driver method that runs the whole duke project.
     *
     * @throws IOException .
     */
    private void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ui.start();
        boolean hasNext = true;
        while (hasNext) {
            Parser ps = new Parser(br.readLine());
            hasNext = ps.canContinue(ui);
            storage.save(tasks);
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }

    public String start() {
        return ui.start();
    }

    private String getMessage(String input) {
        try {
            Parser parser = new Parser(input);
            String reply = parser.dukeReply(ui, tasks);
            storage.save(tasks);
            return reply;
        } catch (IOException e) {
            return new DukeException("OOPS! There is an error saving the task list").errMsg();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return getMessage(input);
    }
}
