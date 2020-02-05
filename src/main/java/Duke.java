import java.io.IOException;

public class Duke {
    private Storage storage;
    private Parser parser = new Parser();
    TaskList tasklist;
    String allInst;

    /**
     * initialises Storage and TaskList classes
     * @param filePath file to be read / written to
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath); //reads txt file
            tasklist = storage.readFileContents();
            allInst =  tasklist.convertToString();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public Duke() {

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

