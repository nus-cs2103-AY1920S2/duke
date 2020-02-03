import task.Deadline;
import task.Event;
import task.Todo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * Represents a Duke bot. This is also the main class of duke project.
 */
public class Duke extends Application {
    public static String taskData = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/IMG_0996.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/IMG_1032.jpeg"));

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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
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
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * constructs a Duke bot instance.
     * @param filePath the file path where the bot stores its data collected from the user in.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.taskList = new TaskList(storage.start());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public Duke() {

    }

    /**
     * processes different requests which is decided by the first token of the message user entered.
     * @param str the first token of the message user entered.
     * @throws InvalidKeyException if the first token entered is not a valid command.
     * @throws IllegalArgumentException if the tokens entered after the first token are not correctly formatted.
     * @throws EmptyDescriptionException if the user only entered the first token.
     */
    public void processRequest(String str)
            throws InvalidKeyException, IllegalArgumentException, EmptyDescriptionException {

        if (str.equals("")) {
            throw new InvalidKeyException("Try to say something to me.");
        }

        StringTokenizer st = new StringTokenizer(str);
        String first = st.nextToken(" ");

        switch (parser.getMessage(first)) {
        case DONE:
            parser.checkDescription(str, "done".length());
            taskList.markDone(str);
            break;

        case DELETE:
            parser.checkDescription(str, "delete".length());
            taskList.delete(str);
            break;

        case FIND:
            parser.checkDescription(str, "find".length());
            taskList.find(str);
            break;

        case TODO:
            parser.checkDescription(str, "todo".length());
            Todo td = new Todo(st.nextToken("").substring(1));
            taskList.addTask(td);
            break;

        case DEADLINE:
            parser.checkDescription(str, "deadline".length());
            String[] strings = parser.stringSplitting(st);
            Deadline ddl = new Deadline(strings[0], strings[1]);
            taskList.addTask(ddl);
            break;

        case EVENT:
            parser.checkDescription(str, "event".length());
            String[] strings2 = parser.stringSplitting(st);
            Event ev = new Event(strings2[0], strings2[1]);
            taskList.addTask(ev);
            break;

        default:
            throw new InvalidKeyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * sets up the bot, shows greeting messages and then the user is able to interact with the bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isexiting = false;

        this.ui.greet(taskList);

        String str = sc.nextLine();
        while (!isexiting) {
            //check if the user want to exit
            while (!str.equals("bye")) {
                if (str.equals("list")) {
                    //print out the whole list
                    this.ui.gettingList(taskList);
                } else {
                    //update the list of tasks
                    try {
                        processRequest(str);
                        this.storage.rewriteFile(taskList);
                    } catch (InvalidKeyException | IllegalArgumentException | EmptyDescriptionException
                            | IOException e) {
                        System.err.println(e);
                    }
                }
                str = sc.nextLine();
            }

            //exit confirmation
            ui.bye();

            if (sc.nextLine().equals("y")) {
                //confirm to leave and leaving message
                isexiting = true;
                ui.typeSetting("    Bye. Hope to see you again soon! \uD83D\uDE1E\n");
            } else {
                //not leaving and continue to interact with Bob
                ui.typeSetting("    I know you are the best! \uD83D\uDE06\n    Then, what's next?\n");
                str = sc.nextLine();
            }
        }
    }

    /**
     * runs the whole program.
     */
    public static void main(String[] args) {
        new Duke(taskData).run();
    }
}
