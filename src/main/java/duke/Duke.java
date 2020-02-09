package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Node;
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
 * Duke program.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    private TaskList tasks;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /**
     * Constructor.
     *
     * @param filePath path to file
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            UI.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke () {
        this("./data/duke.txt");
    }

    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt").run();
    }

    /**
     * The run method.
     *
     * @throws IOException exception
     */
    public void run() throws IOException {
        TaskList arr = tasks;
        UI.say("Hello I am [AKSHAY]!\nHow may I help you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] c = input.split(" ", 2);
            switch (c[0]) {
            case ("list"):
                UI.list(arr);
                break;
            case ("done"):
                Task curr = arr.get(Integer.parseInt(c[1]) - 1);
                curr.mark();
                UI.done(curr);
                break;
            case ("todo"):
                try {
                    Task todo = new Todo(c[1]);
                    arr.add(todo);
                    UI.added(todo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.say("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case ("deadline"):
                String[] dl = c[1].split("/by", 2);
                Task d = new Deadline(dl[0], dl[1].trim());
                arr.add(d);
                UI.added(d);
                break;
            case ("event"):
                String[] ev = c[1].split("/at", 2);
                Task e = new Event(ev[0], ev[1].trim());
                arr.add(e);
                UI.added(e);
                break;
            case ("delete"):
                try {
                    Task del = arr.get(Integer.parseInt(c[1]) - 1);
                    arr.remove(Integer.parseInt(c[1]) - 1);
                    UI.delete(del);
                } catch (Exception i) {
                    UI.say("Failed to delete item!!!");
                }
                break;
            case ("find"):
                UI.results(tasks.search(c[1]));
                break;
            default:
                try {
                    throw new DukeException();
                } catch (DukeException de) {
                    UI.say("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            storage.save(arr.toArr());
            input = sc.nextLine();
        }
        UI.goodbye();
    }

    @Override
    public void start(Stage stage) throws Exception {
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
}