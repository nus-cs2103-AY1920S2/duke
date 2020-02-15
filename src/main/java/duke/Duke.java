package duke;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

/**
Represents the Duke object with which the user interacts.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a Duke Instance with given filePath
     * @param filePath
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            tasks = new TaskList(storage.getPreviousTasks(filePath));
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("data/data.txt");
    }

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Runs the duke object so that it can be used
     */
    public void run() {
        ui.welcomeMessage();
        while(true) {
            String input = ui.getInput();
            if(input.equalsIgnoreCase("bye")) {
                ui.byeMessage();
                try {
                    storage.fillFileWithTasks(tasks.getTaskList());
                } catch (IOException e) {
                    ui.promptUser("Error saving to file. Please if 'data.txt' is present in '/data/");
                }
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(parser.parse(input, 2)[1]); //accept second argument from command line
                tasks.markDone(index);
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(parser.parse(input, 2)[1]); //accept second argument from command line
                tasks.deleteTaskByIndex(index);
            } else if (input.equalsIgnoreCase("list")) {
                tasks.printList();
            } else if (input.startsWith("find")){
                String[] parsedInput = parser.parse(input, 2);
                tasks.find(parsedInput[1]);
            } else {
                String[] parsedInput = parser.parse(input, 2);
                if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    try {
                        tasks.addTask(parsedInput[0], parsedInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.promptUser("OOPS, task description cannot be empty");
                    }
                } else {
                    Ui.promptUser("OOPS, I don't understand this input. Please use a known command and try again");
                }
            }
        }
    }

    /**
     * Starts the duke application
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/data.txt");
        duke.run();
    }
}
