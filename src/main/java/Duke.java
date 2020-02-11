import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Grapie.Commands.CommandTypes;
import Grapie.Exceptions.GrapieExceptions;
import Grapie.Functions.Parser;
import Grapie.Functions.Storage;
import Grapie.Commands.TaskList;
import Grapie.Functions.Ui;

import Grapie.Tasks.Task;
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
        try {
            CommandTypes.Commands typeResult = parser.parseCommand(command);

            String result;

            switch (typeResult) {
                case LIST:
                    String list = tasks.listTheList();
                    return list;

                case DONE:
                    result = tasks.completeTask(command);
                    return result;

                case ADD:
                    result = tasks.addToList(command);
                    return result;

                case DELETE:
                    result = tasks.deleteTask(command);
                    return result;

                case FIND:
                    result = tasks.findFromList(command);
                    return result;

                case TAG:
                    result = tasks.tagTask(command);
                    return result;

                default:
                    System.out.println("ERRORRR");
                    break;
            }

        } catch (GrapieExceptions grapieExceptions) {
            return grapieExceptions.toString();
        }


        return "OOPS!!! I do not understand you :(";
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
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

        return result;
    }
}
