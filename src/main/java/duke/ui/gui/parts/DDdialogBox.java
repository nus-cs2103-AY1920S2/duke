package duke.ui.gui.parts;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

import java.net.URL;

enum DDdialogBoxType {
    RIGHT, LEFT
}

public class DDdialogBox extends GuiComponent<Region> {
    private static final String FXML = "dialogbox.fxml";

    @FXML
    FlowPane dialogBox;

    public DDdialogBox(DDdialogBoxType type, String str, URL imgLocation) {
        super(FXML);
        setAttr(type);
        fillDetails(type, str, imgLocation);
    }

    private void setAttr(DDdialogBoxType type) {
        switch (type) {
            case LEFT:
                this.dialogBox.setAlignment(Pos.TOP_RIGHT);
                break;
            case RIGHT:
            default:
                this.dialogBox.setAlignment(Pos.TOP_LEFT);
                break;
        }
    }

    private void fillDetails(DDdialogBoxType type, String str, URL imgLocation) {
        switch (type) {
            case LEFT:
                this.dialogBox.getChildren().addAll(getLabel(str), getImage(imgLocation));
                break;
            case RIGHT:
            default:
                this.dialogBox.getChildren().addAll(getImage(imgLocation), getLabel(str));
                break;
        }
    }

    private Label getLabel(String str) {
        Label labelElem = new Label(str);
        labelElem.setWrapText(true);
        labelElem.setPrefWidth(200.0);
        return labelElem;
    }

    private ImageView getImage(URL imgLocation) {
        ImageView imgView = new ImageView();
        Image image = new Image(imgLocation.toString());
        imgView.setImage(image);
        imgView.setFitHeight(99.0);
        imgView.setFitWidth(99.0);
        return imgView;
    }
}
