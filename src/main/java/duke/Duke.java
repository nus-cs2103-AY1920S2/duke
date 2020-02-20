package duke;

import approxsearch.Search;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.IOException;

public class Duke extends Application {

    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Stage stage;

    public static void main(String[] args) {
        // ...
    }

    public Duke(TaskList tasks, Stage stage) {
        this.tasks = tasks;
        this.stage = stage;
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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
        stage.setTitle("duke.Duke");
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

        // more code to be added here later

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add.
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
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
//        return "duke.Duke heard: " + input;
        Parser parse = new Parser(input, false);

        int index;
        String result = "";

        switch (parse.getCommand()) {
        case LIST:
            result += "Here are the tasks in your list: \n";
            for (int i = 0; i < tasks.size(); i++) {
                result += (String.format("%d.%s\n", i + 1, tasks.get(i)));
            }

            return result;
        case DONE:
            index = parse.getIndex();
            tasks.doTask(index);


            result += "Nice! I've marked this task as done: \n";
            result += (" " + tasks.get(index) + "\n");
            try {
                Storage.doTask(index);
            } catch (IOException e) {
                return e.getMessage();
            }

            return result;
        case DELETE:
            index = parse.getIndex();

            result += "Noted. I've removed this task: \n";
            result += " " + tasks.remove(index) + "\n";

            try {
                Storage.deleteTask(index);
            } catch (IOException e) {
                return e.getMessage();
            }

            return result;
        case FIND:
            try {
                String searchTerm = parse.getSearchTerm();
                Search search = new Search(tasks);

                result += "Here are the matching tasks in your list:\n";
                TaskList searchResults = search.search(searchTerm);

                int number = 1;
                for (int i = 0; i < searchResults.size(); i++) {
                    result += String.format("%d.%s\n", number++, searchResults.get(i));
                }

                return result;
            } catch (IndexOutOfBoundsException e) {
                return ("☹ OOPS!!! The search term of a "
                        + parse.getCommandString() + " cannot be empty.");
            }
        case TODO:
        case DEADLINE:
        case EVENT:
            try {
                Task task = parse.createTask();
                tasks.add(task);

                result += ("Got it. I've added this task: \n");
                result += (" " + task + "\n");
                result += (String.format("Now you have %d tasks in the list.\n", tasks.size()));

                try {
                    Storage.addTask(input);
                } catch (IOException e) {
                    return e.getMessage();
                }

                return result;
            } catch (IndexOutOfBoundsException e) {
                return ("☹ OOPS!!! The description of a "
                        + parse.getCommandString() + " cannot be empty.");
            }
        case BYE:
            stage.close();
            return "";
        default:
            return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }


}