package main.java;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.FileAlreadyExistsException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.util.stream.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;






import main.java.*;

//add javadocs
public class Duke extends Application {


    /**
     * Declaration of variables
     */
    Scanner sc = new Scanner(System.in);
    ArrayList<Task> Tasks = new ArrayList<>();
    final String FILEPATH = "java/data/list.txt";
    final File FILE = new File(FILEPATH);
    final String SIZE = "/main/java/data/list.txt";
    final File SIZEFILE = new File(SIZE);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    Storage storage = new Storage(FILEPATH);
    TaskList tl = new TaskList(FILEPATH, Tasks);
    ArrayList<Task> TL = tl.getTL();


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Button startButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/main/resources/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/main/resources/images/DaDuke.jpg"));


    /**
     * Constructor
     *
     * @param String filepath
     */
    public Duke() {}


    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        startButton = new Button("Start");


        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, startButton);

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
        AnchorPane.setRightAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setTopAnchor(startButton, 1.0);
        AnchorPane.setLeftAnchor(startButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 2.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = null;
        try {
            dukeText = new Label(getResponse(userInput.getText()));
        } catch (DukeException e) {
            e.printStackTrace();
        }
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
    private String getResponse(String input) throws DukeException{
        //return "Duke heard: " + input;
        Ui ui = new Ui(input);
        String command = ui.getCommand();
        String response = "";
        if (command.equals("todo")) {
            try {
                //response = "todo";
                Todo todo = new Todo(ui.getDescription());
                tl.TL.add(todo);
                response = ui.addedTask(todo);
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("deadline")) {
            try {
                //response = "deadline";
                String desc = ui.getDescription();
                String date = (desc.split("/by "))[1];
                LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                String sldt = ldt.format(formatter);
                Deadline deadline = new Deadline(desc, sldt);
                tl.TL.add(deadline);
                response = ui.addedTask(deadline);
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("event")) {
            try {
                //response = "event";
                String desc = ui.getDescription();
                String date = (desc.split("/at "))[1];
                LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                String sldt = ldt.format(formatter);
                Event event = new Event(desc, sldt);
                tl.TL.add(event);
                response = ui.addedTask(event);
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("done")) {
            try {
                //response = "done";
                int taskNum = Integer.parseInt(ui.getNumber());
                Task t = tl.TL.get(taskNum - 1);
                response = t.markAsDone();
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("delete")) {
            try {
                //response = "delete";
                int taskNum = Integer.parseInt(ui.getNumber());
                Task t = tl.TL.get(taskNum - 1);
                response = ui.deleteTask(t.toString());
                tl.TL.remove(taskNum - 1);
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("find")) {
            try {
                //response = "find";
                String rest = ui.getDescription();
                response = ("Here are the tasks in your list that matches:" + rest + "\n");
                response += (tl.getTaskFromKeyword(rest));
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("bye")) {
            //response = "bye";
            try {
                String S = "";
                for (int i = 0; i < TL.size(); i++) {
                    S += tl.TL.get(i).toString() + '\n';
                }
                storage.writeToFile(FILEPATH, S);
                response = "bye see you soon";

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (command.equals("list")) {
            try {//response = "list";
                response = (ui.showList()+"\n");
                //storage.printFileContents(FILEPATH);

                for (int i = 0; i < tl.TL.size(); i++) {
                    int j = i + 1;
                    response += (j + tl.TL.get(i).toString() + "\n");
                }
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("begin")) {
            try {
                tl.loadFromStorage();
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            try {
                throw new  DukeException("IDK what you mean");
            } catch (Exception E) {
                response = E.getMessage();
            }
        }
        return response;


    }
}
/**
 *todo:
 * handle exceptions done
 * more oop
 * push to git
 * do this week tasks
 * close the program when input = bye
 * add instructions upon startup
 */
