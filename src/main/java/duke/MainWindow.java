package duke;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/** Controller for duke.MainWindow. Provides the layout for the other controls. */
public class MainWindow extends AnchorPane {
  @FXML private ScrollPane scrollPane;
  @FXML private VBox dialogContainer;
  @FXML private TextField userInput;
  @FXML private Button sendButton;

  private Duke duke;

  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Me.jpg"));
  private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.jpeg"));

  @FXML
  public static void triggerReminder(String message, long delay) {
    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.seconds(delay), ae -> MainWindow.showReminder(message)));
    timeline.play();
  }

  @FXML
  /**
   * Is triggered at the delayed time in Duke itself.
   * https://thecodinginterface.com/blog/javafx-alerts-and-dialogs/#informational-alert
   */
  public static void showReminder(String response) {
    var alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("reminder");
    alert.setHeaderText("Reminder");
    alert.setContentText(response);
    alert.show();
  }

  @FXML
  public void initialize() {
    scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
  }

  public void setDuke(Duke d) {
    duke = d;
  }

  /**
   * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then
   * appends them to the dialog container. Clears the user input after processing.
   */
  @FXML
  private void handleUserInput() {
    String input = userInput.getText();
    String response = duke.getResponse(input);
    dialogContainer
        .getChildren()
        .addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage));
    userInput.clear();
  }
}
