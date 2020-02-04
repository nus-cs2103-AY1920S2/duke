import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    /**
     *Initialises everything needed by Duke.
     *
     * @param filePath path used to access tasks in Hard Disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.getTasksFromFile(filePath));
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Simulates running of Duke.
     */
    public void run() {
        ui.greetings();
        String input = ui.getInput();
        while (!input.equalsIgnoreCase("Bye")) {
            try {
                ui.horizontalLine();
                if (input.equalsIgnoreCase("list")) {
                    tasks.list();
                } else if (input.startsWith("done")) {
                    int n = Integer.parseInt(parser.parse(input)[1]);
                    tasks.done(n);
                } else if (input.startsWith("delete")) {
                    int n = Integer.parseInt(parser.parse(input)[1]);
                    tasks.delete(n);
                } else if (input.startsWith("find")) {
                    String str = parser.parse(input)[1];
                    tasks.find(str);
                } else {
                    String[] splitInput = parser.parse(input);
                    if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                        try {
                            tasks.add(splitInput[0], splitInput[1]);
                        } catch (ArrayIndexOutOfBoundsException arr) {
                            throw new EmptyTaskException("");
                        }
                    } else {
                        throw new InvalidRequestException();
                    }
                }
                ui.horizontalLine();
                input = ui.getInput();
            } catch (EmptyTaskException empty) {
                System.out.println("\t" + empty.toString());
                ui.horizontalLine();
                input = ui.getInput();
            } catch (InvalidRequestException invalid) {
                System.out.println("\t" + invalid.toString());
                ui.horizontalLine();
                input = ui.getInput();
            }
        }
        ui.horizontalLine();
        System.out.println("\tBye. Hope to see you again soon!");
        try {
            storage.addTasksToFile(tasks.tasks);
        } catch (IOException e) {
            System.out.println("Error in saving to file");
        }
        ui.horizontalLine();
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

        // more code to be added here later
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
