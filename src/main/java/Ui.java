import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The UI of the application.
 */
public class Ui extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the scroll pane to bind to the dialog container. Also prints out the welcome message on the chat.
     */
    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        printGreeting();
    }

    /**
     * Initializes duke.
     *
     * @param d the duke
     */
    public void setDuke(Duke d) {

        duke = d;
    }

    /**
     * Obtains the input command from the text field and creates a user dialog box containing the command. Clears the
     * text field after creating the dialog box. The command is passed to Duke and processed to get the appropriate
     * response, which is used to create another dialog box and appended to the dialog container.
     */
    @FXML
    private void handleUserInput() {

        String command = userInput.getText();

        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(command, userImage));
        userInput.clear();

        printResponse(duke.executeCommand(command));
    }

    /**
     * Prints the welcoming message in the chat.
     */
    public void printGreeting() {

        String greeting = "Hi! I'm Duke.\nHow can I help you?";

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greeting, dukeImage));
    }

    /**
     * Creates a dialog box containing the relevant response and append it onto the dialog container.
     * Will call for exit if bye is received.
     *
     * @param response the response.
     */
    private void printResponse(Response response) {

        String message = "";
        switch (response.getMessage()) {
        case ADD_TASK:
            message = "I've got your back. Adding the new task:\n";
            message += "    " + response.getArgument().toString();
            break;

        case COMMAND_NOT_RECOGNIZED:
            message = "I don't understand your request.";
            break;

        case DATE_NOT_RECOGNIZED:
            message = "Sorry. I only recognize date in the format YYYY-MM-DD";
            break;

        case DELETE:
            message = "The task has been removed.\n";
            message += "    " + response.getArgument().toString();
            break;

        case DONE:
            message = "Another task off the list. Good job!\n";
            message += "    " + duke.getTaskList().get((int) response.getArgument());
            break;

        case EMPTY_COMMAND:
            message = "Aren't you a quiet type.";
            break;

        case GOODBYE:
            message = "It's nice talking to you. See you soon! ;)";
            break;

        case IO_ERROR:
            message = "Opps. Something went wrong when saving your tasks.";
            break;

        case INDEX_NAN:
            message = "The index is not a number.";
            break;

        case INVALID_ARGUMENT:
            message = "You cannot set task count to less than zero.";
            break;

        case ITEM_NOT_FOUND:
            message = "That item is not on your list.";
            break;

        case LIST:
            message = "Here's your list:\n";

            TaskList list = (TaskList) response.getArgument();

            for (int i = 0; i < list.getSize(); i++) {
                message += "    " + (i + 1) + "." + list.get(i) + "\n";
            }

            break;

        case MISSING_DATE:
            message = "The date is missing.";
            break;

        case MISSING_DATE_TIME:
            message = "Either the date or time is missing.";
            break;

        case MISSING_DEADLINE:
            message = "When is the deadline?";
            break;

        case MISSING_DELETE_INDEX:
            message = "Which task do you want to delete?";
            break;

        case MISSING_DESCRIPTION:
            message = "What is the task about?";
            break;

        case MISSING_DONE_INDEX:
            message = "Which task have you completed?";
            break;

        case MISSING_END_TIME:
            message = "When does the event end?";
            break;

        case NO_TASK:
            message = "You have nothing to do today.";
            break;

        case NO_TASK_ON_DATE:
            message = "You have nothing to do on that day.";
            break;

        case TASK_COMPLETED:
            message = "You have already done that task.";
            break;

        case TASK_COUNT:
            if (Task.getTotalTaskCount() == 0) {
                message = "You have no more task today.";

            } else {
                message = "Now you've " + Task.getTotalTaskCount() + " task(s) in your list";
            }
            break;

        case TASK_FOUND:
            message = "These are the tasks you are looking for:\n";

            TaskList results = (TaskList) response.getArgument();

            for (int i = 0; i < results.getSize(); i++) {
                message += "    " + (i + 1) + "." + results.get(i) + "\n";
            }
            break;

        case TASK_NOT_FOUND:
            message = "There is no task matching your keyword";
            break;

        default:
            //Do nothing
        }
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(message, dukeImage));

        if (response.getMessage().equals(Message.GOODBYE)) {

            try {
                Platform.exit();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}