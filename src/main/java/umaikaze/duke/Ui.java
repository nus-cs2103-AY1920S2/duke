/**
 * Main Ui which the user interact with, the Application launched by Launcher
 */

package umaikaze.duke;

import javafx.animation.PauseTransition;
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
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeParseException;

public class Ui extends Application {
    private Stage mainStage;
    private Duke duke;
    private Scene scene;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image systemImage = new Image(this.getClass().getResourceAsStream("/images/DaSystem.png"));
    private Image meowImage = new Image(this.getClass().getResourceAsStream("/images/DaMeow.png"));
    PrintStream out;

    @Override
    public void start(Stage stage) {
        mainStage = stage;

        setUiElements();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        setUiFormatting(stage, mainLayout);

        try {
            duke = new Duke();
            if (duke.tl.list.size() == 0) {
                showReply(Message.GREETING_EMPTY_SAVE);
            } else {
                showReply(Message.GREETING_LOADED);
            }
        } catch (IOException | DukeException e) {
            showError(e.getMessage());
        }
        out.print("Duke class initialized " + duke);
        setAction();
    }

    /**
     * While instantiating the Ui, header is printed automatically
     */
    public Ui() throws UnsupportedEncodingException {
        out = new PrintStream(System.out, true, "UTF-8");
    }

    /**
     * Prints given String to console
     * @param line Only supports single line, when there are multiple lines, add your own \n\t at the end of every
     *             line (Except the last line)
     */
    public void showReply(String line) {
        if (line != null && !line.equals("")) {
            Label dukeText = new Label(line);
            dialogContainer.getChildren().add(new DialogBox(dukeText, new ImageView(dukeImage)));
        }
    }

    /**
     * Same as showReply but different divider
     */
    public void showError(String error) {
        Label errorLabel = getDialogLabel(error);
        errorLabel.setStyle("-fx-color: red");
        dialogContainer.getChildren().add(new DialogBox(errorLabel, new ImageView(systemImage)));
    }

    void setUiElements() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
    }

    void setUiFormatting(Stage stage, AnchorPane mainLayout) {
        stage.setTitle("Cat Person");
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    void setAction() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    void handleUserInput() {
        String userString = userInput.getText();
        if (userString.equals("")) {
            return;
        }

        Label userText = new Label(userString);
        dialogContainer.getChildren().add(new DialogBox(userText, new ImageView(userImage)));

        String reply = "";
        try {
            if (userString.toLowerCase().contains("no you don't") || userString.toLowerCase().contains("no you dont")) {
                popEasterEgg();
                return;
            }
            if (userString.toLowerCase().equals("bye")) {
                hideUi();
                return;
            }

            reply = duke.getResponse(userString);
        }  catch (DukeException e) {
            System.out.println("Duke exception caught in handleUserInput");
            showError(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("DateTimeParseException caught in handleUserInput");
            showError(e.getMessage() + "\n" + Message.EXCEPTION_BAD_TIME_FORMAT);
        } catch (IOException e) {
            System.out.println("IOException caught in handleUserInput");
            showError(Message.EXCEPTION_UNABLE_TO_SAVE_LOAD + "\n" + e.getMessage());
        }
        showReply(reply);

        userInput.clear();
    }

    private void popEasterEgg() {
        dialogContainer.getChildren().add(new DialogBox(
                new Label(Message.BONUS), new ImageView(meowImage)));
        userInput.clear();
    }

    private void hideUi() {
        showReply(Message.GREETING_GOODBYE);
        PauseTransition delay = new PauseTransition(Duration.millis(1000));
        delay.setOnFinished( event -> mainStage.close() );
        delay.play();
    }
}
