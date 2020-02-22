package seedu.java.util;

import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * A UI for user interaction.
 * intro()
 * showLoadingError()
 * inputLoop()
 * Controls how Duke should look like.
 */
public class Ui {
    /**
     * Prints an error. Intended for storage if it fails to load
     */
    public void showLoadingError() {
        System.out.println("System failed to load file. Opening with a new blank file");
    }

    /**
     * Takes in an input and returns the input. Intended for TaskList & parsing.
     * @return input for parsing
     */
    public String input() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public void output(String out) {
        System.out.println(out);
    }
}