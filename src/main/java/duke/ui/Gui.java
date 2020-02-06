package duke.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Scanner;

public class Gui extends Ui{
    VBox dialogContainer;
    TextField userInput;

    private boolean isOpen = false;
    private StringBuilder tempText;

    public Gui(VBox dialogContainer, TextField userInput) {
        super(new Scanner(""));
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

    public void respond(String text) {
        this.dialogContainer.getChildren().add(getDialogLabel(text));
    }

    public void start(String... initials) {
        assert !this.isOpen : "illegal usage of responder";
        this.tempText = new StringBuilder();
        outPrint(resSpace);
        outPrintln(line);
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
        outPrintln(line);
        outPrintln();
        respond(this.tempText.toString());
        this.isOpen = false;
    }

    public void clearUserInput() {
        this.userInput.clear();
    }

    public String getUserInputText() {
        return this.userInput.getText();
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
