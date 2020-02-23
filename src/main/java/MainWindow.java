import duke.Task;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Timer;
import java.util.TimerTask;


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
    @FXML
    private Button sendButton;
    @FXML
    private Button offButton;

    private boolean playing = false;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser2.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke2.jpg"));
    //public Media media = new Media(getClass().getResourceAsStream("/music/music.mp3").toString());

    //MediaPlayer mediaPlayer = new MediaPlayer(media);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input =  userInput.getText();
        String response =  duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("User says:\n" +input, userImage),
                DialogBox.getDukeDialog("Duke says:\n" +response, dukeImage)
        );
        if (response.contains("Bye. Hope to see you again")) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 500);
        }
        userInput.clear();
    }

    @FXML
    private void stopMusic() {
        playing = !playing;
        if(playing) {
            //mediaPlayer.play();
        } else {
            //mediaPlayer.stop();
        }
    }
}
