package dukeproj;

import dukeproj.command.Command;
import dukeproj.command.ListCommand;
import dukeproj.enums.SayType;
import dukeproj.exception.InvalidCommandException;
import dukeproj.gui.DialogBox;
import javafx.application.Application;
import javafx.application.Platform;
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

import java.io.File;

public class GuiApp extends Application {

    private Duke duke;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userIm = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeIm = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        // Set up components
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

        // Format window
        stage.setTitle("I AM DUKE");
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

        // Add functionality to handle user input.
        sendButton.setOnMouseClicked((event -> {
            handleUserInput();
        }));
        userInput.setOnAction((event -> {
            handleUserInput();
        }));
        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable -> {
            scrollPane.setVvalue(1.0);
        }));

        dukeSay(duke.getUi().say(SayType.INTRO));
    }

    public void dukeSay(String str) {
        Label text = new Label(str);

        text.setWrapText(true);

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(text, new ImageView(dukeIm)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and appends them to
     * dialog container. Clears user input upon processing.
     * The method will also check whether the command given by user is an exit command and responses appropriately.
     */
    private void handleUserInput() {
        String dukeResponse = "";
        Command command = new ListCommand(); //default command
        try {
            command = duke.getCommandResponse(userInput.getText());
            dukeResponse = duke.getResponse(command);
        } catch (InvalidCommandException e) {
            dukeResponse = duke.getUi().say(SayType.INVALID_COMMAND);
        }

        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(dukeResponse);

        userText.setWrapText(true);
        dukeText.setWrapText(true);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userIm)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeIm))
        );

        if (command.isExit()) {
            closeApp();
        }
        userInput.clear();
    }

    private void closeApp() {
        try {
            Platform.exit();
        } catch (Exception e) {
            dukeSay("Oh dear! I cant seem to close, please alt+F4");
        }
    }

    public GuiApp() {
        duke = new Duke("." + File.separator + "data" + File.separator + "Task.txt", true);
    }

}
