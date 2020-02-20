package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    //@@author Exeexe93-reused
    //Reused from https://github.com/Exeexe93/duke/blob/master/tutorials/javaFxTutorialPart4.md.

    /**
     * Set the text and image based on the arguments into the dialog box.
     *
     * @param text text to display in dialog box
     * @param img  image to display in dialog box
     */
    private DialogBox(String text, Image img) {
        loadFxml();
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    //@@author Exeexe93-reused
    //Reused from https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md
    //with minor modifications

    /**
     * Load the style from DialogBox.fxml and set the controller to this instance and load it.
     * Print out any IOException occurs in the terminal.
     */
    private void loadFxml() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            //@@author Exeexe93
            System.out.println(e.getMessage());
        }
    }

    //@@author Exeexe93-reused
    //Reused from https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md.

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    //@@author Exeexe93-reused
    //Reused from https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md.

    /**
     * Return user Dialog Box with the text and image in it.
     *
     * @param text text to be print out in dialog box
     * @param img  image to be display in dialog box
     * @return DialogBox with the text and image in it
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    //@@author Exeexe93-reused
    //Reused from https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md.

    /**
     * Return Duke Dialog Box with the text and image in it. Invert the dialog object around the vertical axis.
     *
     * @param text text to be print out in dialog box
     * @param img  image to be display in dialog box
     * @return DialogBox with the text and image in it
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}