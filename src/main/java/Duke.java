import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.command.*;

import java.util.Scanner;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Duke Task Manager */
public class Duke {

    /** Storage object to load and save tasks to text file. */
    private Storage storage;

    /** Task list object to store tasks. */
    private TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            String message = Parser.parseUserInput(input, storage, taskList).execute(storage, taskList);
            return "Duke heard: " + message;
        } catch (DukeException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }

    /**
     * Starts the Duke program.
     * Invokes the run() method to start simulation.
     *
     * @param args Main method arguments for when program runs.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Constructs the Duke program.
     */
    protected Duke() {
        try {
            this.storage = new Storage("data/duke.txt");
            this.taskList = this.storage.loadTaskList();
        } catch (DukeException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Starts simulation.
     */
    private void run() {
        Ui.printIntro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                System.out.println(input);
                Command command = Parser.parseUserInput(input, this.storage, this.taskList);
                command.execute(storage, taskList);
                if (command.isBye()) break;
            } catch (DukeException e) {
                System.err.println(e.toString());
                continue;
            } catch (ArrayIndexOutOfBoundsException x) {
                Exception e = new DukeException("Please enter a valid instruction!");
                System.err.println(e.toString());
                continue;
            } catch (IOException e) {
                System.err.println(e.toString());
                continue;
            }
        }
    }

}