package duke.gui;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.command.Command;
import duke.command.DummyCommand;

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

    private Duke duke;
    private String saveFolder;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;

    private Image userImg = new Image(this.getClass()
            .getResourceAsStream("/images/DaUser.png"));
    private Image dukeImg = new Image(this.getClass()
            .getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void init() throws Exception {
        super.init();

        saveFolder = "data";
        duke = new Duke(saveFolder);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other 
     * containing Duke's reply and then appends them to the dialog container. 
     * Clears the user input after processing.
     * @param response The response of duke
     */
    private void createDialogBoxes(String response) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(response);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImg)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImg))
        );
        userInput.clear();
    }

    /**
     * Obtains duke's response to the user input.
     */
    private void handleUserInput() {
        Command command = new DummyCommand();
        String response;

        try {
            command = Parser.parse(userInput.getText());
            response = duke.getResponse(command);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        createDialogBoxes(response);

        if (command.isExit()) {
            exit();
        }
    }

    /**
     * Exits the application after 1 second so that duke's exit response will
     * be shown.
     */
    private void exit() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }).start();
    }

    /**
     * Creates instances of components.
     */
    private void createContainer() {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
    }

    /**
     * Configures the settings of stage.
     */
    private void configureStage(Stage stage) {
        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    /**
     * Configures the size of components.
     */
    private void configureDisplay() {
        mainLayout.setPrefSize(400.0, 600.0);
        
        scrollPane.setPrefSize(400, 545);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this. 
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        userInput.setPrefWidth(325.0);
        
        sendButton.setPrefWidth(74.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Configures the position of components.
     */
    private void configureAnchorPosition() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Configures the event triggered by user interaction.
     */
    private void configureEvent() {
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    @Override
    public void start(Stage stage) {
        createContainer();
        configureStage(stage);
        configureDisplay();
        configureAnchorPosition();
        configureEvent();
    }
}