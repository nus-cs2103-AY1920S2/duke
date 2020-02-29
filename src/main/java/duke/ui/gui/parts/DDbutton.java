package duke.ui.gui.parts;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class DDbutton extends GuiComponent<Region> {
    private static final String FXML = "button.fxml";
    @FXML
    private Button button;

    public DDbutton(String label, ButtonPressHandler pressHandler) {
        super(FXML);
        initButton(label, pressHandler);
    }

    private void initButton(String label, ButtonPressHandler pressHandler) {
        this.button.setText(label);
        addMouseClickListener(pressHandler);
    }

    private void addMouseClickListener(ButtonPressHandler handler) {
        this.button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handler.execute();
            }
        });
    }

    public void setDimension(double prefWidth) {
        this.button.setPrefWidth(prefWidth);
    }

    @FunctionalInterface
    public interface ButtonPressHandler {
        void execute();
    }

}
