package dukeproj;

import dukeproj.gui.DialogBox;
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

/**
 * Represents the User Interface in DukeProject.
 */
public class Ui extends Application {
    private String logo;
    private String lineBreak;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing dukeproj.Duke's reply and appends them to
     * dialog container. Clears user input upon processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        userText.setWrapText(true);
        dukeText.setWrapText(true);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Prints out the introductory message for DukeProject.
     */
    public void getIntroduction() {
        Label dukeText = new Label(lineBreak + "\nHello I am \n" + logo
                + "\n What can I do for you?\n" + lineBreak);

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        /*System.out.println(lineBreak);
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");
        System.out.println(lineBreak);*/
    }

    /**
     * Prints out the exit message for DukeProject.
     */
    public void exit() {
        Label dukeText = new Label(lineBreak + "\nBye! Hope to see you again soon!\n" + lineBreak);

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        /*System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);*/
    }

    /**
     * Prints out a lineBreak.
     */
    public void printLineBreak() {
        System.out.println(lineBreak);
    }

    /**
     * Creates the User Interface with pre-defined logo and linebreak.
     */
    public Ui() {
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        lineBreak = "_____________________________" +
                "_______________________________";

    }
}
