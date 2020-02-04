import com.sun.source.tree.Scope;
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
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {

    }

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromSaveFile());

            // Since we can successfully load it, we save all in TaskList
            storage.writeTasks(tasks);
        } catch (FileNotFoundException e) {
            // Load an empty one
            tasks = new TaskList();
            Ui.printLine();
            Ui.indent("It seems that there is no save file in: " + filePath);
            Ui.printLine();
        }
    }

    public void run() {
        Ui.greet();
        while (Parser.stillHasInputs()) {
            try {
                String input = Parser.readLine();
                if (Parser.commandEquals("bye", input)) {
                    Ui.sayGoodbye();
                    break;
                } else if (Parser.commandEquals("list", input)) {
                    Ui.displayList(tasks);
                } else if (Parser.commandEquals("done", input)) {
                    int taskNo = Parser.getTaskNo(input);
                    tasks.markTaskAsDone(taskNo);
                } else if (Parser.commandEquals("delete", input)) {
                    int taskNo = Parser.getTaskNo(input);
                    tasks.deleteTask(taskNo);
                } else if (Parser.commandEquals("deadline", input)) {
                    if (Parser.hasNoArgs(input)) throw new EmptyDescriptionException();
                    if (tasks.isFull()) throw new TooManyTasksException();

                    tasks.addDeadline(Parser.getArgs(input));
                } else if (Parser.commandEquals("event", input)) {
                    if (Parser.hasNoArgs(input)) throw new EmptyDescriptionException();
                    if (tasks.isFull()) throw new TooManyTasksException();

                    tasks.addEvent(Parser.getArgs(input));
                } else if (Parser.commandEquals("todo", input)) {
                    if (Parser.hasNoArgs(input)) throw new EmptyDescriptionException();
                    if (tasks.isFull()) throw new TooManyTasksException();

                    tasks.addTodo(Parser.getArgs(input));
                } else if (Parser.commandEquals("find", input)) {
                    if (Parser.hasNoArgs(input)) throw new EmptyDescriptionException();

                    tasks.find(Parser.getArgs(input));
                } else {
                    throw new UnknownCommandException();
                }

                // Rewrites the entire file for every update you make here
                // Probably O(n^2) time where n is the number of tasks but this is the simplest change we can make
                storage.writeTasks(tasks);
            } catch (DukeException e) {
                Ui.printLine();
                Ui.indent(e.toString());
                Ui.printLine();
            } catch (DateTimeParseException e) {
                Ui.printLine();
                Ui.indent("It seems that you have entered a format we don't understand. ");
                Ui.indent("Please use the YYYY-MM-DD format.");
                Ui.printLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
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

        // Step 2. Formatting the window to look as expected
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

        // Need to import javafx.scene.layout.Region for this
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added

    }
}
