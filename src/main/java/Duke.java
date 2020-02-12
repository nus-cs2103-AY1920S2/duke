import javafx.application.Application;
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
 * done</space></taskNumber>
 * delete</space></taskNumber>
 * find</space></taskNumber>
 * todo</space></name of task>
 * deadline</space></name of task></backslash></Date in yyyy-mm-dd format>
 * event</space></name of task></backslash></Date in yyyy-mm-dd format><T></Time in mm:ss-mm:ss format>
 * bye
 */
public class Duke extends Application {

    /**
     * Keeps of number of task left undone
     */
    public static int pendingTask = 0;
    /**
     * To take in user input
     */
    public static Scanner sc = new Scanner(System.in);
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
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a Duke bot
     *
     * @param filePath where to save and load files when Duke closes
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFiles());
        } catch (Exception e) {
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Empty Duke Constructor
     */
    public Duke() {

    }

    public static void main(String[] args) throws IOException {
        new Duke("src/main/java/data/duke.txt").run();
    }

    /**
     * Runs the Duke bot's processes
     *
     * @throws IOException if buffer reads a NULL input
     */
    public void run() throws IOException {
        ui.printOpeningScreen();
        Parser parser = new Parser(tasks);
        String input = "";
        while (!(input = sc.nextLine()).equals("bye")) {
            ui.printBreak();
            parser.parse(input);
            ui.printBreak();
        }
        storage.saveFiles(tasks);
        ui.closeScreen();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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

        AnchorPane.setLeftAnchor(userInput, 1.0);
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getUserDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke replies: " + input;
    }

    public TaskList getTaskList() {
        return tasks;
    }

    public void save() throws IOException {
        storage.saveFiles(tasks);
    }

    public void load() throws IOException {
        tasks = new TaskList(storage.loadFiles());
    }
}