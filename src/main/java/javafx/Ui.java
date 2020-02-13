package javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.Duke;
import main.Response;
import main.TaskList;
import task.Task;

/**
 * The UI of the application.
 */
public class Ui extends AnchorPane {

    //Constant messages
    private static final String ADD_TASK_MSG = "I've got your back. Adding the new task:\n";
    private static final String CMD_NOT_RECOGNIZED_MSG = "I don't understand your request.";
    private static final String DATE_NOT_RECOGNIZED_MSG = "Sorry. I only recognize date in the format YYYY-MM-DD";
    private static final String DELETE_MSG = "The task has been removed.\n";
    private static final String DONE_MSG = "Another task off the list. Good job!\n";
    private static final String DUPLICATE_TASK_MSG = "The task already exists.";
    private static final String EMPTY_CMD_MSG = "Aren't you a quiet type.";
    private static final String IO_ERR_MSG = "Opps. Something went wrong when saving your tasks.";
    private static final String INDEX_NAN_MSG = "The index is not a number.";
    private static final String INVALID_ARG_MSG = "You cannot set task count to less than zero.";
    private static final String ITEM_NOT_FOUND_MSG = "That item is not on your list.";
    private static final String LIST_MSG = "Here's your list:\n";
    private static final String MISSING_DATE_MSG = "The date is missing.";
    private static final String MISSING_DATE_TIME_MSG = "Either the date or time is missing.";
    private static final String MISSING_DEADLINE_MSG = "When is the deadline?";
    private static final String MISSING_DELETE_INDEX_MSG = "Which task do you want to delete?";
    private static final String MISSING_DESCRIPTION_MSG = "What is the task about?";
    private static final String MISSING_DONE_INDEX_MSG = "Which task have you completed?";
    private static final String MISSING_END_TIME_MSG = "When does the event end?";
    private static final String MISSING_KEYWORD_MSG = "You need a keyword to search.";
    private static final String NO_MORE_TASK_MSG = "You have no more task today.";
    private static final String NO_TASK_MSG = "You have nothing to do today.";
    private static final String NO_TASK_ON_DATE_MSG = "You have nothing to do on that day.";
    private static final String TASK_ALREADY_COMPLETED_MSG = "You have already done that task.";
    private static final String TASK_FOUND_MSG = "These are the tasks you are looking for:\n";
    private static final String TASK_NOT_FOUND_MSG = "There is no task matching your keyword";

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
     * text field after creating the dialog box. The command is passed to main.Duke and processed to get the appropriate
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

        String greeting = "Hi! I'm main.Duke.\nHow can I help you?";

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
            message = ADD_TASK_MSG;
            message += "    " + response.getArgument().toString();
            break;

        case COMMAND_NOT_RECOGNIZED:
            message = CMD_NOT_RECOGNIZED_MSG;
            break;

        case DATE_NOT_RECOGNIZED:
            message = DATE_NOT_RECOGNIZED_MSG;
            break;

        case DELETE:
            message = DELETE_MSG;
            message += "    " + response.getArgument().toString();
            break;

        case DONE:
            message = DONE_MSG;
            message += "    " + duke.getTaskList().get((int) response.getArgument());
            break;

        case DUPLICATE_TASK:
            message = DUPLICATE_TASK_MSG;
            break;

        case EMPTY_COMMAND:
            message = EMPTY_CMD_MSG;
            break;

        case GOODBYE:
            try {
                Platform.exit();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return;

        case IO_ERROR:
            message = IO_ERR_MSG;
            break;

        case INDEX_NAN:
            message = INDEX_NAN_MSG;
            break;

        case INVALID_ARGUMENT:
            message = INVALID_ARG_MSG;
            break;

        case ITEM_NOT_FOUND:
            message = ITEM_NOT_FOUND_MSG;
            break;

        case LIST:
            TaskList list = (TaskList) response.getArgument();
            message = LIST_MSG + list.toString();
            break;

        case MISSING_DATE:
            message = MISSING_DATE_MSG;
            break;

        case MISSING_DATE_TIME:
            message = MISSING_DATE_TIME_MSG;
            break;

        case MISSING_DEADLINE:
            message = MISSING_DEADLINE_MSG;
            break;

        case MISSING_DELETE_INDEX:
            message = MISSING_DELETE_INDEX_MSG;
            break;

        case MISSING_DESCRIPTION:
            message = MISSING_DESCRIPTION_MSG;
            break;

        case MISSING_DONE_INDEX:
            message = MISSING_DONE_INDEX_MSG;
            break;

        case MISSING_END_TIME:
            message = MISSING_END_TIME_MSG;
            break;

        case MISSING_FIND_INDEX:
            message = MISSING_KEYWORD_MSG;
            break;
        case NO_TASK:
            message = NO_TASK_MSG;
            break;

        case NO_TASK_ON_DATE:
            message = NO_TASK_ON_DATE_MSG;
            break;

        case TASK_COMPLETED:
            message = TASK_ALREADY_COMPLETED_MSG;
            break;

        case TASK_COUNT:
            if (Task.getTotalTaskCount() == 0) {
                message = NO_MORE_TASK_MSG;

            } else {
                message = "Now you've " + Task.getTotalTaskCount() + " task(s) in your list";
            }
            break;

        case TASK_FOUND:
            TaskList results = (TaskList) response.getArgument();
            message = TASK_FOUND_MSG + results.toString();
            break;

        case TASK_NOT_FOUND:
            message = TASK_NOT_FOUND_MSG;
            break;

        default:
            //Do nothing
        }
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(message, dukeImage));
    }

}