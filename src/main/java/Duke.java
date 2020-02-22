import java.io.IOException;

public class Duke {
    private Storage storage;
    private Parser parser = new Parser();
    private Ui ui = new Ui();
    private TaskList tasklist;
    private String allInst;

    /**
     * initialises Storage and TaskList classes.
     * @param filePath file to be read / written to.
     */
    public Duke(String filePath) throws IOException {
        ui.greeting();
        storage = new Storage(filePath);
        try {
            storage.readFileContents();
        } catch (IOException e) {
            tasklist = new TaskList();
        }
    }

    public Duke() throws IOException {

    }

    public String getGreet() {
        return ui.greeting();
    }

    /**
     * takes in user input as string and output it as task
     * @param input takes in user input
     * @return the reply by bot in task
     */
    public String getResponse(String input) {
        String s = "";
        try {
            s = parser.scan(input);
            return s;
        } catch (Exception e) {
            System.err.println(e);
        }
        return s;
    }
//    }
}

