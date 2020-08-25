package duke;

import duke.command.Command;
import duke.gui.components.DialogBox;
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

import java.util.Optional;
import java.util.Scanner;

/**
 * The Duke program.
 */
public class Duke extends Application {
    private Controller controller;

    private Image duke = new Image(this.getClass().getResourceAsStream("/images/andrew.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/ricky.png"));

    /**
     * Constructs a Duke instance with file path set to src/data/data.csv. This is primarily for the sake of GUI.
     */
    public Duke() {
        this("data/data.csv");


    }

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath a String value of the file path.
     */
    public Duke(String filePath) {
        Storage storageController = new Storage(filePath);
        this.controller = new Controller(storageController);
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }

    /**
     * A driver method for the program Duke.
     */
    private void run() {
        Scanner scan = new Scanner(System.in);
        Ui.printGreet();
        Ui.printTaskList();
        while (scan.hasNext()) {
            try {
                Optional<Command> parsed = Parser.parse(scan.nextLine());
                if (parsed.isPresent()) {
                    Command command = parsed.get();
                    if (controller.execute(command)) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.exit(0);

    }

    /**
     * Starts rendering process by JavaFX.
     *
     * @param stage a Stage object for JavaFX
     */
    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(625.0);

        mainLayout.setPrefSize(625.0, 535.0);

        scrollPane.setPrefSize(610, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(523.0);

        sendButton.setPrefWidth(85.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        String initialLine = "";
        Ui.printGreet();
        initialLine += Ui.getContent() + "\n";
        Ui.printTaskList();
        initialLine += Ui.getContent();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(initialLine), new ImageView(duke))
        );

        sendButton.setOnMouseClicked((event) -> handleUserInput(userInput, dialogContainer));

        userInput.setOnAction((event) -> handleUserInput(userInput, dialogContainer));

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Handles user input.
     *
     * @param userInput       a TextField object containing a String user input.
     * @param dialogContainer a VBox object that will contain the dialogue boxes for Duke and the user characters.
     */
    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        if (controller.getStatus()) {
            try {
                System.exit(0);
            } catch (Exception e) {
                Controller.raiseException(e);
            }
        }
    }

    /**
     * Returns a response for a text user input in the text field.
     *
     * @param text a String-form user input
     * @return a String response for the given user input
     */
    private String getResponse(String text) {
        try {
            Optional<Command> parsed = Parser.parse(text);
            parsed.ifPresent(command -> controller.execute(command));
        } catch (Exception e) {
            return e.getMessage();
        }
        return Ui.getContent();
    }
}
