package duke.gui;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.command.Command;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/** Handles user input to GUI. */
public class DialogParser {
    // User icon image obtained from
    // https://www.hiclipart.com/free-transparent-background-png-clipart-dlzoi/download
    private static Image userIcon = new Image(
            DialogParser.class.getResourceAsStream("/images/user-icon-hiclipart.png"));
    // Mr Robot: https://www.usanetwork.com/mrrobot
    private static Image mrRobotIcon = new Image(
            DialogParser.class.getResourceAsStream("/images/mr-robot-logo.jpg"));

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     *
     * @param dialogContainer used to place user and response text
     * @param userInput contains user input
     */
    public static void handleUserInput(Duke duke, VBox dialogContainer, TextField userInput) {
        Label userText = new Label(userInput.getText());
        Label mrRobotText = new Label(getResponse(userInput.getText(), duke));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(userIcon)),
                new DialogBox(mrRobotText, new ImageView(mrRobotIcon))
        );
        userInput.clear();
    }

    /**
     * Returns a response message based on given input command String.
     *
     * @param input command string to be processed by Duke
     * @param duke instance used for command execution
     * @return Duke's response
     */
    private static String getResponse(String input, Duke duke) {
        String response;
        // Change stdout for duke
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try {
            Command command = Parser.parse(input);
            command.execute(duke.getTasks(), duke.getUi(), duke.getStorage());
        } catch (DukeException dukeException) {
            // Display error message
            System.out.print(dukeException.getMessage());
        }

        response = output.toString();
        // Reset stdout for duke
        System.setOut(System.out);
        return response;
    }
}
