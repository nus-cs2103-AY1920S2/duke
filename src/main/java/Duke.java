import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private static final String BORDER_LINE = "____________________________________________________________\n";
    private static final int STARTING_VARIABLE = 0;
    private static final int VARIABLE_ENTRY_ONE = 1;
    private static final int SPLIT_BY_TWO = 2;


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
                DialogBox.getUserDialog(userText.getText(), new ImageView(user).getImage()),
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(duke).getImage())
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */


    static Scanner sc = new Scanner(System.in);
    static final String FILEPATH = "../../data/duke.txt";
    static Ui ui;
    static TaskList tasks;
    static Storage storage;

    static String taskToParse(TaskList tasks) {

        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < tasks.getSize(); i++) {
            s.append(tasks.getIndex(i).toParser() + "\n");
        }

        return s.toString();
    }

    static void printIntro() {
        String introText = BORDER_LINE + "Hello! I'm Duke\nWhat can I do for you?\n" + BORDER_LINE;
        assert(introText == null): "Intro text should not be null";
    }

    static void printReply(Task task) {
        System.out.print(
                BORDER_LINE +
                        "Got it! I've added the task: \n" + task.toString() + "\nNow you have " + tasks.getSize() +
                        " tasks in the list.\n" + BORDER_LINE);
    }

    static void printGoodbye() {
        String goodbyeText = BORDER_LINE + "\"Bye. Hope to see you again soon!\"n" + BORDER_LINE;
        System.out.println(goodbyeText);
        assert(goodbyeText == null): "Goodbye text should not be NULL";
    }

    static String stringToTime(String s) throws DukeException {
        try {
            // Convert DATE to expected format
            LocalDate d = LocalDate.parse(s.split(" ")[STARTING_VARIABLE]);
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            Date dateObj = sdf.parse(s.split(" ")[VARIABLE_ENTRY_ONE]);
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + new SimpleDateFormat("HH:mm aa").format(dateObj);
        } catch (Exception e) {
            System.out.println("Please give a correct format (ie. yyyy-mm-dd hh:mm)");
            throw new DukeException(s);
        }
    }

    static void addTask(String input) throws DukeException, TodoException {
        try {
            if (input.toLowerCase().equals("list")) {
                tasks.printList();
            } else if (input.split(" ")[STARTING_VARIABLE].equals("done")) {
                int taskNumber = Integer.parseInt(input.split(" ")[VARIABLE_ENTRY_ONE]) - 1;
                tasks.markTaskDone(taskNumber);
                storage.saveFile(taskToParse(tasks));
            } else if (input.split(" ")[STARTING_VARIABLE].equals("todo")) {
                if (input.split(" ").length == 1) {
                    throw new TodoException(input);
                }
                Task task = new Todo(input.split(" ", SPLIT_BY_TWO)[1]);
                tasks.addTask(task);
                storage.saveFile(taskToParse(tasks));
                printReply(task);
            } else if (input.split(" ")[STARTING_VARIABLE].equals("deadline")) {
                Task task = new Deadline(input.split("/by ", SPLIT_BY_TWO)[STARTING_VARIABLE].split(" ", SPLIT_BY_TWO)[VARIABLE_ENTRY_ONE], stringToTime(input.split("/by ", SPLIT_BY_TWO)[VARIABLE_ENTRY_ONE]));
                tasks.addTask(task);
                storage.saveFile(taskToParse(tasks));
                printReply(task);
            } else if (input.split(" ")[STARTING_VARIABLE].equals("event")) {
                Task task = new Event(input.split("/at", SPLIT_BY_TWO)[STARTING_VARIABLE].split(" ", SPLIT_BY_TWO)[VARIABLE_ENTRY_ONE], stringToTime(input.split("/at ", SPLIT_BY_TWO)[VARIABLE_ENTRY_ONE]));
                tasks.addTask(task);
                storage.saveFile(taskToParse(tasks));
                printReply(task);
            } else if (input.split(" ")[STARTING_VARIABLE].equals("find")){
                tasks.search(input.split(" ", SPLIT_BY_TWO)[VARIABLE_ENTRY_ONE]);
            } else {
                throw new DukeException(input);
            }
        } catch (TodoException e) {
            System.out.println(e.toString());
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    static void deleteTask(String input) {
        int pos = Integer.parseInt(input.split(" ")[VARIABLE_ENTRY_ONE]);
        Task task = tasks.removeTask(pos - 1);
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this task: \n  "
                + task.toString() + "\n Now you have " + tasks.getSize() + " tasks in the list.\n"
                + "____________________________________________________________");
    }

    public void run() throws DukeException {
        printIntro();
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            if (input.split(" ")[STARTING_VARIABLE].toLowerCase().equals("delete")) {
                deleteTask(input);
                storage.saveFile(taskToParse(tasks));
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }
        printGoodbye();
    }

    public Duke() {
        String filePath = FILEPATH;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }

}