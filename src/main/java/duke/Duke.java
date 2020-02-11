package duke;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.time.DateTimeException;

import duke.command.Command;

import duke.dukeexception.DukeException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() throws IOException {
        this("data/duke.txt");
    }
    /**
     * Creates a Duke object.
     * @param filePath The file path to Duke.txt.
     * @throws IOException If Duke.txt is not found.
     */

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            File file = new File("data");
            if (!file.exists()) {
                //create new data dir and duke.file
                new File("data").mkdir();
            }
            new File(filePath).createNewFile();
            tasks = new TaskList();
        }
    }
    /**
     * Returns the respond by Duke.
     * @param input The command entered by the user.
     * @return The result of the command to be displayed.
     */

    public String getResponse(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            System.out.flush();
            System.setOut(old);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException io) {
            ui.showSavingError();
        } catch (DateTimeException dt) {
            System.out.println("      Please enter date in this format: YYYY-MM-DD");
        } catch (IndexOutOfBoundsException iobe) {
            System.out.println("      Sorry, index is out of bounce.");
        } finally {
            return baos.toString();
        }
    }
    /**
     * Runs the duke program.
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException io) {
                ui.showSavingError();
            } catch (DateTimeException dt) {
                System.out.println("      Please enter date in this format: YYYY-MM-DD");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}