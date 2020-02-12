package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke is a task-management system. It accept user commands to add/delete tasks, mark tasks as done and list all tasks.
 * Upon exit, tasks will be saved and kept in a file for the next run.
 */
public class Duke extends Application {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke object. An attempt will be made to load in tasks from the file specified. If the file
     * does not exist or cannot be accessed, an empty task list will be loaded.
     */
    public Duke() {
        Path filePath = Paths.get("data", "duke.txt");
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
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

        // more code to be added here later
        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke - Task Manager");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Part 2
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked(mouseEvent -> {
            handleUserInput();
        });

        userInput.setOnAction(actionEvent -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(userInput.getText(), duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Parser instruction = new Parser(input);
            Command command = instruction.getCommand();

            switch (command) {
                case BYE:
                    break;
                case DONE:
                    int doneTaskNum = Integer.parseInt(instruction.getParameter());
                    tasks.setAsDone(doneTaskNum);
                    return ui.getDoneTask(tasks.getTask(doneTaskNum));
                case TODO:
                    Task newToDo = new ToDo(instruction.getDescription());
                    tasks.addToTasks(newToDo);
                    return ui.getAddedTask(tasks, newToDo);
                case DEADLINE:
                    Task newDeadline = new Deadline(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newDeadline);
                    return ui.getAddedTask(tasks, newDeadline);
                case EVENT:
                    Task newEvent = new Event(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newEvent);
                    return ui.getAddedTask(tasks, newEvent);
                case LIST:
                    return ui.getTaskList(tasks);
                case DELETE:
                    int delTaskNum = Integer.parseInt(instruction.getParameter());
                    Task toBeDeleted = tasks.getTask(delTaskNum);
                    tasks.deleteFromTasks(delTaskNum);
                    return ui.getDeletedTask(tasks, toBeDeleted);
                case FIND:
                    String searchTerm = instruction.getParameter();
                    return ui.getFoundTasks(tasks.findTasks(searchTerm));
                case ARCHIVE:
                    Path archiveFilePath = Paths.get("data", "dukeArchive.txt");
                    storage.archive(tasks, archiveFilePath);
                    tasks.clean();
                    return ui.getArchiveMessage(tasks);
                default:
                    ;
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
        storage.update(tasks);
        return ui.getExitMessage();
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }

    /**
     * Starts Duke. User will be able to input commands to perform actions.
     */
    public void run() {
        ui.showGreeting();

        loop:
        while (true) {
            try {
                String input = ui.getInput();
                Parser instruction = new Parser(input);
                Command command = instruction.getCommand();

                switch (command) {
                case BYE:
                    break loop;
                case DONE:
                    int doneTaskNum = Integer.parseInt(instruction.getParameter());
                    tasks.setAsDone(doneTaskNum);
                    ui.showDoneTask(tasks.getTask(doneTaskNum));
                    break;
                case TODO:
                    Task newToDo = new ToDo(instruction.getDescription());
                    tasks.addToTasks(newToDo);
                    ui.showAddedTask(tasks, newToDo);
                    break;
                case DEADLINE:
                    Task newDeadline = new Deadline(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newDeadline);
                    ui.showAddedTask(tasks, newDeadline);
                    break;
                case EVENT:
                    Task newEvent = new Event(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newEvent);
                    ui.showAddedTask(tasks, newEvent);
                    break;
                case LIST:
                    ui.showTaskList(tasks);
                    break;
                case DELETE:
                    int delTaskNum = Integer.parseInt(instruction.getParameter());
                    Task toBeDeleted = tasks.getTask(delTaskNum);
                    tasks.deleteFromTasks(delTaskNum);
                    ui.showDeletedTask(tasks, toBeDeleted);
                    break;
                case FIND:
                    String searchTerm = instruction.getParameter();
                    ui.showFoundTasks(tasks.findTasks(searchTerm));
                    break;
                case ARCHIVE:
                    Path archiveFilePath = Paths.get("data", "dukeArchive.txt");
                    storage.archive(tasks, archiveFilePath);
                    ui.showArchiveMessage(tasks);
                    tasks.clean();
                    break;
                default:
                    ;
                }

            } catch (DukeException e) {
                ui.showError(e);
                System.out.print("> ");
            }
        }
        storage.update(tasks);
        ui.showExitMessage();
    }
}
