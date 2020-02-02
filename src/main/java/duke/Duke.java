package duke;

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

import java.io.IOException;

/**
 * Duke is a task management assistant. It accepts user commands with the appropriate parameters and time
 * information (if any). It can add/delete tasks, mark tasks as done, and list all tasks. Upon exit, the
 * tasks will be saved to a data file. 
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public static void main(String[] args) {
        new Duke("data/tasks.txt").runCli();
    }

    /**
     * Constructs a Duke object. An attempt will be made to load in tasks from the default file. If the file
     * does not exist or cannot be accessed, an empty task list will be loaded.
     */
    public Duke() {
        ui = new Ui();

        // load saved tasks from file
        try {
            storage = new Storage("data/tasks.txt");
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    /**
     * Constructs a Duke object. An attempt will be made to load in tasks from the file specified. If the file
     * does not exist or cannot be accessed, an empty task list will be loaded.
     * 
     * @param filePath Path of file containing saved tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        
        // load saved tasks from file
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke task management assistant. The user will input commands and the appropriate actions
     * will be performed. Enter "bye" to exit the program.
     */
    public void runCli() {
        System.out.println(ui.showGreeting());

        label:
        while (true) {
            String input = ui.getInput();
            
            try {
                Parser instruction = new Parser(input);
                Command command = instruction.getCommand();

                switch (command) {
                case BYE:
                    break label;
                case DONE:
                    setDoneAndShow(tasks, ui, Integer.parseInt(instruction.getParameters()));
                    break;
                case TODO:
                    addTaskAndShow(tasks, ui, new Todo(instruction.getParameters()));
                    break;
                case DEADLINE:
                    addTaskAndShow(tasks, ui,
                            new Deadline(instruction.getParameters(), instruction.getDate()));
                    break;
                case EVENT:
                    addTaskAndShow(tasks, ui,
                            new Event(instruction.getParameters(), instruction.getDate()));
                    break;
                case LIST:
                    System.out.println(ui.showTasks(tasks));
                    break;
                case DELETE:
                    deleteTaskAndShow(tasks, ui, Integer.parseInt(instruction.getParameters()));
                    break;
                case FIND:
                    ui.showFound(tasks.findTasks(instruction.getParameters()));
                default:
                    ;
                }
            } catch (InvalidInstructionException e) {
                System.out.format("\tError: %s. Please try again.%n%n", e.getMessage());
            }
        }

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("\tError: Unable to write to file.\n");
        }
        
        System.out.println(ui.showFarewell());
    }

    private static void setDoneAndShow(
            TaskList tasks, Ui ui, int taskNum) throws InvalidInstructionException {
        tasks.setAsDone(taskNum);
        System.out.println(ui.showSetAsDone(tasks, tasks.getTask(taskNum)));
    }

    private static void addTaskAndShow(TaskList tasks, Ui ui, Task task) {
        tasks.addTask(task);
        System.out.println(ui.showAddTask(tasks, task));
    }

    private static void deleteTaskAndShow(
            TaskList tasks, Ui ui, int taskNum) throws InvalidInstructionException {
        Task delTask = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);
        System.out.println(ui.showDeleteTask(tasks, delTask));
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        // set window and UI elements
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//        
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//        
//        scene = new Scene(mainLayout);
//        
//        stage.setScene(scene);  // set stage to show scene
//        stage.show();  // render stage
//        
//        // format window and UI elements
//        stage.setTitle("Duke - Task Master");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//        
//        mainLayout.setPrefSize(400.0, 600.0);
//        
//        scrollPane.setPrefSize(385.0, 535.0);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//        
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        
//        userInput.setPrefWidth(325.0);
//        
//        sendButton.setPrefWidth(55.0);
//        
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//        
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//        
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//        
//        // handle user input
//        sendButton.setOnMouseClicked(mouseEvent -> {
//            handleUserInput();
//        });
//        
//        userInput.setOnAction(actionEvent -> {
//            handleUserInput();
//        });
//        
//        // auto scroll down
//        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
//    }

    private Label getDialogLabel(String text) {
        Label textInput = new Label(text);
        textInput.setWrapText(true);
        
        return textInput;
    }
    
//    private void handleUserInput() {
//        String input = userInput.getText();
//        String response = processInstruction(input);
//        
//        Label userText = new Label(input);
//        Label dukeText = new Label(response);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
    
    protected String processInstruction(String input) {
        try {
            Parser instruction = new Parser(input);
            Command command = instruction.getCommand();

            switch (command) {
                case BYE:
                    try {
                        storage.writeToFile(tasks);
                        return ui.showFarewell();
                    } catch (IOException e) {
                        return "Error: Unable to write to file.\n";
                    }
                case DONE: {
                    int taskNum = Integer.parseInt(instruction.getParameters());
                    tasks.setAsDone(taskNum);
                    return ui.showSetAsDone(tasks, tasks.getTask(taskNum));
                }
                case TODO:
                    Task todo = new Todo(instruction.getParameters());
                    tasks.addTask(todo);
                    return ui.showAddTask(tasks, todo);
                case DEADLINE:
                    Task deadline = new Deadline(instruction.getParameters(), instruction.getDate());
                    tasks.addTask(deadline);
                    return ui.showAddTask(tasks, deadline);
                case EVENT:
                    Task event = new Event(instruction.getParameters(), instruction.getDate());
                    tasks.addTask(event);
                    return ui.showAddTask(tasks, event);
                case LIST:
                    return ui.showTasks(tasks);
                case DELETE:
                    int taskNum = Integer.parseInt(instruction.getParameters());
                    Task delTask = tasks.getTask(taskNum);
                    tasks.deleteTask(taskNum);
                    return ui.showDeleteTask(tasks, delTask);
                case FIND:
                    return ui.showFound(tasks.findTasks(instruction.getParameters()));
                default:
                    return "";
            }
        } catch (InvalidInstructionException e) {
            return String.format("Error: %s. Please try again.%n%n", e.getMessage());
        }
    }
}
