package duke.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    private enum Sender {
        USER,
        DUKE
    }

    public DialogBox(Label l, ImageView iv, Sender sender) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setMinHeight(Region.USE_PREF_SIZE);
        text.setMaxWidth(300);

        displayPicture.setFitHeight(100);
        displayPicture.setFitWidth(100);

        Circle circleClip = new Circle(50, 50, 50);
        displayPicture.setClip(circleClip);

        this.setSpacing(10);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        if (sender.equals(Sender.DUKE)) {
            this.setStyle("-fx-background-color:#B1C0CF");
        } else {
            this.setStyle("-fx-background-color:#D4D5D5");
        }
        this.setPadding(new Insets(0, 10, 0, 10));
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String l, Image iv) {
        Label label = new Label(l);
        label.setPadding(new Insets(0, 15, 0, 0));
        var db = new DialogBox(label, new ImageView(iv), Sender.USER);
        db.flip();
        return db;
    }

    public static DialogBox getDukeDialog(String l, Image iv) {
        Label label = new Label(l);
        label.setPadding(new Insets(0, 0, 0, 15));
        label.setAlignment(Pos.CENTER_LEFT);
        return new DialogBox(label, new ImageView(iv), Sender.DUKE);
    }
}
