import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import java.util.ArrayList;

/*
 * Ui
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 11 February 2020
 *
 */

/**
 * The Ui class handles the creation of the User Interface
 * for the duke chat platform.
 * @author Daniel Alfred Widjaja
 */
public class Ui {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private ArrayList<Task> listing;
    private Stage stage;
    private Parser parser;
    private Handler handler;

    private Image user;
    private Image duke;


    /**
     * Initiation for Ui class.
     * @param stage The stage to load the UI in.
     * @param parser The parser to load the database.
     * @param handler The class to handle the duke response.
     */
    public Ui(Stage stage, Parser parser, Handler handler) {
        this.stage = stage;
        this.parser = parser;
        this.handler = handler;
        this.listing = parser.getDatabase();
    }

    /**
     * Create the User Interface layout.
     */
    public void start() {

        listing = parser.getDatabase();

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

        String introText = String.join("\n",
            "Hello! I'm Duke",
            "What can I do for you?",
            "Here's a list of command examples you can use:",
            "1. todo read book 2",
            "2. deadline finish book /by 2020-02-30 5",
            "3. event book meeting /at 2020-03-02 1",
            "4. list",
            "5. done 2",
            "6. delete 2",
            "7. bye");

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(getDialogLabel(introText), new ImageView(duke)));

        //step 3
        sendButton.setOnMouseClicked((event) -> {
            if (handleUserInput() == 1) {
                stage.close();
            }
        });

        userInput.setOnAction((event) -> {
            if (handleUserInput() == 1) {
                stage.close();
            }
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input and write the response in the layout
     * @return the response status,
     * 0 if UI doesn't need to exit,
     * 1 if UI need to exit.
     */
    private int handleUserInput() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().add( new DialogBox(userText, new ImageView(user)));
        String userTxt = userInput.getText();
        userInput.clear();

        DukeResponse resp = handler.getResponse(userTxt, listing);

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(getDialogLabel(resp.getResponse()), new ImageView(duke)));
        return resp.getStatus();
    }

    /**
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
}
