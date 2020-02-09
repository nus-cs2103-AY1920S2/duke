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

    @FXML
    private Duke duke;
    @FXML
    private SaveToFile saveToFile = new SaveToFile();
    @FXML
    private Ui ui = new Ui();
    @FXML
    private TaskList tasks = new TaskList(saveToFile.loadList("./out.txt"));

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/rick.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/mrmeeseeks.jpg"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.showWelcome(), dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String[] arrString = input.split(" ", 2);
        try {
            if (arrString[0].equalsIgnoreCase("bye")) {
                handleExit();
            } else if (arrString[0].equalsIgnoreCase("list")) {
                if (tasks.getTaskListSize() == 0) {
                    handleDialogOutput(input, ui.emptyList());
                    saveToFile.usingFileWriter("");
                } else {
                    StringBuilder sb = new StringBuilder();
                    handleDialogOutput(input, ui.listCommand(tasks, sb));
                    saveToFile.usingFileWriter(sb.toString());
                }
            } else if (arrString[0].equalsIgnoreCase("done")) {
                try {
                    int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.getTaskListSize()) {
                        handleDialogOutput(input, ui.doneTask(tasks, taskNumber));
                    } else {
                        handleDialogOutput(input, ui.invalidTask());
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    handleDialogOutput(input, ui.missingTaskNumber());
                    throw new DukeException(ui.missingTaskNumber());
                }

            } else if (arrString[0].equalsIgnoreCase("delete")) {
                try {
                    int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.getTaskListSize()) {
                        handleDialogOutput(input, ui.deletedTask(tasks, taskNumber));
                    } else {
                        handleDialogOutput(input, ui.invalidTask());
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    handleDialogOutput(input, ui.missingTaskNumber());
                    throw new DukeException(ui.missingTaskNumber());
                }

            } else if (arrString[0].equalsIgnoreCase("todo")) {
                try {
                    Todo todo = new Todo(arrString[1]);
                    tasks.addTask(todo);
                    handleDialogOutput(input, ui.taskInList(tasks.getTaskListSize()));
                } catch (ArrayIndexOutOfBoundsException ex) {
                    handleDialogOutput(input, ui.incompleteCommand("Todo"));
                    throw new DukeException(ui.incompleteCommand("Todo"));
                }
            } else if (arrString[0].equalsIgnoreCase("event")) {
                try {
                    String[] eventString = arrString[1].split("/");
                    Event event = new Event(eventString[0].strip(), eventString[1].substring(2).strip());
                    tasks.addTask(event);
                    handleDialogOutput(input, ui.taskInList(tasks.getTaskListSize()));
                } catch (ArrayIndexOutOfBoundsException ex) {
                    handleDialogOutput(input, ui.incompleteCommand("Event"));
                    throw new DukeException(ui.incompleteCommand("Event"));
                }
            } else if (arrString[0].equalsIgnoreCase("deadline")) {
                try {
                    String[] deadlineString = arrString[1].split("/");
                    Deadline deadline = new Deadline(deadlineString[0].strip(),
                            deadlineString[1].substring(2).strip());
                    tasks.addTask(deadline);
                    handleDialogOutput(input, ui.taskInList(tasks.getTaskListSize()));
                } catch (ArrayIndexOutOfBoundsException ex) {
                    handleDialogOutput(input, ui.incompleteCommand("Deadline"));
                    throw new DukeException(ui.incompleteCommand("Deadline"));
                }
            } else if (arrString[0].equalsIgnoreCase("Find")) {
                try {
                    tasks.findTask(arrString[1]);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    handleDialogOutput(input, ui.incompleteCommand("Find"));
                    throw new DukeException(ui.incompleteCommand("Find"));
                }
            } else {
                handleDialogOutput(input, ui.invalidCommand());
                throw new DukeException(ui.invalidCommand());
            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }


    @FXML
    private void handleExit() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("Bye", userImage),
                DialogBox.getDukeDialog(ui.goodbyeMessage(), dukeImage)
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

    @FXML
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}