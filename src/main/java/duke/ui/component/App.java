package duke.ui.component;

import java.io.IOException;
import duke.DukeException;
import duke.command.Command;
import duke.model.TaskModel;
import duke.util.Parser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Duke's root application component.
 */
public class App extends VBox {
    @FXML
    private ScrollPane messageListContainer;

    @FXML
    private VBox messageList;

    @FXML
    private TextField messageInput;

    private final TaskModel taskModel;

    /**
     * Create Duke's root application component.
     */
    public App(TaskModel taskModel) {
        this.taskModel = taskModel;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/App.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        getStylesheets().add("/style/App.css");
    }

    @FXML
    private void askQuery(ActionEvent event) {
        String query = messageInput.getText();
        messageInput.clear();

        // Display user query in messageList
        messageList.getChildren().add(new UserMessage(query));

        // Process input and display result in messageList
        try {
            Command command = Parser.parse(query);
            Command.ExecuteResult result = command.execute(taskModel.getTasks());
            messageList.getChildren().add(new DukeMessage(result.getMessage()));
            taskModel.updateTasks(result.getTasks());
        } catch (DukeException exception) {
            messageList.getChildren().add(new DukeMessage(exception.getMessage() + "!"));
        } catch (IOException exception) {
            messageList.getChildren().add(new DukeMessage("Failed to persist data!"));
        }
    }

    @FXML
    private void initialize() {
        // Auto-scroll messageListContainer to its bottom
        messageListContainer.vvalueProperty().bind(messageList.heightProperty());

        // Display startup message
        messageList.getChildren().add(new DukeMessage("Hello, I'm Duke. What can I help you with?"));
    }
}
