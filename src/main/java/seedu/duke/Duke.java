package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
     * @throws IOException If an input or output exception occurred.
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
            Command cmd = null;
            try {
                String input = sc.nextLine();
                String[] inputs = input.split(" ", 2);
                cmd = parser.handleCommands(inputs, taskList);
                if (!cmd.hasNextCommand()) {
                    break;
                }
                cmd.execute(taskList, ui, storage);
            } catch (NullPointerException e) {

            } catch (IOException e) {

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
     * Generates a response to user input.
     *
     * @param input The user input.
     * @return Duke's response.
     */
    protected String getResponse(String input) {
//            if (!parser.hasNextCommand()) {
//                break;
//            }
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Parser parser = new Parser();
        String[] inputs = input.split(" ", 2);
        parser.handleCommands(inputs, taskList);

        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }
}