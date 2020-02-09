import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import packagedirectory.test.ChatBox;
import packagedirectory.test.DukeException;
import packagedirectory.test.Message;

public class Duke {

    private ChatBox chatBox = new ChatBox("./src/main/duke.txt");

    public String initialize() {
        String initMsg = chatBox.initialise();
        return initMsg;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        output = chatBox.reply(new Message(input));
        return "Duke heard: " + output;
    }

    public boolean isClose() {
        return chatBox.isClose();
    }
}
