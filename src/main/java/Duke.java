
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sampletest.Task;
import sampletest.Events;
import sampletest.Deadlines;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * CS2103 Individual Project
 * author Wei Cheng
 * The class for the ChatBot
 */
public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    public Duke() {

    }

    /**
     * Constructor to create an instance of Duke.
     *
     * @param filePath the String representation of the file location
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * To run the conversation with the user.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String[] userInput = sc.nextLine().split(" ", 2);
        Parser parser = new Parser(userInput);
        String command = parser.getCommand();
        while (!command.equals("bye")) {
            try {
                ExceptionGenerator.checkInputLength(userInput);
                ExceptionGenerator.checkInputAction(userInput);
                switch (command) {
                    case "done":
                        int taskNo = Integer.parseInt(parser.getDescription());
                        this.tasks.getTask(taskNo - 1).markAsDone();
                        ui.transmitMessage("Nice! I've marked this task as done:\n "
                                + this.tasks.getTask(taskNo - 1).toString());
                        break;
                    case "delete":
                        taskNo = Integer.parseInt(userInput[1]) - 1;
                        Task removedTask = this.tasks.getTask(taskNo);
                        this.tasks.removeTask(taskNo);
                        ui.transmitMessage("Noted. I've removed this task:\n"
                                + removedTask.toString());
                        break;
                    case "list":
                        this.printText();
                        break;
                    case "todo":
                        String description = parser.getDescription();
                        ui.transmitMessage("Got it. I've added this task:");
                        this.tasks.add(new Task(description));
                        break;
                    case "deadline":
                        String[] tokens = parser.getDescription().split(" /by ");
                        ui.transmitMessage("Got it. I've added this task:");
                        this.tasks.add(new Deadlines(tokens[0], tokens[1]));
                        Deadlines d =  new Deadlines(tokens[0], tokens[1]);
                        ui.transmitMessage((d.getDate()).toString());
                        break;
                    case "event":
                        tokens = parser.getDescription().split(" /at ");
                        ui.transmitMessage("Got it. I've added this task:");
                        this.tasks.add(new Events(tokens[0], tokens[1]));
                        break;
                    case "find":
                        String keyWord = parser.getDescription();
                        ArrayList<Task>  matchingTasks = new ArrayList<>();
                        for(int i = 0 ; i < tasks.taskStorage.size() ; i++) {
                            Task task = tasks.taskStorage.get(i);
                            if((task.getDescription()).contains(keyWord)){
                                matchingTasks.add(task);
                            }
                        }
                        if(matchingTasks.size() == 0){
                            ui.transmitMessage("There is no matching task in your list");
                        }
                        else {
                            System.out.println("Here are the matching tasks in your list:");
                            for (Task task : matchingTasks){
                                System.out.println(task.toString());
                            }
                        }
                        break;
                }

                int numbOfTask = this.tasks.taskStorage.size();
                if(numbOfTask > 0 && !command.equals("list") && !command.equals("delete") && !command.equals("done")) {
                    ui.transmitMessage(this.tasks.getTask(numbOfTask - 1).toString());
                }
                    ui.transmitMessage("Now you have " + numbOfTask + " tasks in the list.");
            } catch (DukeException ex){
                ex.printStackTrace();
            }
            userInput = sc.nextLine().split(" ", 2);
            parser = new Parser(userInput);
            command = parser.getCommand();
            storage.saveToDisk(this.tasks.taskStorage);
        }
        ui.initiateFareWell();
    }

    /**
     * To print out all the task in the
     * TaskStorage
     */
    public void printText() {
        int counter = 1;
        for(Task task : this.tasks.taskStorage){
            ui.transmitMessage(Integer.valueOf(counter).toString() + "." + task.toString());
            counter++;
        }

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
   /* @Override
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
         /*sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
            // current code ...

            //Scroll down to the end every time dialogContainer's height changes.
            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        });
    //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event)->

    {
        handleUserInput();
    });

        userInput.setOnAction((event)->

    {
        handleUserInput();
    });*/

    }



