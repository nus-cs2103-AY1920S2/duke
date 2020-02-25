import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Represents Duke, the task tracking smart bot
 *
 * @author Goh Boon Hee Shaun
 * @version 0.1
 * <p>
 * Command input formats:
 * list
 * clear</space> list
 * done</space></taskNumber>
 * delete</space></taskNumber>
 * find</space></taskNumber>
 * todo</space></name of task>
 * deadline</space></name of task></backslash></Date in yyyy-mm-dd format>
 * event</space></name of task></backslash></Date in yyyy-mm-dd format><T></Time in hh:mm-hh:mm format>
 */
public class Duke extends Application {

    /**
     * To take in user input
     */
    public static Scanner sc = new Scanner (System.in);
    /**
     * Keeps of track of saved files
     */
    private Storage storage;
    /**
     * A list to store tasks
     */
    private TaskList tasks;
    /**
     * In charge of the interface the user sees
     */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Button exitButton;
    private Scene scene;

    /**
     * Creates a Duke bot
     *
     * @param filePath where to save and load files when Duke closes
     */
    public Duke (String filePath) {
        storage = new Storage (filePath);
        try {
            tasks = new TaskList (storage.loadFiles());
        } catch (Exception e) {
            tasks = new TaskList (new ArrayList<Task>());
        }
    }

    /**
     * Empty Duke Constructor
     */
    public Duke() {

    }

    /**
     * Starts up the duke application
     *
     * @param stage Stage layout of duke application
     */
    @Override
    public void start (Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent (dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        exitButton = new Button("exit");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll (scrollPane, userInput, sendButton,exitButton);

        scene = new Scene (mainLayout);

        stage.setScene (scene);
        stage.show();

        stage.setTitle ("Polar Express");
        stage.setResizable (false);
        stage.setMinHeight (600.0);
        stage.setMinWidth (400.0);

        mainLayout.setPrefSize (400.0, 600.0);

        scrollPane.setPrefSize (385, 535);
        scrollPane.setHbarPolicy (ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy (ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue (1.0);
        scrollPane.setFitToWidth (true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight (Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth (325.0);

        sendButton.setPrefWidth (55.0);

        AnchorPane.setTopAnchor (scrollPane, 1.0);

        AnchorPane.setBottomAnchor (sendButton, 1.0);
        AnchorPane.setRightAnchor (sendButton, 1.0);

        AnchorPane.setLeftAnchor (userInput, 1.0);
        AnchorPane.setBottomAnchor (userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked ( (event) -> {
            dialogContainer.getChildren().add(getDialogLabel (userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction ( (event) -> {
            dialogContainer.getChildren().add (getDialogLabel (userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener ( (observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel (String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label (text);
        textToAdd.setWrapText (true);

        return textToAdd;
    }

    /**
     * Returns the TaskList attribute, which contains list of task, from Duke object
     *
     * @return a TaskList object
     */
    public TaskList getTaskList() {
        return tasks;
    }

    /**
     * Saves the data to a text file when application closes
     *
     * @throws IOException When unable to find location to save into
     */

    public void save() throws IOException {
        storage.saveFiles (tasks);
    }

    /**
     * Loads saved data from text file into application
     *
     * @throws IOException if unable to find location to load from
     */
    public void load() throws IOException {
            tasks = new TaskList(storage.loadFiles());
    }
}