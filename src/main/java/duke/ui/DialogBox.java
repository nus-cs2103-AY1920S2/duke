package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    public static final double DEFAULT_SPACING = 5;
    public static final double IMAGE_WIDTH = 84.0;
    public static final double IMAGE_HEIGHT = 48.0;

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label label, ImageView imageView) {
        super(DialogBox.DEFAULT_SPACING);
        this.text = label;
        this.displayPicture = imageView;

        text.setWrapText(true);

        displayPicture.setFitWidth(DialogBox.IMAGE_WIDTH);
        displayPicture.setFitHeight(DialogBox.IMAGE_HEIGHT);

        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        this.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: black;");
    }

    private void flip() {
        this.setAlignment(Pos.BOTTOM_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(
                getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        DialogBox db = new DialogBox(label, imageView);
        return db;
    }

    public static DialogBox getReplyDialog(Label label, ImageView imageView) {
        DialogBox db = new DialogBox(label, imageView);
        db.flip();
        return db;
    }
}