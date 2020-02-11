import java.awt.*;
import java.io.*;
import java.util.Scanner;

import javafx.application.Application;
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


public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/gudetama2.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/gudetama1.png"));

    private Store lib;
    private Ui ui = new Ui();
    private Scanner sn = new Scanner(System.in);
    private DukeException DE;
    String[] CheckInput;

    /**
     * This method create a new Duke object.
     * If file exist, load the file. Otherwise create new file.
     */
    public Duke() {
        String filepath = "D:/duke/data/d.txt";
        DE = new DukeException();
        File file = new File(filepath); //create a file obj with the given filepath.
        this.lib = new Store(file); //create store from absolute filepath
        try {
            boolean result = file.exists();
            if(!result){
                boolean isNewFile = file.createNewFile();
            } else {
                    Scanner newSN = new Scanner(file);
                    while(newSN.hasNextLine()){
                        String nxtLine = newSN.nextLine();
                        lib.load(nxtLine);
                    } //end while: for reading existing file
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    } //end Duke

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();                //create a textfield
        sendButton = new Button("Send");       //set button with "Send" label

        AnchorPane mainLayout = new AnchorPane();   //the background
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());
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
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String cmd) {
        if (cmd.equals("bye")) {
            return lib.bye();
        } else if (cmd.equals("list")) {
            return lib.list();
        } else if (cmd.contains("done")) {
            String[] splited = cmd.split(" ");
            if (splited.length < 2){
                return DE.MissingDoneIndex();
            } else {
                int index = Integer.parseInt(splited[1]);
                return lib.done(index);
            }
        } else if (cmd.contains("delete")) {
            String[] splited = cmd.split(" ");
            int index = Integer.parseInt(splited[1]);
            return lib.delete(index);
        } else if (cmd.contains("todo")) {
            CheckInput = cmd.split(" ");
            if (CheckInput.length < 2) {
                return DE.IncorrectInputTodo();
            } else {
                String NewInput = cmd.substring(5);
                return lib.todo(NewInput);
            }
        } else if (cmd.contains("deadline")) {
            CheckInput = cmd.split(" ");
            if (CheckInput.length < 2) {
                return DE.IncorrectInputDeadline();
            } else if (!cmd.contains("/")) {
                return DE.DeadlineMissingDate();
            } else {
                String NewInput = cmd.substring(9);
                String[] ActionTime = NewInput.split("/", 2);
                return lib.deadline(ActionTime);
            }
        } else if (cmd.contains("event")) {
            CheckInput = cmd.split(" ");
            if (CheckInput.length < 2) {
                return DE.IncorrectInputEvent();
            } else if (!cmd.contains("/")) {
                return DE.EventMissingDate();
            } else {
                String NewInput = cmd.substring(6);
                String[] ActionTime = NewInput.split("/", 2);
                return lib.event(ActionTime);
            }
        } else if (cmd.contains("find")) {
            CheckInput = cmd.split(" ");
            if (CheckInput.length < 2) {
                return DE.InvalidInput();
            } else {
                String NewInput = cmd.substring(4).strip();
                return lib.find(NewInput);
            }
        } else {
            return DE.InvalidInput();
        }
    }
}

