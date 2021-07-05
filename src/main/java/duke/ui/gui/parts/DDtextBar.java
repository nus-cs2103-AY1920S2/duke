package duke.ui.gui.parts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class DDtextBar extends GuiComponent<Region> {
    private static final String FXML = "textbar.fxml";

    @FXML
    private TextField textField;

    public DDtextBar(TextBarEnterHandler handler) {
        super(FXML);
        addKeyboardEnterListener(handler);
    }

    private void addKeyboardEnterListener(TextBarEnterHandler handler) {
        this.textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent mouseEvent) {
                handler.execute();
            }
        });
    }

    public void setDimension(double prefWidth) {
        this.textField.setPrefWidth(prefWidth);
    }

    public void clearText() {
        this.textField.clear();
    }

    public String getText() {
        return this.textField.getText();
    }

    @FunctionalInterface
    public interface TextBarEnterHandler {
        void execute();
    }
}
