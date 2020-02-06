import java.util.Scanner;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represent the main driving class Duke.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("DaDuke.png"));

    protected Ui ui;
    protected Storage storage;
    protected Tasklist tasklist;

    /**
     * Constructor of Duke.
     * Initialise Ui, Storage and Tasklist
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./list.txt");
        this.tasklist = new Tasklist(storage.readFile());
    }

    /**
     * The main logic of the chatbot.
     */
    public void run() {
        this.ui.printIntro();
        Scanner myScanner = new Scanner(System.in);

        while (true) {
            String word = myScanner.nextLine();
            try {
                String[] parsed = TextParser.myFirstParser(word);
                String keyword = parsed[0];
                if (keyword.equals("bye")) {
                    this.storage.writeFile(this.tasklist.mylist);
                    this.ui.printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (keyword.equals("list")) {
                    this.ui.printMessage("Here are the task in your list");
                    this.tasklist.printList();
                } else if (keyword.equals("done")) {
                    int taskNumber = Integer.valueOf(parsed[1]);
                    this.tasklist.markDone(taskNumber);
                    this.ui.printMessage("Nice! I've marked this task as done:");
                    this.ui.printMessage("" + taskNumber + ". " + this.tasklist.getTask(taskNumber));
                } else if (keyword.equals("delete")) {
                    int taskNumber = Integer.valueOf(parsed[1]);
                    this.ui.printMessage("Noted. I've removed this task");
                    this.ui.printMessage("" + this.tasklist.getTask(taskNumber));
                    this.tasklist.removeTask(taskNumber);
                    this.ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
                } else if (keyword.equals("find")) {
                    this.tasklist.findKeyword(parsed[1]);
                } else if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {
                    if (parsed.length <= 1) {
                        throw new DukeException("I think u need more arguments");
                    } else {
                        String word2 = parsed[1];
                        String[] parsed2 = TextParser.mySecondParser(word2);
                        if (keyword.equals("todo")) {
                            this.tasklist.addTask(new Todo(parsed2[0]));
                        } else if (keyword.equals("deadline")) {
                            this.tasklist.addTask(new Deadline(parsed2[0], LocalDate.parse(parsed2[1])));
                        } else if (keyword.equals("event")) {
                            this.tasklist.addTask(new Event(parsed2[0], LocalDate.parse(parsed2[1])));
                        }

                        this.ui.printMessage("Got it. I 've added this task:");
                        this.ui.printMessage("" + this.tasklist.getTask(this.tasklist.getSize()));
                        this.ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
                    }
                } else {
                    throw new DukeException("I DK how to process this -> " + word);
                }
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            } finally {
                this.ui.printLine();
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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


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

     /*  //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
    */
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
     * The main method.
     * @param args argument
     */
  /* public static void main(String[] args) {
        new Duke().run();
   }
  */

}
