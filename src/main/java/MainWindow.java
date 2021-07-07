import packagedirectory.test.Tasks;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.TableColumn;

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
    private Button writeButton;
    @FXML
    private TableView<Tasks> table;
    @FXML
    private TableColumn<Tasks, String> typeColumns;
    @FXML
    private TableColumn<Tasks, String> activitiesColumns;
    @FXML
    private TextField typeField;
    @FXML
    private TextField detailField;

    private Duke duke;

    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    private ObservableList<Tasks> observableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        typeColumns.setCellValueFactory(new PropertyValueFactory<>("logo"));
        activitiesColumns.setCellValueFactory(new PropertyValueFactory<>("text"));
        table.setItems(observableList);
    }

    public void setDuke(Duke d, Stage stage) {
        this.stage = stage;
        duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.initialize(), dukeImage)
        );
        table.setEditable(true);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if(duke.isClose()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(e -> stage.close());
            delay.play();
        }
    }

    @FXML
    private void handleUserWriteInput() throws InterruptedException {
        String typeInput = typeField.getText();
        String detailInput = detailField.getText();
        table.getItems().add(new Tasks(typeInput, detailInput));
        detailField.clear();
    }

    @FXML
    private void handleUserExecuteInput() throws InterruptedException {
        for (Tasks tasks : observableList) {
            duke.getResponse(tasks.getLogo(), tasks.getText());
        }
        table.getItems().clear();
    }
}
