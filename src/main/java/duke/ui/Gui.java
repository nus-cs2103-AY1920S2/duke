package duke.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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

    /**
     * Start a dialog box with the initial texts
     *
     * @param initials = intial texts
     */
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

    /**
     * Respond to user with a list of strings using the same dialog box, must be used after start()
     * Each String in the list is a line inside the particular dialog box
     *
     * @param respondStr = list of strings to respond to user
     */
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

    public void respondLine(String... respondStr) {
        respondLine(Arrays.asList(respondStr));
    }

    /**
     * Close the dialog box with some remarks
     *
     * @param remarks = remarks to the user before closing the dialog box
     */
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

    /**
     * Clears user input box
     */
    public void clearUserInput() {
        this.userInput.clear();
    }

    /**
     * Get input by user
     */
    public String nextLine() {
        return this.userInput.getText();
    }

    /**
     * user inputted string?
     */
    public boolean hasNextLine() {
        return !this.userInput.getText().isEmpty();
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
