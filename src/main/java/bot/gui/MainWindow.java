package bot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * MainWindow is the class that provides the layout
 * for the other components of 4LC3N-BOT's
 * graphical user interface
 *
 * <p>Adapted from MainWindow in JavaFX tutorial found at
 * https://github.com/se-edu/duke/tree/master/tutorials
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox chatContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button enterButton;

    private Baron baron;

    private Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image botImage = new Image(
            this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the MainWindow. This also sets the
     * user image and the bot image used for the
     * ChatBoxes
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(chatContainer.heightProperty());
        ChatBox.setUserImage(this.userImage);
        ChatBox.setBotImage(this.botImage);
    }

    /**
     * Sets the Baron for the MainWindow, to allow access
     * to the CommandParser,
     *
     * @param br The Baron to be set
     */
    public void setBaron(Baron br) {
        this.baron = br;
    }

    /**
     * Gets the user input from the text box, and then
     * creates ChatBoxes to show the user input. The
     * user input is also given to Baron to generate
     * the bot's response, and the bot's response
     * will also be shown in a ChatBox
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        baron.receiveInput(input);
    }

    /**
     * Gets the VBox in the MainWindow that holds all
     * the chat boxes (also known as chat container)
     */
    public VBox getChatContainer() {
        return this.chatContainer;
    }
}