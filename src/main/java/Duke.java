import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Task[] arr = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    Hello! I'm Duke\n" + "  What can I do for you?");
        Task.loadSavedData(); //check if there is previous list avail and load if avail
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputs = input.split(" ", 2);
                String command = inputs[0];
                char[] inputArr = input.toCharArray();
                if (command.equals("todo") || command.equals("t")) { //create todo
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    ;
                    String info = Todo.generateTodoDesc(inputArr);
                    Todo task = new Todo(info);
                    Task.addTask(task);
                } else if (command.equals("event") || command.equals("e")) { //create event
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    ;
                    String date, desc;
                    date = Event.getEventDate(inputs[1]);
                    desc = Event.getEventDesc(inputArr);
                    Event task = new Event(desc, date);
                    Task.addTask(task);
                } else if (command.equals("deadline") || command.equals("d")) { //create deadline
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    ;
                    String by, desc;
                    by = Deadline.getDate(inputs[1]);
                    desc = Deadline.getDesc(inputArr);
                    Deadline task = new Deadline(desc, by);
                    Task.addTask(task);
                } else if (command.equals("list") || command.equals ("l")) { //list command
                    Task.showTasks();
                } else if (command.equals("done") || command.equals("do")) { //done command
                    Task.taskDone(input);
                } else if (command.equals("bye") || command.equals("b")) { //bye command
                    Task.saveToFile();
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("delete") || command.equals("del")) { //delete command
                    Task.deleteTask(inputs[1]);
                } else if (command.equals("find") || command.equals("f")) { //find command
                    Task.find(inputs[1]);
                } else if (command.equals("view") || command.equals("v")) { //view schedules
                    Task.viewSchedule(inputs[1]);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void start(Stage stage){
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
        return "Duke heard: " + input;
    }
}