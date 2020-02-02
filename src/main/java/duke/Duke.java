package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class for the entire program, Duke is the chatbot and this is the driver class.
 */
public class Duke extends Application {

    // JavaFX Supporting Variables.

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // Duke Functionality Variables.

    /**
     * TaskList to store all the Tasks for Duke.
     */
    private TaskList allTasks;

    /**
     * Storage file for reading when initialising or writing to when there are changes to the TaskList.
     */
    private Storage file;

    /**
     * Handles the User Interactions/Formalities.
     */
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.allTasks = new TaskList();
        this.ui = new Ui();
        this.file = new Storage();
    }

    /**
     * Starts the Duke application for GUI.
     * Creates the Root Node, Places this Root Node in a Scene, Sets the Stage scene, and runs the Stage show.
     *
     * @param stage the primary stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        // Additional controls to put into the main container.
        userInput = new TextField();
        sendButton = new Button("Send");

        // Putting multiple controls into one container layout pane.
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Setting the scene to be our container layout pane.
        scene = new Scene(mainLayout);

        // Setting the stage to show our screen.
        stage.setScene(scene);
        // Render the stage.
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

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
     * Main driver method.
     *
     * @param args standard argument.
     */
    public static void main(String[] args) {

        // Welcomes the user
        Ui.welcome();

        // Initialise duke.Duke program
        Duke duke = new Duke();
        duke.initialise();

        // Run main program
        duke.run();

        // User is done
        Ui.goodbye();

    }

    /**
     * Runs TaskList to serve the user.
     */
    private void run() {
        // Begins accepting input commands
        allTasks.serveUser();
    }

    /**
     * Constructs new duke.Duke, and uses past user data if any.
     */
    private void initialise() {
        // Loads previous Tasks if any
        load();
    }

    /**
     * Loads previously saved files if any.
     * Creates new file otherwise.
     */
    private void load() {
        // Take information from file to update allTasks to last saved preferences
        file.readFromLastSavedFile(allTasks);
    }

    /**
     * Prints a given String.
     *
     * @param s String to be printed.
     */
    private void print(String s) {
        System.out.println(s);
    }

}
