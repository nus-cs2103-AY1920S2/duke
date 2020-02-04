package duke;

import java.io.IOException;

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

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/theshoebill.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/thecathulhu.jpg"));

    public Duke() {
        ui = new Ui();
        try{
            storage = new Storage();
            this.tasks = storage.loadTasksFile();
        } catch (Exception e) {
            ui.printError(e);
        }
    }


    private void interact() {

        boolean byebye = false;
        ui.showWelcome();

        while (!byebye) {
            ui.printLine1();
            try {
                byebye = Parser.parse(tasks, ui);
                storage.writeTasksFile(tasks);
            } catch (DukeException e) {
                ui.printError(e);
            } catch (IOException e) {
                ui.printError(e);
                break;
            }
            ui.printLine2();
        }
        ui.showGoodbye();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

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
            handleUserInput();
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
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new Duke().interact();
    }
}
