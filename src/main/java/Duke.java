import duke.pack.Command;
import duke.pack.DukeException;
import duke.pack.Parser;
import duke.pack.Storage;
import duke.pack.TaskList;
import duke.pack.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Represents the chatbot
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private ImageView image;
    private Label label;

    public Duke() {
    }

    public Duke(String filePath) {
        // following code from module website

        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        /*
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.setTextFill(Color.web("#ffdbef")); // change font colour
        helloWorld.setFont(new Font("Arial", 20)); // change font and size
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
        */

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        //Label helloWorld = new Label("Hello World!");
        //dialogContainer.getChildren().addAll(helloWorld);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

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

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Starts to run the bot
     */
    public void run() {
        // following code from module website

        ui.greet();

        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.receiveInput();
                ui.showLine();
                Command comm = parser.parseCommand(command);
                comm.execute(tasks, ui, storage);
                isExit = comm.isExit();

            } catch (DukeException e) {
                ui.showError(e);

            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }

}
