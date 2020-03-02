import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadSavedData());
        } catch (IOException e) {
            tasks = new TaskList();
            System.out.println(e);
            ui.noExistingSaveFile();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/saved.txt").run();
    }

    public void run() {
        ui.sayHi();
        while (parser.hasNext()) {
            try {
                String input = parser.scanLine();
                if (parser.commandEquals(parser.getCommand(input), "todo")) {
                    if (!parser.hasDetails(input)) {
                        throw new EmptyDescriptionException();
                    }
                    ;
                    tasks.addTask(new Todo(parser.getInfo(input)));
                } else if (parser.commandEquals(parser.getCommand(input), "event")) {
                    if (!parser.hasDetails(input)) {
                        throw new EmptyDescriptionException();
                    }
                    ;
                    tasks.addTask(new Event(Event.getEventDesc(input.toCharArray()), Event.getEventDate(parser.
                            getInfo(input))));
                } else if (parser.commandEquals(parser.getCommand(input), "deadline")) {
                    if (!parser.hasDetails(input)) {
                        throw new EmptyDescriptionException();
                    }
                    ;
                    tasks.addTask(new Deadline(Deadline.getDesc(input.toCharArray()), Deadline.getDate(parser.
                            getInfo(input))));
                } else if (parser.commandEquals(parser.getCommand(input), "list")) {
                    tasks.showTasks();
                } else if (parser.commandEquals(parser.getCommand(input), "done")) {
                    tasks.taskDone(parser.getTaskNum(input));
                } else if (parser.commandEquals(parser.getCommand(input), "bye")) {
                    try {
                        storage.saveToFile(tasks.getTaskArrList());
                        ui.sayBye();
                        break;
                    } catch (FileNotFoundException e) {

                    }
                } else if (parser.commandEquals(parser.getCommand(input), "delete")) {
                    tasks.deleteTask(parser.getTaskNum(input));
                } else if (parser.commandEquals(parser.getCommand(input), "find")) {
                    tasks.find(parser.getInfo(input));
                } else if (parser.commandEquals(parser.getCommand(input), "view")) {
                    tasks.viewSchedule(parser.getInfo(input));
                } else {
                    throw new InvalidCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
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
        String userText = (userInput.getText());
        String dukeText = (getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, (user)),
                DialogBox.getDukeDialog(dukeText, (duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        String output = "";
        try {
            if (parser.commandEquals(parser.getCommand(input), "todo")) {
                if (!parser.hasDetails(input)) {
                    throw new EmptyDescriptionException();
                }
                ;
                Todo t = new Todo(parser.getInfo(input));
                tasks.addTask(t);
                output += "Got it. I've added this task: \n  " + t.toString() + "\n" + tasks.numOfTasks();
            } else if (parser.commandEquals(parser.getCommand(input), "event")) {
                if (!parser.hasDetails(input)) {
                    throw new EmptyDescriptionException();
                }
                ;
                Event e = new Event(Event.getEventDesc(input.toCharArray()), Event.getEventDate(parser.
                        getInfo(input)));
                tasks.addTask(e);
                output += "Got it. I've added this task: \n  " + e.toString() + "\n" + tasks.numOfTasks();
            } else if (parser.commandEquals(parser.getCommand(input), "deadline")) {
                if (!parser.hasDetails(input)) {
                    throw new EmptyDescriptionException();
                }
                ;
                Deadline d = new Deadline(Deadline.getDesc(input.toCharArray()), Deadline.getDate(parser.
                        getInfo(input)));
                tasks.addTask(d);
                output += "Got it. I've added this task: \n  " + d.toString() + "\n" + tasks.numOfTasks();
            } else if (parser.commandEquals(parser.getCommand(input), "list")) {
                output = tasks.showTasks();
            } else if (parser.commandEquals(parser.getCommand(input), "done")) {
                output = tasks.taskDone(parser.getTaskNum(input));
            } else if (parser.commandEquals(parser.getCommand(input), "bye")) {
                try {
                    output += storage.saveToFile(tasks.getTaskArrList()) + "\n" + ui.sayBye();
                } catch (FileNotFoundException e) {

                }
            } else if (parser.commandEquals(parser.getCommand(input), "delete")) {
                output += tasks.deleteTask(parser.getTaskNum(input));
            } else if (parser.commandEquals(parser.getCommand(input), "find")) {
                output += tasks.find(parser.getInfo(input));
            } else if (parser.commandEquals(parser.getCommand(input), "view")) {
                output += tasks.viewSchedule(parser.getInfo(input));
            } else {
                throw new InvalidCommandException();
            }
            return output;
        } catch (DukeException e) {
            output = e.toString();
            return output;
        }
    }
}