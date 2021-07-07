package seedu.duke;

import javafx.application.Platform;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
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
    public Duke() {
        String filePath = "duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList(null, null);
        } catch (IOException e) { // if file path does not exist.
            storage.makeNewFile();
            taskList = new TaskList(new ArrayList<>(), storage);
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
                ui.print(e.toString());
            } catch (IOException e) {
                ui.print(e.toString());
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

    //@@author johannagwan-reused
    //Reused from https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java/8708357
    //with minor modifications.
    /**
     * Generates a response to user input.
     *
     * @param input The user input.
     * @return Duke's response.
     */
    protected String getResponse(String input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Parser parser = new Parser();
        String[] inputs = input.split(" ", 2);
        Command cmd = parser.handleCommands(inputs, taskList);
        cmd.execute(taskList, ui, storage);

        if (!cmd.hasNextCommand()) {
            Platform.exit();
        }

        System.out.flush();
        PrintStream old = System.out;
        System.setOut(old);
        return baos.toString();
    }
    //@@author
}