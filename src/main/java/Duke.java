import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke class to contain the main function for execution.
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    private ChatBot bot = new ChatBot();

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    public void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        /*
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );*/
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = bot.getParser();
        bot.updateTaskList();
        TaskList list = bot.getTaskLists();
        Storage storage = bot.getStorage();
        HistoryManager historyManager = bot.getHistoryManager();
        UI ui = bot.getUI();
        try {
            Command command = parser.respondToUser(input);
            assert command != null; // assert that command not null
            String reply = command.execute(ui, list, storage, historyManager).trim();
            return reply;
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * Main driver method.
     * @param args arguments.
     */
    public static void main(String[] args) {
        // add a new chatbot for users
        ChatBot duke = new ChatBot();
        Scanner sc = new Scanner(System.in);
        // run the chat-bot
        duke.runChatBot(sc);
    }
}
