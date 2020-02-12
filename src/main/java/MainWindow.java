import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TaskList tasks;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/rick.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/mrmeeseeks.jpg"));

    /**
     * Initialize the GUI. Sets duke's welcome message to user.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        tasks = d.tasks;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {

        String errMsg = "";
        String input = userInput.getText();
        String[] arrString = input.split(" ", 2);
        try {
            if (arrString[0].equalsIgnoreCase("bye")) {
                StringBuilder sb = new StringBuilder();
                Ui.listCommand(tasks, sb);
                SaveToFile.usingFileWriter(sb.toString());
                handleExit();
            } else if (arrString[0].equalsIgnoreCase("list")) {
                if (tasks.getTaskListSize() == 0) {
                    handleDialogOutput(input, Ui.emptyList());
                    SaveToFile.usingFileWriter("");
                } else {
                    StringBuilder sb = new StringBuilder();
                    handleDialogOutput(input, Ui.listCommand(tasks, sb));
                    SaveToFile.usingFileWriter(sb.toString());
                }
            } else if (arrString[0].equalsIgnoreCase("done")) {
                int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.getTaskListSize()) {
                    handleDialogOutput(input, Ui.doneTask(tasks, taskNumber));
                } else {
                    handleDialogOutput(input, Ui.invalidTask());
                }
            } else if (arrString[0].equalsIgnoreCase("delete")) {
                int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.getTaskListSize()) {
                    handleDialogOutput(input, Ui.deletedTask(tasks, taskNumber));
                } else {
                    handleDialogOutput(input, Ui.invalidTask());
                }
            } else if (arrString[0].equalsIgnoreCase("todo")) {
                errMsg = "todo";
                Todo todo = new Todo(arrString[1]);
                tasks.addTask(todo);
                handleDialogOutput(input, Ui.addedCommand(tasks.getTaskListSize()));
            } else if (arrString[0].equalsIgnoreCase("event")) {
                errMsg = "event";
                String[] eventString = arrString[1].split("/");
                Event event = new Event(eventString[0].strip(), eventString[1].substring(2).strip());
                tasks.addTask(event);
                handleDialogOutput(input, Ui.addedCommand(tasks.getTaskListSize()));
            } else if (arrString[0].equalsIgnoreCase("deadline")) {
                errMsg = "deadline";
                String[] deadlineString = arrString[1].split("/");
                Deadline deadline = new Deadline(deadlineString[0].strip(),
                        deadlineString[1].substring(2).strip());
                tasks.addTask(deadline);
                handleDialogOutput(input, Ui.addedCommand(tasks.getTaskListSize()));
            } else if (arrString[0].equalsIgnoreCase("find")) {
                errMsg = "find";
                handleDialogOutput(input, tasks.findTask(arrString[1]));
            } else if (arrString[0].equalsIgnoreCase("help")) {
                handleDialogOutput(input, Ui.helpCommand());
            } else {
                handleDialogOutput(input, Ui.invalidCommand());
                throw new DukeException(Ui.invalidCommand());
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            handleDialogOutput(input, Ui.incompleteCommand(errMsg));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void handleExit() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("Bye", userImage),
                DialogBox.getDukeDialog(Ui.goodbyeMessage(), dukeImage)
        );
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void handleDialogOutput(String input, String uiCommand) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(uiCommand, dukeImage)
        );
        userInput.clear();
    }
}