import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;
    private static final Insets DIALOG_BOX_INSETS = new Insets(0, 0, 50, 0);

    public DialogBox(Label l, ImageView iv) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setPadding(new Insets(0, 20, 0, 0));
        DialogBox db = new DialogBox(l, iv);
        db.setPadding(DIALOG_BOX_INSETS);
        return db;
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        l.setPadding(new Insets(0, 0, 0, 20));
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        db.setPadding(DIALOG_BOX_INSETS);
        return db;
    }
}