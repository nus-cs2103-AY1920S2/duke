import java.io.IOException;

import Grapie.Exceptions.GrapieExceptions;
import Grapie.Storage;
import Grapie.TaskList;
import Grapie.Ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javafx.scene.image.Image;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
     * @throws IOException Throws away the exception.
     * @throws GrapieExceptions Throws Grapie.Exceptions.GrapieExceptions.
     */
    public Duke() throws IOException, GrapieExceptions {
        String filePath = "data/dukeStorage.txt";
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(), storage);
        ui = new Ui();
    }

    /**
     * Main method for Duke, to run the program.
     *
     * @throws IOException Throws away exception.
     */
    public String runDuke(String input) throws IOException, GrapieExceptions {
        //loop
        if (!input.equals("bye")) { //check for ending input
            String result = ui.readCommand(input, tasks);
            return result;
            //nextStr = sc.nextLine();
        } else {
            return sayonara();
        }
    }


    /**
     * Greet the user.
     */
    public String greetings() {
        String intro = "Hello! I'm Grapie! \n"
                + "   _____                 _      \n"
                + "  / ____|               (_)     \n"
                + " | |  __ _ __ __ _ _ __  _  ___ \n"
                + " | | |_ | '__/ _` | '_ \\| |/ _ \\ \n"
                + " | |__| | | | (_| | |_) | |  __/ \n"
                + "  \\_____|_|  \\__,_| .__/|_|\\___| \n"
                + "                  | |           \n"
                + "                  |_|           \n"


                + "What do ya need from me?\n";

        return intro;
    }

    /**
     * Returns goodbye to the user.
     */
    public String sayonara() {
        return "Okie!! Goodbye!";
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
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
            result = ui.readCommand(input, tasks);
            //nextStr = sc.nextLine();
        } else {
            result = sayonara();
        }

        return result;
    }
}
