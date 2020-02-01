import java.io.IOException;

import java.util.List;

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

    /**
     *  parser.scan() will read inputs from user,
     *  subsequently writes output into txt.file
     */
    public void run() {
        try {
            parser.scan();
            List<Task> task = tasklist.getDoneTasks();
            storage.setDoneTasks(task);
            storage.load();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}