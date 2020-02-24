import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        String filePath = new File("").getAbsolutePath();
        int ind = filePath.lastIndexOf("/");
        assert ind == -1 : "File path is incorrect";
        String path = filePath.substring(0, ind + 1);
        filePath = path.concat("duke.txt");
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //if file is empty
            tasks = new TaskList();
        }
    }

    /**
     * Parses user input and executes the same.
     *
     * @return String to be displayed.
     * @throws IOException Throws IOException.
     */
    public String getResponse(String fullCommand) throws IOException {
        try {
            Command c = Parser.parse(0,fullCommand,0, tasks);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
