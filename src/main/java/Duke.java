package main.java;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;


public class Duke extends Application {

    /**
     * Constructor
     *
     */
    public Duke() {}

    /**
     * Declaration of variables
     */
    private Command command = new Command();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Image user = new Image(this.getClass().getResourceAsStream("/main/resources/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/main/resources/images/DaDuke.jpg"));

    @Override
    public void start(Stage stage) throws IOException {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");
        Button startButton = new Button("Start");


        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, startButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
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



        userInput.setPrefWidth(275.0);


        sendButton.setPrefWidth(55.0);

        startButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setBottomAnchor(startButton, 1.0);

        AnchorPane.setRightAnchor(startButton, 65.0);


        AnchorPane.setLeftAnchor(userInput, 2.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        // create a input stream
        FileInputStream input = new FileInputStream("marble.jpg");

        // create a image
        Image image = new Image(input);

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);

        // set background
        dialogContainer.setBackground(background);



        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        startButton.setOnMouseClicked((event) -> {
            //handleUserInput();
            command.loadList();
            showWelcomeMessage();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
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
     *
     * Creates a label with a welcome message and adds it to the dialog container
     *
     * */
    private void showWelcomeMessage() {
        Label dukeText = null;
        try {
            dukeText = new Label("Hello I am Duke. What can I do for you?");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */


    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = null;
        try {
            if(userInput.getText().equals("bye")) {
                command.getResponse("bye");
                dukeText = new Label("bye see you soon");
                dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
                );
                System.exit(0);
            } else {
                dukeText = new Label(command.getResponse(userInput.getText()));
            }
        } catch (DukeException e) {
            e.printStackTrace();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

}

