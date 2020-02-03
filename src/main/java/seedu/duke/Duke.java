package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class of Duke.
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for Duke.
     *
     * @throws IOException if an input or output exception occurred
     */
    public Duke() throws IOException {
        String filePath = "data/duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList(null, null);
        }
    }

    /**
     * Reads the user command and passes it to Ui to be processed accordingly.
     */
    private void runDuke() {
        ui.greet();
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ", 2);
            parser.handleCommands(inputs, taskList);
            if (!parser.hasNextCommand()) {
                break;
            }
        }
    }

    /**
     * Main method to run Duke.
     */
    public static void main(String[] args) throws IOException {
        Duke duke;
        duke = new Duke();
        duke.runDuke();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }
}