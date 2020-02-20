package duke.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.time.format.DateTimeParseException;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.UnableToLoadException;

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    private Stage stage;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initialises the classes need for Duke.
     * They include UI, Storage, Parser
     * @throws UnableToLoadException If cannot load from storage.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(System.getProperty("user.dir"));
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadFromSave());
        } catch (UnableToLoadException e) {
            ui.showDukeError(e);
        }
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        
        sendButton.setDefaultButton(true);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        
        stage.setTitle("CrankyNoteMaster");
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Label(ui.sayHi()), 
            new ImageView(duke)), new Label(Constant.LINE));

    }

    /**
     * Creates a label with the specified text and adds it to the
     * dialog container.
     * 
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    
    private void handleUserInput() {
        Label userText = new Label("\nUser: " + userInput.getText());
        
        String response;
        String input = userInput.getText();
        Command command = parser.parse(input);

        try {    
            response = command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = ui.showDukeError(e);
        } catch (DateTimeParseException e) {
            response = ui.showDateTimeError();
        }
        
        Label dukeText = new Label(getResponse(response));
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, 
            new ImageView(user)), new Label(Constant.LINE),
            DialogBox.getDukeDialog(dukeText, new ImageView(duke)), new Label(Constant.LINE));
        userInput.clear();

        Boolean isExit = command.isExit();
        if (isExit) {
            stage.close();
        }
    }

    private String getResponse(String input) {
        return "\nDuke: " + input; 
    }
}
