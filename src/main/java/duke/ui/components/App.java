package duke.ui.components;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * Duke's root application component.
 */
public class App extends VBox {
    @FXML
    private VBox messageList = null;

    /**
     * Create Duke's root application component.
     */
    public App() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/App.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void askQuery(ActionEvent event) {
        // TODO: Insert request-response logic
    }
}
