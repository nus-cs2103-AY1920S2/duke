/*
 * @author leslieharland
 */

package duke;


import duke.command.Operation;
import duke.storage.Storage;
import duke.task.SearchTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The Class Duke.
 */
public class Duke extends Application {
    /**
     * The i.
     */
    static int i;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/avatar.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * The storage.
     */
    private Storage storage;

    /**
     * The tasks.
     */
    private TaskList tasks;
    private TaskList temp;
    /**
     * The ui.
     */
    private Ui ui;

    /**
     * Instantiates a new duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        temp = new TaskList();

        storage = new Storage(filePath);
        try {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * this method is not called for the purpose of fulfilling the javafx tutorial
     **/
    @Override
    public void start(Stage stage) {
        //Step 1. Formatting the window to look as expected.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });


        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
       /* Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(user))
        );
        userInput.clear();

        */
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String command = "";

        String[] current = input.split(" ");
        command = current[0];
        if ((command).equalsIgnoreCase(Operation.BYE.toString())) {
            return ui.clean();
        } else if (command.equalsIgnoreCase(Operation.LIST.toString())) {
            temp.getTasks().clear();
            tasks = storage.loadTasks();
            return ui.showTasks(tasks, false);

        } else if (command.equalsIgnoreCase(Operation.TODO.toString())
                || command.equalsIgnoreCase(Operation.DEADLINE.toString())
                || command.equalsIgnoreCase(Operation.EVENT.toString())) {
            try {
                Task t = tasks.addTask(current, storage);
                return ui.taskAddSuccess(t, tasks.getSize());
            } catch (DukeException ex) {
                return ui.showMessage(Arrays.asList(ex.getMessage()));
            }

        } else if (command.equalsIgnoreCase(Operation.FIND.toString())) {
            temp = storage.findTasks(current[1]);
            return ui.showTasks(temp, true);
        } else if (command.equalsIgnoreCase(Operation.DONE.toString())) {
            int value = Integer.parseInt(current[1]);
            try {
                Task cur = tasks.get(value - 1);
                StringBuilder sb = new StringBuilder();
                if (!temp.getTasks().isEmpty()) {
                    cur = tasks.get(((SearchTask) temp.get(value - 1)).getKey() - 1);
                    value = ((SearchTask) temp.get(value - 1)).getKey();

                }
                cur.markAsDone();
                tasks.deleteTask(value, storage);
                tasks.addTask(value - 1, cur);
                for (Task t : tasks.getTasks()) {
                    sb.append(t.print() + "\n");
                }
                storage.writeToFile(sb.toString());
                return ui.taskMarkDone(cur);
            } catch (IndexOutOfBoundsException ex) {
                return ui.taskNumberError();
            }

        } else if (command.equalsIgnoreCase(Operation.DELETE.toString())) {
            try {
                int keyToDelete = Integer.parseInt(current[1]);
                if (!temp.getTasks().isEmpty()) {
                    keyToDelete = ((SearchTask) temp.get(keyToDelete - 1)).getKey();
                }
                temp.getTasks().clear();
                Task cur =  tasks.deleteTask(keyToDelete, storage);
                return ui.taskRemoveSuccess(cur, tasks.getSize());
            } catch (IndexOutOfBoundsException ex) {
                return ui.taskNumberError();
            }

        } else {
            try {
                throw new DukeException(
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception ex) {
                return ui.showMessage(Arrays.asList(ex.getMessage()));
            }
        }

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

}
