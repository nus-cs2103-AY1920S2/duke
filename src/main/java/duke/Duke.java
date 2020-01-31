package duke;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

import duke.ui.Ui;
import duke.ui.Gui;
import duke.ui.TextUi;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.storage.TextStorage;
import duke.commands.CommandHandler;
import duke.exceptions.DukeException;

// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.control.Label;
// import javafx.scene.control.Button;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.AnchorPane;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.VBox;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.stage.Stage;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new TextUi();
        String filePath = "data/tasks.txt";
        Storage storage = new TextStorage(filePath);
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        ui.showGreeting();
        try {
            tasks.add(storage.load());
            ui.showReply("Save file loaded!");
        } catch (FileNotFoundException e) {
            ui.showError("Save file not found!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        CommandHandler handler = new CommandHandler(tasks, ui);
        while (handler.isActive()) {
            handler.executeCmd(sc.nextLine());
        }
        try {
            storage.save(tasks.getAllTasks());
            ui.showReply("Save Success! See you next time!");
        } catch (IOException e) {
            ui.showError("Save Failure :-(. Try again next time!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        sc.close();
    }

    // private ScrollPane scrollPane;
    // private VBox dialogContainer;
    // private TextField userInput;
    // private Button sendButton;
    // private Scene scene;
    // private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    // private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    // // @Override
    // // public void start(Stage stage) {
    // //     // Step 1. Setting up required components

    // //     // The container for the content of the chat to scroll.
    // //     scrollPane = new ScrollPane();
    // //     dialogContainer = new VBox();
    // //     scrollPane.setContent(dialogContainer);

    // //     userInput = new TextField();
    // //     sendButton = new Button("Send");

    // //     AnchorPane mainLayout = new AnchorPane();
    // //     mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

    // //     scene = new Scene(mainLayout);
    // //     stage.setScene(scene);
    // //     stage.show();

    // //     // Step 2. Formatting the window to look as expected
    // //     stage.setTitle("Duke");
    // //     stage.setResizable(false);
    // //     stage.setMinHeight(600.0);
    // //     stage.setMinWidth(400.0);

    // //     mainLayout.setPrefSize(400.0, 600.0);

    // //     scrollPane.setPrefSize(385, 535);
    // //     scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    // //     scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    // //     scrollPane.setVvalue(1.0);
    // //     scrollPane.setFitToWidth(true);

    // //     // You will need to import `javafx.scene.layout.Region` for this.
    // //     dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

    // //     userInput.setPrefWidth(325.0);

    // //     sendButton.setPrefWidth(55.0);

    // //     AnchorPane.setTopAnchor(scrollPane, 1.0);

    // //     AnchorPane.setBottomAnchor(sendButton, 1.0);
    // //     AnchorPane.setRightAnchor(sendButton, 1.0);

    // //     AnchorPane.setLeftAnchor(userInput, 1.0);
    // //     AnchorPane.setBottomAnchor(userInput, 1.0);

    // //     // Scroll down to the end every time dialogContainer's height changes.
    // //     dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    // //     // Part 3. Add functionality to handle user input.
    // //     sendButton.setOnMouseClicked((event) -> {
    // //         handleUserInput();
    // //     });

    // //     userInput.setOnAction((event) -> {
    // //         handleUserInput();
    // //     });
    // // }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}