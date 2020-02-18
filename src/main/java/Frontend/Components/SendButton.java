package Frontend.Components.SendButton;

import java.io.IOException;
import java.util.Collections;

import Frontend.Components.MainWindow;
import Frontend.Objects.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.Circle;

public class SendButton extends Button {

    public SendButton( String buttonText ){
        super(buttonText);

        this.setStyle("-fx-background-color: #0066e3; -fx-text-fill: #fff; -fx-cursor: hand;");

    }

}