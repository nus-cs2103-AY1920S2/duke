//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//import javafx.scene.layout.Region;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.control.TextField;
//import javafx.scene.control.Button;
//import javafx.scene.layout.AnchorPane;
//
////Problem: we need to run GUI#Start using Application.launch
//public class Gui implements Ui {
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String commandText) {
//        // You will need to import `javafx.scene.control.Label`. 
//        //TODO: gracefully exit program
//        String commandString = commandText;
//        
//        ui.startMessage();
//        try {
//            Optional<Command> c = new Parser(commandString).parse();
//            if (c.isPresent()) {
//                Command cmd = c.get();
//                cmd.execute(tasks, ui, storage);
//                isRunning = isRunning && !cmd.isExit();
//            } else {
//                ui.showUnknownCommandMessage(commandString);
//            }
//        } catch (DukeException e) {
//            ui.showError(e);
//        }
//        ui.endMessage();
//        
//        Label textToAdd = new Label(PrintUtil.flushBuffer());
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    @Override
//    public void start(Stage stage) {
//        //Step 1. Formatting the window to look as expected.
//        //...
//        
//        //The container for the content of the chat to scroll.
//        ScrollPane scrollPane = new ScrollPane();
//        VBox dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        TextField userInput = new TextField();
//        Button sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        Scene scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        //stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//        
//        mainLayout.setPrefSize(400.0, 600.0);
//        
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//        
//        // You will need to import `javafx.scene.layout.Region` for this. 
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        
//        userInput.setPrefWidth(325.0);
//        
//        sendButton.setPrefWidth(55.0);
//        
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//        
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//
//        //Step 3. Add functionality to handle user input.
//        ui.startMessage();
//        ui.greet();
//        ui.endMessage();
//        
//        dialogContainer.getChildren().add(PrintUtil.flushBuffer());
//        
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//        
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//    }
//    
//    /**
//     * Denotes the start of a new message to be printed.
//     */
//    public void startMessage();
//    
//    /**
//     * Denotes the end of a new message to be printed.
//     */
//    public void endMessage();
//    
//    /**
//     * Reads a single-line command string from the user.
//     * @return command string
//     */
//    public String readCommandString();
//    
//    /**
//     * Displays a horizontal line.
//     */
//    public void showLine();
//    
//    /**
//     * Displays a numbered task.
//     * @param index Index of the task
//     * @param task Task to be displayed
//     */
//    public void showNumberedEntry(int index, Task task);
//    
//    /**
//     * Displays the message of the provided `DukeException`.
//     * @param e exception of type DukeException
//     */
//    public void showError(DukeException e);
//    
//    /**
//     * Displays a message that the task list could not be found.
//     * @param savePath Intended path of the file
//     */
//    public void showSaveNotFoundMessage(String savePath);
//    
//    /**
//     * Displays a greeting.
//     */
//    public void showGreeting();
//    
//    /**
//     * Displays the goodbye message.
//     * Header lines are not printed.
//     */
//    public void showBye();
//    
//    /**
//     * Displays a message when a task is added.
//     * @param task Task added
//     * @param remainingCount New number of tasks
//     */
//    public void showAddTaskMessage(Task task, int remainingCount);
//    
//    /**
//     * Displays a message when a task is removed.
//     * @param task Task added
//     * @param remainingCount New number of tasks
//     */
//    public void showRemoveTaskMessage(Task task, int remainingCount);
//    
//    /**
//     * Displays a message when a task is marked as completed.
//     * @param task Task marked as complete
//     */
//    public void showDoneTaskMessage(Task task);
//    
//    /**
//     * Displays an error message when a command is not recognized.
//     * @param command Command string
//     */
//    public void showUnknownCommandMessage(String command);
//    
//    /**
//     * Displays a header message to indicate matching tasks from a query.
//     * This method only prints the header message.
//     */
//    public void showMatchingTasksMessage();
//}
