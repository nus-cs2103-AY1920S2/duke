import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class that drives the code to run the Duke bot.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("images/DaDuke.png"));
    private static final String filepath = "data/duke.txt";


    /**
     * Duke class constructor for JavaFX.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.readFile());
        Scanner sc = new Scanner(System.in);
        parser = new Parser(sc);
    }

    /**
     * Duke class constructor that creates a new instance of a Duke bot.
     *
     * @param filePath Path of file for instance of Storage class to be created.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFile());
        Scanner sc = new Scanner(System.in);
        parser = new Parser(sc);
    }

    /**
     * Main method that drives the Duke bot.
     */
    public void run() {
        ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc);

        String command = parser.readCommand();

        try {
            while (!parser.getCommandType(command).equals("bye")) {

                String commandType = parser.getCommandType(command);

                if (commandType.equals("list")) {
                    tasks.printList();
                    command = parser.readCommand();
                } else if (commandType.equals("find")) {
                    String findString = parser.getFind(command);
                    TaskList matchedTask = tasks.find(findString);
                    ui.printFind(matchedTask);
                    command = parser.readCommand();
                } else if (commandType.equals("done")) {
                    int taskNo = parser.getTaskNo(command);
                    try {
                        tasks.setDone(taskNo);
                        ui.printDone(tasks.getTask(taskNo));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("There is no task " + (taskNo + 1) + " in the list.");
                    }
                    command = parser.readCommand();
                } else if (commandType.equals("delete")) {
                    int taskNo = parser.getTaskNo(command);
                    Task task = tasks.getTask(taskNo);
                    ui.printDelete(task, tasks.getSize() - 1);
                    tasks.deleteTask(taskNo);
                    storage.saveFile(tasks.getTaskList());
                    command = parser.readCommand();
                } else if (commandType.equals("deadline")) {
                    try {
                        Deadline deadline = parser.getDeadline(command);
                        tasks.addTask(deadline);
                        storage.saveFile(tasks.getTaskList());
                        ui.printAdd(deadline, tasks.getSize());
                    } catch (DukeException | ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    command = parser.readCommand();
                } else if (commandType.equals("event")) {
                    try {
                        Event event = parser.getEvent(command);
                        tasks.addTask(event);
                        storage.saveFile(tasks.getTaskList());
                        ui.printAdd(event, tasks.getSize());
                    } catch (DukeException | ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    command = parser.readCommand();
                } else if (commandType.equals("todo")) {
                    try {
                        ToDo toDo = parser.getToDo(command);
                        tasks.addTask(toDo);
                        storage.saveFile(tasks.getTaskList());
                        ui.printAdd(toDo, tasks.getSize());
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    command = parser.readCommand();
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            command = parser.readCommand();
        }

        ui.printBye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Method to launch JavaFX.
     *
     * @param stage Primary stage that JavaFX provides.
     */
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

        //Step 3. Add functionality to handle user input.
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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Gets the response of Duke based on the command given from the user.
     * @param command String input from the user.
     */
    protected String getResponse(String command) {
        //return "Duke heard " + input;
        try {
            String commandType = parser.getCommandType(command);
            switch (commandType) {
            case "list":
                return tasks.printList();
            case "find":
                String findString = parser.getFind(command);
                TaskList matchedTask = tasks.find(findString);
                return ui.printFind(matchedTask);
            case "done": {
                int taskNo = parser.getTaskNo(command);
                try {
                    tasks.setDone(taskNo);
                    return ui.printDone(tasks.getTask(taskNo));
                } catch (IndexOutOfBoundsException e) {
                    return ("There is no task " + (taskNo + 1) + " in the list.");
                }
            }
            case "delete": {
                int taskNo = parser.getTaskNo(command);
                Task task = tasks.getTask(taskNo);
                tasks.deleteTask(taskNo);
                storage.saveFile(tasks.getTaskList());
                return ui.printDelete(task, tasks.getSize());
            }
            case "deadline":
                try {
                    Deadline deadline = parser.getDeadline(command);
                    tasks.addTask(deadline);
                    storage.saveFile(tasks.getTaskList());
                    return ui.printAdd(deadline, tasks.getSize());
                } catch (DukeException | ParseException e) {
                    return (e.getMessage());
                }
            case "event":
                try {
                    Event event = parser.getEvent(command);
                    tasks.addTask(event);
                    storage.saveFile(tasks.getTaskList());
                    return ui.printAdd(event, tasks.getSize());
                } catch (DukeException | ParseException e) {
                    return (e.getMessage());
                }
            case "todo":
                try {
                    ToDo toDo = parser.getToDo(command);
                    tasks.addTask(toDo);
                    storage.saveFile(tasks.getTaskList());
                    return ui.printAdd(toDo, tasks.getSize());
                } catch (DukeException e) {
                    return (e.getMessage());
                }
            case "bye":
                return ui.printBye();
            default:
                return "Invalid command, please try again.";
            }
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }
}
