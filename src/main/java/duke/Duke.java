package duke;

import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;


/**
 * This class is the main driving chatbot.
 **/
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    //private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    //private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Constructs a Duke object, it takes in a file path
     * to obtain information from the file.
     * @param filePath The file path to the file to be modified
     **/
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public Duke() {
    }

    /**
     * Calls the run method to run the chatbot.
     **/
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
     * Runs Duke chatbot.
     **/
    public void run() {
        ui.greetUser();
        processInput();
    }

    /**
     * Processes the input from user.
     **/
    public void processInput() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        Parser curr = new Parser(next);
        while (!curr.getTaskType().equals("bye")) {
            if (curr.getTaskType().equals("list")) {
                ui.printList(tasks);
            } else if (curr.getTaskType().equals("find")) {
                String finding = curr.getSecond();
                TaskList filtered = tasks.filter(finding);
                ui.printFindings(filtered);
            } else if (curr.getTaskType().equals("done")) {
                try {
                    Integer taskNumber = Integer.valueOf(curr.getSecond());
                    Task updatedTask = tasks.get(taskNumber - 1).setDone();
                    tasks.set(taskNumber - 1, updatedTask);
                    storage.updateDrive(updatedTask);
                    ui.printDone(updatedTask);
                } catch (IndexOutOfBoundsException e) {
                    ui.printException(new DukeException(
                            " ☹ OOPS!!! The description of a done cannot be empty."));
                }
            } else if (curr.getTaskType().equals("delete")) {
                try {
                    Integer taskNumber = Integer.valueOf(curr.getSecond());
                    Task removedTask = tasks.get(taskNumber - 1);
                    tasks.remove(removedTask);
                    storage.deleteDrive(taskNumber);
                    ui.printRemove(removedTask, tasks.size());
                } catch (IndexOutOfBoundsException e) {
                    ui.printException(new DukeException(
                            " ☹ OOPS!!! The description of a delete cannot be empty."));
                }
            } else {
                Task newTask;
                String taskName;
                if (curr.getTaskType().equals("todo")) {
                    try {
                        taskName = curr.getThird();
                        newTask = new ToDo(taskName);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printException(new DukeException(
                                " ☹ OOPS!!! The description of a todo cannot be empty."));
                    }
                } else if (curr.getTaskType().equals("deadline")) {
                    try {
                        taskName = curr.getThird();
                        LocalDateTime time = curr.getDate();
                        newTask = new Deadline(taskName, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printException(new DukeException(
                                " ☹ OOPS!!! The description of a deadline cannot be empty."));
                    }
                } else if (next.trim().split(" ")[0].equals("event")) {
                    try {
                        taskName = curr.getThird();
                        LocalDateTime time = curr.getDate();
                        newTask = new Event(taskName, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printException(new DukeException(
                                " ☹ OOPS!!! The description of an event cannot be empty."));
                    }
                } else {
                    ui.printException(new DukeException(
                            " ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
                }
            }
            next = sc.nextLine();
            curr = new Parser(next);
        }
        ui.printBye();
    }

    /**
     * Adds a task to the list.
     **/
    void addTask(Task newTask) {
        tasks.add(newTask);
        storage.updateDrive(newTask);
        ui.printTask(newTask, tasks.size());
    }
/*
    @Override
    public void start(Stage stage) {
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

        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(399, 546);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(343.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }
*/
/*    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
/*    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
/*    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }*/


    protected String getResponse(String text) {
        return "Duke heard: " + text;
    }


}
