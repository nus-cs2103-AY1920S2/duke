package duke;// packages imports
import duke.tasks.TaskList;
import duke.ui.Ui;

// java imports
import java.io.IOException;
import java.io.FileNotFoundException;

// javafx imports
//import javafx.application.Application;
//
//import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.layout.Region;


/**
 * Main class of the chat bot program.
 */
public class Duke {
    /**
     * User interface class with formatted outputs.
     */
    private Ui ui;
    /**
     * Allows for persistent data.
     */
    private Storage storage;
    /**
     * List to store all tasks.
     */
    private TaskList taskList;

    /**
     * Creates a bot with personalize user interface, storage, and task list.
     * Will create a new save file is there is no existing one.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        try {
            storage.readSaveFile(taskList);
        } catch (FileNotFoundException ex) {
            ui.printFormattedOutput("No saved task list found. Creating a new one...");
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
           return "Bye. Hope to see you again soon!";
        }
        try {
            Parser parser = new Parser(taskList, storage, ui);
            String response = parser.parseToString(input);
            storage.save(taskList);
            return "Duke response: "+ response;
        } catch (IOException ex) {
            return ex.toString();
        }

    }
}


//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 10.0);
//
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        // Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//        // more code to be added here later
//    }

/**
 * Iteration 1:
 * Creates a label with the specified text and adds it to the dialog container.
 * @param text String containing text to add
 * @return a label with the specified text that has word wrap enabled.
 */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

/**
 * Iteration 2:
 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
 * the dialog container. Clears the user input after processing.
 */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
