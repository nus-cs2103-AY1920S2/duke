package duke;

import duke.exception.DukeException;
import duke.interact.Parser;
import duke.interact.Ui;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a new Duke object which initializes the TaskList, Ui and Storage.
     */
    public Duke() {
        String programDir = System.getProperty("user.dir");
        String fileName = "duke.txt";
        Path path  = Paths.get(programDir, "data", fileName);

        try {
            storage = new Storage(path);
            tasks = storage.load();
            Parser parser = new Parser();
            ui = new Ui(parser);
        } catch (DukeException error) {
            System.out.println(error);
        }
    }


    /**
     * Returns the output from the program.
     * @param input String the user inputted.
     * @return String reply from the program.
     */
    public String getResponse(String input) {
        String output = ui.getOutput(input, tasks, storage);
        assert !output.isEmpty() : "The output should not be empty.";
        return output;
    }

}
