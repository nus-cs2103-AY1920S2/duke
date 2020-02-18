import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * The Duke program implements an application that is able to keep track of a task list. A user can populate the list
 * with tasks inputted through the standard output, mainly Todo tasks, Deadline tasks and Event tasks, or delete tasks
 * from the list.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DukeBot.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    Parser parser;

    /**
     * Creates a Ui object to deal with user interaction , a Storage object to deal with loading or saving tasks and a
     * new TaskList object, loaded with the file storing the task list, if the file exists.
     *
     * @param filePath location where file which contains task list is found or created
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException exception) {
            ui.printLoadingError();
            tasks = new TaskList();
            storage.updateTaskList(tasks.getList());
        }
        parser = new Parser(tasks);
    }

    @Override
    public void start(Stage stage) {

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

        // formatting the window to look as expected
        stage.setTitle("DukeBot");
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

        // add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        // scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Generates a response to user input.
     *
     * @param input command by user.
     * @return String that parser returns after parsing user input.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

    public Storage getStorage() {
        assert storage != null: "Storage not initialised properly";
        return storage;
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(parser.parse(command));
            command = sc.nextLine();
        }
        ui.printExitLine();
    }

    // able to run from command line
    public static void main(String[] args) {
        new Duke("./src/main/data/duke.txt").run();
    }
}
