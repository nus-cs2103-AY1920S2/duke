import command.Command;

import dukeexception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/nerd.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dwayne.png"));

    /**
     * Represents a class Duke which runs the program.
     * @throws IOException exception is thrown when file is not found
     */
    public Duke() throws IOException {
        String filePath = "data/duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.displayLoadError();
            File file = new File("data");
            if (!file.exists()) {
                new File("data").mkdir();
            }
            tasks = new TaskList();
        }
    }

    /**
     * Represents a method which runs the program.
     */
    public void run() {
        ui.displayIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            } catch (IOException e) {
                ui.displaySaveError();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Method parses the user input in the gui to translate into designated output by the program.
     * @param input input that user enters
     * @return returns a string of output that is displayed in the GUI
     */
    protected String getResponse(String input) {
        assert ui != null;
        assert storage != null;
        assert tasks != null;
        // Create stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        // Saving old System.out
        PrintStream old = System.out;
        // Telling java to use special stream
        System.setOut(printStream);
        try {
            Command c = Parser.parseCommand(input);
            c.execute(tasks, ui, storage);
            System.out.flush();
            System.setOut(old);
        } catch (DukeException e) {
            ui.displayError(e.getMessage());
        } catch (IOException e) {
            ui.displaySaveError();
        } finally {
            return baos.toString();
        }
    }




    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

}



