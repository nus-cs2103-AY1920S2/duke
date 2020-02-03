import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke { //extends Application {
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
     * @throws GrapieExceptions Throws GrapieExceptions.
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
    public void runDuke() throws IOException {
        greetings();

        //Scanner class for input
        Scanner sc = new Scanner(System.in);
        String nextStr = sc.nextLine();

        //loop
        while (!nextStr.equals("bye")) { //check for ending input
            ui.readCommand(nextStr, tasks);
            nextStr = sc.nextLine();
        }

        sayonara();
    }


    /**
     * Greet the user.
     */
    public void greetings() {
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

        TaskList.formattingDivider(intro);
    }

    /**
     * Returns goodbye to the user.
     */
    public void sayonara() {
        TaskList.formattingDivider("Okie!! Goodbye!");
    }

    /**
     * The main method.
     *
     * @param args The main method.
     * @throws IOException Throws away exception.
     */
    public static void main(String[] args) throws IOException {
        Duke duke = null;
        try {
            duke = new Duke();
        } catch (GrapieExceptions grapieExceptions) {
            System.out.println("    #__________________________________________________________# \n");
            System.out.println("      " + grapieExceptions);
            System.out.println("    #__________________________________________________________#");
        }
        duke.runDuke();
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
    String getResponse(String input) {
        //input is the user input

        return "Duke heard: " + input;
    }
}
