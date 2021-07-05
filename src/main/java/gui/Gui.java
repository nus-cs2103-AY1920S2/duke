package gui;

import sample.Main;
import common.Messages;
import data.exceptions.StorageOperationException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {

    private Image userPng = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukePng = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = setupAnchorPane(stage);
        formatWindow(stage, mainLayout);
        parseUserInput();
        //isExit(stage, "aa");
    }

    /**
     * setup anchorPane with parameters
     * @param stage stage
     */
    private AnchorPane setupAnchorPane(Stage stage) {
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
        return mainLayout;
    }

    /**
     * setup Window with parameters
     * @param stage stage
     * @param mainLayout uninitialized anchorPane
     */
    private void formatWindow(Stage stage, AnchorPane mainLayout) {
        //Step 2. Formatting the window to look as expected
        setStage(stage);
        mainLayout.setPrefSize(400.0, 600.0);
        setScrollPane();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        setAnchorPane();
    }

    /** Step 3. Add functionality to handle user input. */
    private void parseUserInput() {
        //click button
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (StorageOperationException e) {
                System.out.println(e);
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (StorageOperationException e) {
                System.out.println(e);
            }
        });
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing TaskManager's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws StorageOperationException {
        //user box
        Label userText = new Label(userInput.getText());
        //taskManager response
        //construct a label with the response
        Label dukeText = new Label(new Main().getResponseForGUI(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userPng)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukePng))
        );
        userInput.clear();
    }

    private void isExit(Stage stage, String outputToUser){
        if (outputToUser.equals(Messages.MESSAGE_FAREWELL)){
            stage.close();
        }
    }

    /** setup AnchorPane with parameters*/
    private void setAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /** setup ScrollPane with parameters*/
    private void setScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * setup Stage with parameters
     * @param stage uninitialized stage
     */
    private void setStage(Stage stage) {
        stage.setTitle("TaskManager");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }
}
