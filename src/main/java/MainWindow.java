import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image gooseImage = new Image(this.getClass().getResourceAsStream("/images/DaGoose.png"));
    private static String WELCOME_MESSAGE = "Honk, this Goose. Do you need help with something?";


    @FXML
    public void initialize() {
        ui = new Ui();
        storage = new Storage("duke.txt", "duke_prev.txt", "mainList.txt", "mainList_prev.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getGooseDialog(WELCOME_MESSAGE, gooseImage)
        );
    }

    public void setGoose(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGooseDialog(response, gooseImage)
        );
        userInput.clear();
    }

    @FXML
    private String getResponse(String input) {
        boolean isBye = false;
        Boolean marked;

        while (!isBye) {
            isBye = Parser.isBye(input);

            if (!isBye) {
                try {
                    String command = Parser.parseCommand(input);
                    assert !command.equalsIgnoreCase("bye");
                    String[] inputArr = input.split(" ");
                    try {
                        int taskIndex;
                        Task selected;

                        switch (command) {
                            case ("list"):
                                return "Honk! Here's your current task list: \n" + storage.loadList();

                            case ("done"):
                                storage.saveLastState(tasks.getTaskList());
                                taskIndex = Integer.parseInt(inputArr[1]) - 1;
                                selected = tasks.getTaskList().get(taskIndex);
                                marked = tasks.markDone(taskIndex);
                                storage.save(tasks.getTaskList());
                                if (marked) {
                                    String doneHeader = "Good job! I've honked it as done:\n  ";
                                    return doneHeader + selected;
                                } else {
                                    return "Honk! Task is already done.";
                                }

                            case ("undone"):
                                storage.saveLastState(tasks.getTaskList());
                                taskIndex = Integer.parseInt(inputArr[1]) - 1;
                                selected = tasks.getTaskList().get(taskIndex);
                                marked = tasks.markUndone(taskIndex);
                                storage.save(tasks.getTaskList());
                                if (marked) {
                                    String undoneHeader = "Hope you complete it soon! I've honked it as undone:\n  ";
                                    return undoneHeader + selected;
                                } else {
                                    return "Honk! Task is already marked undone.";
                                }

                            case ("delete"):
                                storage.saveLastState(tasks.getTaskList());
                                taskIndex = Integer.parseInt(inputArr[1]) - 1;
                                selected = tasks.getTaskList().get(taskIndex);
                                tasks.deleteTask(taskIndex);
                                storage.save(tasks.getTaskList());

                                String deleteHeader = "Honk! Removed this task from the list:\n  ";
                                return deleteHeader + selected + ui.showCount(tasks.getTaskList());

                            case ("find"):
                                String search = "";
                                for (int i = 1; i < inputArr.length; i++) {
                                    search += inputArr[i];
                                    search += (i == inputArr.length - 1) ? "" : " ";
                                }
                                ArrayList<Task> foundTasks = tasks.findTask(search);

                                String foundHeader = foundTasks.isEmpty()
                                        ? "Goose didn't find anything, honk..."
                                        : "Honk! Here are the matching tasks in your list:\n";
                                String foundList = "";
                                for (int i = 0; i < foundTasks.size(); i++) {
                                    int indexNum = i + 1;
                                    String item = " " + indexNum + "." + foundTasks.get(i).toString();
                                    if (i != foundTasks.size() - 1) {
                                        item += "\n";
                                    }
                                    foundList += item;
                                }
                                return foundHeader + foundList;

                            case ("todo"):
                                storage.saveLastState(tasks.getTaskList());
                                Todo addedTodo = tasks.createTodo(inputArr);
                                storage.save(tasks.getTaskList());

                                String addTodoHeader = " Honk! Okay added the task:\n  ";
                                return addTodoHeader + addedTodo + ui.showCount(tasks.getTaskList());

                            case ("event"):
                                storage.saveLastState(tasks.getTaskList());
                                Event addedEvent = tasks.createEvent(input);
                                storage.save(tasks.getTaskList());

                                String addEventHeader = " Honk! Okay added the task:\n  ";
                                return addEventHeader + addedEvent + ui.showCount(tasks.getTaskList());

                            case ("deadline"):
                                storage.saveLastState(tasks.getTaskList());
                                Deadline addedDeadline = tasks.createDeadline(input);
                                storage.save(tasks.getTaskList());

                                String addDeadlineHeader = " Honk! Okay added the task:\n  ";
                                return addDeadlineHeader + addedDeadline + ui.showCount(tasks.getTaskList());

                            case ("undo"):
                                tasks = new TaskList(storage.undoAndLoad());
                                String undoMessage = "I have unhonked your previous command.\n " +
                                        "Use \'undo\' to undo your undo. Honk!\n\n";
                                String showList = "Honk! Here's your updated current task list: \n" + storage.loadList();
                                return undoMessage + showList;

                            default:
                                return "Honk! Something went wrong.";
                        }
                    } catch (IOException e) {
                        return "Honk! Something went wrong.";
                    } catch (GooseTaskExistenceException | GooseEmptyDescriptionException
                            | GooseIllegalFormatException e) {
                        return e.getMessage();
                    }
                } catch (GooseUnrecognisedException e) {
                    return e.getMessage();
                }
            }
        }
        return "Bye! Honk!!";
    }
}