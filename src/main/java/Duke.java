import command.*;
import dukeexception.*;
import javafx.application.Platform;
import parser.*;
import storage.*;
import task.*;
import ui.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    public Duke() throws IOException{
        String filePath = "/Users/Simon/Desktop/Y2S2/CS2103T/duke/src/main/java/duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.displayLoadError();
            tasks = new TaskList();
        }
    }

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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




    public static void main(String[] args) throws IOException{
        new Duke().run();
    }

}



