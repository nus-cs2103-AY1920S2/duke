import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
//import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.event.EventHandler;
//import javafx.geometry.Point2D;
import javafx.scene.Scene;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Represents main body for Duke to run.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image goose = new Image(this.getClass().getResourceAsStream("/images/DaGoose.png"));
//    private Circle clip = new Circle(50);

    /**
     * Constructor for Duke.
     *
     * @param listPath Relative file path for where the task list is stored
     * @param arrayPath Relative file path for where the task array is stored
     */
    public Duke(String listPath, String arrayPath) {
        ui = new Ui();
        storage = new Storage(listPath, arrayPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {

    }

    /**
     * Sets up and runs Duke to begin accepting user commands. Send 'bye' to close the program.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        String input;
        while (!isBye) {
            input = scanner.nextLine();
            isBye = Parser.isBye(input);

            try {
                String command = Parser.parseCommand(input);
                String[] inputArr = input.split(" ");
                try {
                    if (command.equals("list")) {
                        ui.showList(storage.loadList());
                    } else if (command.equals("done")) {
                        int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                        Task selected = tasks.getTaskList().get(taskIndex);
                        tasks.markDone(taskIndex);
                        ui.showDoneTask(selected);
                        storage.save(tasks.getTaskList());

                    } else if (command.equals("delete")) {
                        int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                        Task selected = tasks.getTaskList().get(taskIndex);
                        tasks.deleteTask(taskIndex);
                        ui.showDeleteTask(selected, tasks.getTaskList());
                        storage.save(tasks.getTaskList());

                    } else if (command.equals("find")) {
                        String search = "";
                        for (int i = 1; i < inputArr.length; i++) {
                            search += inputArr[i];
                            search += (i == inputArr.length - 1) ? "" : " ";
                        }
                        ArrayList<Task> foundTasks = tasks.findTask(search);
                        ui.showFoundTasks(foundTasks);

                    } else {
                        if (command.equals("todo")) {
                            Todo added = tasks.createTodo(inputArr);
                            ui.showAddTask(added, tasks.getTaskList());
                            storage.save(tasks.getTaskList());

                        } else if (command.equals("event")) {
                            Event added = tasks.createEvent(input);
                            ui.showAddTask(added, tasks.getTaskList());
                            storage.save(tasks.getTaskList());

                        } else if (command.equals("deadline")) {
                            Deadline added = tasks.createDeadline(input);
                            ui.showAddTask(added, tasks.getTaskList());
                            storage.save(tasks.getTaskList());
                        }
                    }
                } catch (IOException e) {
                    ui.showError("Honk! Something went wrong.");
                } catch (GooseTaskExistenceException | GooseEmptyDescriptionException | GooseIllegalFormatException e) {
                    ui.showError(e.getMessage());
                }
            } catch (GooseUnrecognisedException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showBye();
    }

    @Override
    public void start(Stage stage) {
//        // The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        // Formatting the window
//        stage.setTitle("Goose");
//        stage.setResizable(true);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        // Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
    }
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label gooseText = new Label(getResponse(userInput.getText()));
//
//        ImageView userImage = new ImageView(user);
//        ImageView gooseImage = new ImageView(goose);
////        userImage.setClip(clip);
////        gooseImage.setClip(clip);
//
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, userImage),
//                DialogBox.getGooseDialog(gooseText, gooseImage)
//        );
//        userInput.clear();
//    }

    public static void main(String[] args) {
        new Duke("duke.txt", "mainList.txt").run();
    }
}
