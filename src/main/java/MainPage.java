
import helper.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.Task;
import helper.Command;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainPage extends AnchorPane {
    /**
     * object needed for MainPage
     */
    private Parser parser;
    private Storage storage;
    private ArrayList<Task> arrTask;
    private Ui uiHelper;



    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke_1.png"));

    @FXML
    public void initialize() throws FileNotFoundException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        arrTask = new ArrayList<Task>();
        storage = new Storage(arrTask, "duke.txt");
        uiHelper = new Ui();

        String fileData = storage.readFile();
        parser = new Parser(uiHelper);

        String welcomeMessage = uiHelper.welcomeMessage();

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage + "\n" + fileData, dukeImage)
        );
    }



    public String getResponse(String input) throws Exception {
        Command c = parser.parse(input);
        String responseString = c.execute(arrTask, uiHelper);
        storage.saveIntoFile();
        return responseString;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws Exception {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}