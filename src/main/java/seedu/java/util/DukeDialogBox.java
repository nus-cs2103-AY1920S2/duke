package seedu.java.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class DukeDialogBox extends DialogBox {
    private DukeDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DukeDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setMinHeight(Label.USE_PREF_SIZE);
        dialog.setWrapText(true);
        dialog.setTextAlignment(TextAlignment.RIGHT);

    }

    public static DukeDialogBox getDukeDialog (String text, Image img){
        return new DukeDialogBox(text, img);
    }
}
