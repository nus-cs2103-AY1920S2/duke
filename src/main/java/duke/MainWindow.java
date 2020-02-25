package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Parser parser;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private String filePath = "data/duke.txt";

    /**
     * Loads tasks from file and greets the user.
     *
     * @throws IOException When the file to store the tasks is not found.
     */
    @FXML
    public void initialize() throws IOException {
        //@@author JeffryLum-reused
        //Reused from https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        ui = new Ui();
        storage = new Storage(filePath);

        //Load file
        try {
            taskList = new TaskList(storage.load(), ui);
        } catch (DukeException e) {
            String err = DukeStub.getResponse(ui.showInvalidFile());
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(err, duke)
            );
            taskList = new TaskList(ui);
        }

        //Greets the user
        String greet = DukeStub.getResponse(ui.greet());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greet, duke)
        );
    }

    /**
     * Gets the user input and prints the responses.
     *
     * @throws IOException When the file to store the tasks is unavailable.
     */
    @FXML
    private void handleUserInput() throws IOException {
        //@@author JeffryLum-reused
        //Reused from https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md
        // with minor modifications
        String input = userInput.getText();
        parser = new Parser(ui, taskList, input);
        String response = DukeStub.getResponse(parser.run());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
        //@@author
        storage.writeStorage(taskList);

        if (input.equals("bye")) {
            //@@author CheyanneSim-reused
            //Reused with minor modification
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            //@@author
            pause.play();
        }
    }
}
