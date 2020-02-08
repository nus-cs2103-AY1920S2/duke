package duke.ui;

import duke.Duke;
import duke.parser.Command;
import duke.parser.Parser;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Gui implements UiText {
    VBox dialogContainer;
    TextField userInput;

    private boolean isOpen = false;
    private StringBuilder tempText;

    public Gui(VBox dialogContainer, TextField userInput) {
        this.dialogContainer = dialogContainer;
        this.userInput = userInput;
    }

    private void outPrint(String text) {
        this.tempText.append(text);
    }

    private void outPrintln() {
        this.tempText.append("\n");
    }

    private void outPrintln(String text) {
        this.tempText.append(text);
        this.tempText.append("\n");
    }

    public void respond(String... text) {
        respond(Arrays.asList(text));
    }

    public void respond(List<String> text) {
        start();
        respondLine(text);
        over();
    }

    public void start(String... initials) {
        assert !this.isOpen : "illegal usage of responder";
        this.tempText = new StringBuilder();
        outPrint(resSpace);
        Scanner sc2;
        for (String str : initials) {
            sc2 = new Scanner(str);
            while (sc2.hasNext()) {
                outPrint(resSpace + " ");
                outPrintln(sc2.nextLine());
            }
        }
        this.isOpen = true;
    }

    public void respondLine(List<String> respondStr) {
        assert this.isOpen : "illegal usage of responder";
        Scanner sc2;
        for (String str : respondStr) {
            sc2 = new Scanner(str);
            while (sc2.hasNext()) {
                outPrint(resSpace + " ");
                outPrintln(sc2.nextLine());
            }
        }
    }

    public void over(String... remarks) {
        Scanner sc2;
        for (String str : remarks) {
            sc2 = new Scanner(str);
            while (sc2.hasNext()) {
                outPrint(resSpace + " ");
                outPrintln(sc2.nextLine());
            }
        }
        outPrint(resSpace);
        outPrintln();
        this.dialogContainer.getChildren().add(getDialogLabel(this.tempText.toString()));
        this.isOpen = false;
    }

    public void clearUserInput() {
        this.userInput.clear();
    }

    public String nextLine() {
        return this.userInput.getText();
    }

    public boolean hasNextLine() {
        return !this.userInput.getText().isEmpty();
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
