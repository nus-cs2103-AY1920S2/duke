import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * CS2103T AY 19/20 S2
 * Individual Project
 * @author Yap Dian Hao
 * @since 2020-01-30
 * <p>
 * Duke is a personal chatbot assistant to help you organize your tasks. Especially helpful
 * for people who love to procrastinate. Or procrastinate to procrastinate.
 * </p>
 */

public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/cuteuser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/cutebot!.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String filepath;



    /**
     * Constructor of the Duke chatbot assistant.
     * Duke is able to perform CRUD operations (Create, Read, Update, Destroy) to manage tasks.
     * @param filePath A text file which functions as a database to store all tasks.
     */

    public Duke() {
        this.filepath = "data/duke.txt";
        this.ui = new Ui();
        this.storage = new Storage(this.filepath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage stage) {
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

        //step 2
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

        //step3
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return run(input);
    }

    /**
     * Starts the Duke bot by receiving user input, and stores all the data to the database
     * upon shutting down.
     */
    public String run(String input) {
        String toReturn = ui.start(this.taskList, input);
        storage.write(this.taskList.getAllTasks());
        return toReturn;
    }

    /**
     * The main driver function of Duke.
     * @param args The command line arguments entered by the user.
     */
    public static void main(String[] args) {
        //new Duke("data/duke.txt").run();
    }
}