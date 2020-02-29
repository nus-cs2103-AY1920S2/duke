package duke.ui.gui.parts;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class DdialogSection extends GuiComponent<ScrollPane> {
    private static final String FXML = "dialogwindow.fxml";

    @FXML
    private VBox dialogContainer;

    public DdialogSection() {
        super(FXML);
    }

    public void addDialogLabel(String text) {
        this.dialogContainer.getChildren().add(getDialogLabel(text));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public void setDimension(double prefWidth) {
        this.dialogContainer.setPrefWidth(prefWidth);
    }

    public VBox getDialogContainer() {
        return this.dialogContainer;
    }
}
