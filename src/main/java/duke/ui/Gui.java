package duke.ui;

import duke.Duke;
import duke.exception.DukeNoSuchInputException;

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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Gui extends Application implements Ui {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private AnchorPane mainLayout;
    private Scene scene;

    private Image userImage;
    private Image replyImage;

    private final String userImagePath = "/images/user.png";
    private final String replyImagePath = "/images/reply.png";

    private final String stylesheetPath = "/style/stylesheet.css";

    public Gui() {
        userImage = new Image(getClass().getResourceAsStream(userImagePath));
        replyImage = new Image(getClass().getResourceAsStream(replyImagePath));
    }

    @Override
    public void start(Stage stage) {
        addElements(stage);
        stage.show();
        setStyle(stage);
        addEventListeners();
        Duke.getProgram().setUi(this);
        print(Ui.WELCOME_MESSAGE + "\n");
    }

    private void addElements(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
    
        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        scene.getStylesheets().add(stylesheetPath);
    
        stage.setScene(scene);
    }

    private void setStyle(Stage stage) {
        stage.setTitle(Duke.NAME);
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(800.0);
        
        mainLayout.setPrefSize(800.0, 600.0);
        
        scrollPane.setPrefSize(785.0, 530.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        userInput.setPrefWidth(700.0);
        
        sendButton.setPrefWidth(65.0);
        
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void addEventListeners() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        dialogContainer.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
        });
    }

    @Override
    public void stop() {
        Duke.getProgram().setAsTerminated();
    }

    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        userInput.clear();
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(userImage))
        );
        Duke program = Duke.getProgram();
        program.handleUserInput(input);
        if (program.hasTerminated()) {
            end();
        }
    }

    @Override
    public void begin() {
        Application.launch(this.getClass());
    }

    @Override
    public String readInput() throws DukeNoSuchInputException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print(String message) {
        Label replyText = new Label(message);
        dialogContainer.getChildren().addAll(
            DialogBox.getReplyDialog(replyText, new ImageView(replyImage))
        );
    }

    @Override
    public void printException(Exception e) {
        print(e.getMessage());
    }

    @Override
    public void end() {
        Platform.exit();
    }
}
