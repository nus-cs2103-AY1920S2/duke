//Code for GUI is heavily adapted from the Duke JavaFX tutorials, available at
//https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart1.md

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * Encapsulates a graphical user interface (GUI) frontend of Duke.
 * The `Gui` class implements a GUI which allows the user to enter commands and view its responses.
 * The task list is saved when the user enters the "bye" command,
 * or when the user closes the window.
 */
public class Gui implements Ui {
    private Stage stage;
    private VBox dialogContainer;
    private TextField userInput;
    private Duke duke;
    private boolean isClosed = false;
    private Font font = new Font("Liberation Mono", 15.0);
    
    /**
     * Constructs a new `Gui` instance.
     * Each `Duke` object should construct at most one `Gui` instance using itself as the parameter.
     * @param duke The corresponding `Duke` instance
     */
    public Gui(Duke duke) {
        this.duke = duke;
        
        PrintUtil.setIndentLevel(0);
    }
    
    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setFont(font);

        return textToAdd;
    }
    
    /**
     * Receives control of the JavaFX `Application` from `Duke`.
     * Sets up GUI components and callbacks, then runs the event loop until termination.
     * @param stage `Stage` object received from `Duke#start`
     */
    public void start(Stage stage) {
        this.stage = stage;
        
        //Step 1. Formatting the window to look as expected.
        
        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        //stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        
        mainLayout.setPrefSize(400.0, 600.0);
        
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        
        // You will need to import `javafx.scene.layout.Region` for this. 
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        userInput.setPrefWidth(325.0);
        
        sendButton.setPrefWidth(55.0);
        
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        //Step 3. Add functionality to handle user input.
        startMessage();
        showGreeting();
        endMessage();
        
        sendButton.setOnMouseClicked((event) -> {
            duke.processCommand(readCommandString()); //TODO: get reference to Duke instance
        });

        userInput.setOnAction((event) -> {
            duke.processCommand(readCommandString()); //TODO: get reference to Duke instance
        });
        
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    
    /**
     * Denotes the start of a new message to be printed.
     */
    public void startMessage() {
    }
    
    /**
     * Denotes the end of a new message to be printed.
     */
    public void endMessage() {
        //getDialogLabel(PrintUtil.flushBuffer());
        dialogContainer.getChildren().add(getDialogLabel(PrintUtil.flushBuffer()));
    }
    
    /**
     * Reads a single-line command string from the user.
     * @return command string
     */
    public String readCommandString() {
        String command = userInput.getText();
        userInput.clear();
        return command;
    }
    
    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        PrintUtil.printHeaderLine();
    }
    
    /**
     * Displays a numbered task.
     * @param index Index of the task
     * @param task Task to be displayed
     */
    public void showNumberedEntry(int index, Task task) {
        PrintUtil.indentedPrintf("%d.%s\n", index, task);
    }
    
    /**
     * Displays the message of the provided `DukeException`.
     * @param e exception of type DukeException
     */
    public void showError(DukeException e) {
        PrintUtil.indentedPrintf("Error: %s\n",e.getMessage());
    }
    
    /**
     * Displays a message that the task list could not be found.
     * @param savePath Intended path of the file
     */
    public void showSaveNotFoundMessage(String savePath) {
        PrintUtil.printHeaderLine();
        PrintUtil.indentedPrintln("Error: Task list not found");
        PrintUtil.indentedPrintf("       Duke will create a new task list file at %s\n", savePath);
        PrintUtil.printHeaderLine();
    }
    
    /**
     * Displays a greeting.
     */
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        PrintUtil.indentedPrintln("Hello from\n" + logo);
    }
    
    /**
     * Displays the goodbye message.
     * Header lines are not printed.
     */
    public void showBye() {
        PrintUtil.indentedPrintln("Goodbye");
    }
    
    /**
     * Displays a message when a task is added.
     * @param task Task added
     * @param remainingCount New number of tasks
     */
    public void showAddTaskMessage(Task task, int remainingCount) {
        PrintUtil.indentedPrintf("Added task:\n  %s\n", task);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", remainingCount);
    }
    
    /**
     * Displays a message when a task is removed.
     * @param task Task added
     * @param remainingCount New number of tasks
     */
    public void showRemoveTaskMessage(Task task, int remainingCount) {
        PrintUtil.indentedPrintf("Removed task:\n  %s\n", task);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", remainingCount);
    }
    
    /**
     * Displays a message when a task is marked as completed.
     * @param task Task marked as complete
     */
    public void showDoneTaskMessage(Task task) {
        PrintUtil.indentedPrintf("Marked task as done:\n  %s\n", task);
    }
    
    /**
     * Displays an error message when a command is not recognized.
     * @param command Command string
     */
    public void showUnknownCommandMessage(String command) {
        PrintUtil.indentedPrintf("Error: Unknown command: %s\n", command);
    }
    
    /**
     * Displays a header message to indicate matching tasks from a query.
     * This method only prints the header message.
     */
    public void showMatchingTasksMessage() {
        PrintUtil.indentedPrintln("Here are the matching tasks in your list:");
    }
    
    /**
     * Closes and cleans up resources held by the UI.
     * This method closes the only JavaFX `Stage`, thereby ending the GUI loop.
     */
    public void close() {
        if (!isClosed) {
            stage.close();
        }
        isClosed = true;
    }
}
