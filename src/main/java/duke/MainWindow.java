package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
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
        String response = duke.processInstruction(input);

        Label userText = new Label(input);
        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

//    private String processInstruction(String input) {
//        try {
//            Parser instruction = new Parser(input);
//            Command command = instruction.getCommand();
//
//            switch (command) {
//                case BYE:
//                    try {
//                        storage.writeToFile(tasks);
//                        return ui.showFarewell();
//                    } catch (IOException e) {
//                        return "Error: Unable to write to file.\n";
//                    }
//                case DONE: {
//                    int taskNum = Integer.parseInt(instruction.getParameters());
//                    tasks.setAsDone(taskNum);
//                    return ui.showSetAsDone(tasks, tasks.getTask(taskNum));
//                }
//                case TODO:
//                    Task todo = new Todo(instruction.getParameters());
//                    tasks.addTask(todo);
//                    return ui.showAddTask(tasks, todo);
//                case DEADLINE:
//                    Task deadline = new Deadline(instruction.getParameters(), instruction.getDate());
//                    tasks.addTask(deadline);
//                    return ui.showAddTask(tasks, deadline);
//                case EVENT:
//                    Task event = new Event(instruction.getParameters(), instruction.getDate());
//                    tasks.addTask(event);
//                    return ui.showAddTask(tasks, event);
//                case LIST:
//                    return ui.showTasks(tasks);
//                case DELETE:
//                    int taskNum = Integer.parseInt(instruction.getParameters());
//                    Task delTask = tasks.getTask(taskNum);
//                    tasks.deleteTask(taskNum);
//                    return ui.showDeleteTask(tasks, delTask);
//                case FIND:
//                    return ui.showFound(tasks.findTasks(instruction.getParameters()));
//                default:
//                    return "";
//            }
//        } catch (InvalidInstructionException e) {
//            return String.format("Error: %s. Please try again.%n%n", e.getMessage());
//        }
//    }
}
