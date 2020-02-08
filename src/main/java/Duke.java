import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke(){
        this("/Users/liuchao/duke/src/main/");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage.getStorageNo());
        } catch (FileNotFoundException | ParseException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run(){
        String userInput = ui.input();
        Parser newInput = new Parser(userInput);

        while(!userInput.equals("bye")){
            if(newInput.getIsProblem()) {
                Parser.isProblem = false;
            } else {
                if (userInput.equals("list")) {
                    Ui.list(this.tasks);
                } else if (userInput.equals("done 2")) {
                    Ui.done2(this.tasks);
                } else if (userInput.contains("delete")) {
                    this.tasks.delete(userInput);
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                }else if (userInput.contains("find")){
                    this.tasks.find(userInput);
                } else if (userInput.contains("todo")) {
                    this.tasks.toDo(userInput);
                    TaskList.totalTasks[TaskList.totalTasksCount -1].printInit();
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                } else if (userInput.contains("deadline")) {
                    this.tasks.deadline(userInput);
                    TaskList.totalTasks[TaskList.totalTasksCount - 1].printInit();
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                } else {
                    this.tasks.event(userInput);
                    TaskList.totalTasks[TaskList.totalTasksCount -1].printInit();
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                }
            }
            userInput = ui.input();
            newInput = new Parser(userInput);
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/savedTaskList.txt").run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        //scene = new Scene(helloWorld); // Setting the scene to be our Label

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

    }
}
