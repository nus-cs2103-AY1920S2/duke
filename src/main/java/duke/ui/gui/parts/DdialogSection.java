package duke.ui.gui.parts;

import duke.DukeMain;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.net.URL;

public class DdialogSection extends GuiComponent<ScrollPane> {
    private static final String FXML = "dialogwindow.fxml";

    @FXML
    private VBox dialogContainer;

    public DdialogSection() {
        super(FXML);
    }

    public void addRightDialogBox(String text) {
        this.dialogContainer.getChildren().add(getDialogBox(text, DDdialogBoxType.RIGHT));
    }

    public void addLeftDialogBox(String text) {
        this.dialogContainer.getChildren().add(getDialogBox(text, DDdialogBoxType.LEFT));
    }

    private static final URL avatar = DukeMain.class.getResource("/img/fb-avatar.jpg");
    private Region getDialogBox(String text, DDdialogBoxType type) {
        return new DDdialogBox(type, text, avatar).getRoot();
    }

    public void setDimension(double prefWidth) {
        this.dialogContainer.setPrefWidth(prefWidth);
    }

    private VBox getDialogContainer() {
        return this.dialogContainer;
    }
}
