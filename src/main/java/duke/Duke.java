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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class for the entire program, Duke is the chatbot and this is the driver class.
 */
public class Duke<clip> extends Application {

    // GUI Members: JavaFX Support.

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Starts the Duke application for GUI.
     * Creates the Root Node, Places this Root Node in a Scene, Sets the Stage scene, and runs the Stage show.
     *
     * @param stage the primary stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {

        // Step 1. Setting up required components.
        // Scroll pane and chat box.
        scrollPane = new ScrollPane(); // whole pane that can contain scroll bar.
        dialogContainer = new VBox(); // box for chat.
        scrollPane.setContent(dialogContainer);
        // Additional controls to put into the main container.
        userInput = new TextField();
        sendButton = new Button("Send");

        // Putting the above controls into one container layout pane.
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Setting the scene to be our container layout pane.
        scene = new Scene(mainLayout);
        // Setting the stage to show our screen.
        stage.setScene(scene);
        // Render the stage.
        stage.show();

        //Step 2. Set-up Initial Window: Formatting the window to look as expected.

        // Set Window Size.
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        // Set Scene Size.
        mainLayout.setPrefSize(400.0, 600.0);
        // Screen consists of a scrollPane chat box, userInput text box, and a sendButton.
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0); // correctly position scrollPane initially.
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        // for clicking send button.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        // for pressing enter in textfield.
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(getUserResponse(userInput.getText()));
        Label dukeText = new Label(getDukeResponse(userInput.getText()));
        ImageView userImageView = new ImageView(user);
        ImageView dukeImageView = new ImageView(duke);
        userImageView.setClip(new Circle(50, 50, 50));
        dukeImageView.setClip(new Circle(50, 50, 50));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImageView),
                DialogBox.getDukeDialog(dukeText, dukeImageView)
        );
        userInput.clear();
    }

    /**
     * Generates a response for Duke to the user input.
     */
    private String getDukeResponse(String input) {
        return "Duke: " + input;
    }

    /**
     * Provides user input with user as the author.
     */
    private String getUserResponse(String input) {
        return "You: " + input;
    }

    // Below are Non-GUI members.

    // Non-GUI Variables.

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

    // Non-GUI Methods.

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.allTasks = new TaskList();
        this.ui = new Ui();
        this.file = new Storage();
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
