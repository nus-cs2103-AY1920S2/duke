package duke.javafx;

import duke.duke.Duke;
import duke.exception.DukeException;
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

// Main class to run javaFX.
// Workaround is to create a new wrapper class to run this

public class DukeJavaFxRunner extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private MainWindow mainWindow;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Trump.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Putin.jpg"));

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        primaryStage.setScene(scene);
        primaryStage.show();

        // To format the window (Lines 55-82). Tutorial 2

        primaryStage.setTitle("Trump vs Putin");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 800.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(100.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);



        //Tutorial 3: To print whatever you type
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1000000));

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                mainWindow.handleUserInput();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                mainWindow.handleUserInput();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    /*
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    //    private void handleUserInput() {
    //        Label userText = new Label(userInput.getText());
    //        Label dukeText = new Label(getResponse(userInput.getText()));
    //        dialogContainer.getChildren().addAll(
    //                DialogBox.getUserDialog(userText.toString(), (user)),
    //                DialogBox.getDukeDialog(dukeText.toString(), (duke))
    //        );
    //        userInput.clear();
    //    }

    // For crying out loud, please change this to
    // Duke heard
    public String getResponse(String input) {
        return "Hello, this is what you have added or requested \n " + input;
    }


}
