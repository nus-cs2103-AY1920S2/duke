import java.io.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label; // unused???
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// no longer public class Duke extends Application

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    //private Image user = new Image(this.getClass().getResourceAsStream("/images/typhon.png"));
    //private Image duke = new Image(this.getClass().getResourceAsStream("/images/operator.png"));

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String run(String input) {
        // ui.printGreeting(); // THIS ONE HOW TO APPLY????????

        //String input = ui.scanInput();
        while (!input.toLowerCase().equals("bye")) {
            Parser parser = new Parser(input);
            String command = parser.processCommand();
            if (command.toLowerCase().equals("list")) {
                if (tasks.getTaskListSize() == 0) {
                    return ui.printEmptyListMessage();
                }
                else {
                    return ui.printList(tasks);
                }
            } else if (command.toLowerCase().equals("done")) {
                int index = parser.processIndex();
                tasks.get(index).updateIsCompleted(true);
                return ui.printMarkedAsDone(tasks.get(index));
            } else if (command.toLowerCase().equals("delete")) {
                int index = parser.processIndex();
                String lst = ui.printDelete();

                String completion_symbol = tasks.get(index).getCompletionStatusAsString();
                String task_type = tasks.get(index).getTaskType();
                String description = tasks.get(index).getDescription();
                String line = completion_symbol + " | [" + task_type + "] | " + description + "\n";
                if (task_type.equals("D")) {
                    String date = tasks.get(index).getDate();
                    line = completion_symbol + " | [" + task_type + "] | " + description + " | " + date + "\n";
                }
                else if (task_type.equals("E")) {
                    String date = tasks.get(index).getDate();
                    line = completion_symbol + " | [" + task_type + "] | " + description + " | " + date + "\n";
                }
                tasks.deleteTask(index);
                return lst + line;

            } else if (command.toLowerCase().equals("find")) {
                String keyword = parser.processKeyword();
                return tasks.findTask(keyword);
            } else if (command.toLowerCase().equals("todo")) {
                try {
                    String description = parser.processDescriptionForTodo();
                    tasks.addTodo(description);
                    String lst = ui.printAdd();

                    String completion_symbol = "[\u2718]";
                    String task_type = "[T]";
                    lst = lst + completion_symbol + " " + task_type + " " + description + "\n";
                    lst = lst + ui.printNumTasksInList(tasks);
                    return lst;
                } catch (DukeException exception) {
                    return ui.printExceptionMessage(exception);
                }
            } else if (command.toLowerCase().equals("deadline")) {
                try {
                    String description = parser.processDescriptionForEventOrDeadline();
                    LocalDate date = parser.processDateForDeadline();
                    tasks.addDeadline(description, date);
                    String lst = ui.printAdd();

                    String completion_symbol = "[\u2718]";
                    String task_type = "[D]";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = date.format(formatter);
                    lst = lst + completion_symbol + " " + task_type + " " + description + "(by: " + formattedDate + ")\n";
                    lst = lst + ui.printNumTasksInList(tasks);
                    return lst;
                }
                catch (DukeException exception){
                    return ui.printExceptionMessage(exception);
                }

            } else if (command.toLowerCase().equals("event")) {
                try {
                    String description = parser.processDescriptionForEventOrDeadline();
                    LocalDate date = parser.processDateForEvent();
                    tasks.addEvent(description, date);
                    String lst = ui.printAdd();

                    String completion_symbol = "[\u2718]";
                    String task_type = "[E]";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = date.format(formatter);
                    lst = lst + completion_symbol + " " + task_type + " " + description + "(at: " + formattedDate + ")\n";
                    lst = lst + ui.printNumTasksInList(tasks);
                    return lst;
                }
                catch (DukeException exception) {
                    return ui.printExceptionMessage(exception);
                }
            } else {
                //return "Oops! I did not understand that.";
                return "Good to see you, Mr. Yu!";
            }
            //input = ui.scanInput(); // REDUNDANT
        }

        storage.updateFile(tasks); // ned to write back to the file
        return ui.printGoodbye();
    }

    // I think this can delete completely???
    /*
    public static void main(String[] args) {
        new Duke().run();
    } */

    // I think all this also can delete uh.
    /*
    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Recycle"); // AKA send

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.00);
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return A Label with the specified text that has word wrap enabled.
     */
    /*
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getUserDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    } */

    /**
     * Gets response from run().
     * Should I change it back to private? I did public because there were problems in MainWindow.java
     */
    public String getResponse(String input) {
        return this.run(input);
    }
}