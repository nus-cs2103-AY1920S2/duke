import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import grapie.commands.CommandTypes;
import grapie.exceptions.GrapieExceptions;
import grapie.functions.Parser;
import grapie.functions.Storage;
import grapie.commands.TaskList;
import grapie.functions.Ui;

import grapie.tasks.Task;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private List<Task> storingList;

    //Layout
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Main constructor for Duke.
     *
     * @throws IOException      Throws away the exception.
     * @throws GrapieExceptions Throws Grapie.Exceptions.GrapieExceptions.
     */
    public Duke() throws IOException, GrapieExceptions {
        String filePath = "./data/dukeStorage.txt";
        storingList = new ArrayList<Task>();
        storage = new Storage(filePath, storingList);
        storage.load();
        tasks = new TaskList(storingList, storage);
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Main method for Duke, to run the program.
     *
     * @throws IOException Throws away exception.
     */
    public String runDuke(String command) throws IOException, GrapieExceptions {
        String result = "";

        try {
            CommandTypes.Commands typeResult = parser.parseCommand(command);

            switch (typeResult) {
                case LIST:
                    result = tasks.listTheList();
                    break;
                case DONE:
                    result = tasks.completeTask(command);
                    break;
                case ADD:
                    result = tasks.addToList(command);
                    break;
                case DELETE:
                    result = tasks.deleteTask(command);
                    break;
                case FIND:
                    result = tasks.findFromList(command);
                    break;
                case TAG:
                    result = tasks.tagTask(command);
                    break;
                default:
                    System.out.println("ERRORRR");
                    break;
            }
        } catch (GrapieExceptions grapieExceptions) {
            return grapieExceptions.toString();
        }

        return result;
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws IOException {
        //input is the user input
        String result = "";
        if (!input.equals("bye")) { //check for ending input
            try {
                result = runDuke(input);
            } catch (GrapieExceptions grapieExceptions) {
                return grapieExceptions.toString();
            }
        } else {
            result = ui.sayonara();
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }

        return "Please don't eat me... \n" + result;
    }
}
