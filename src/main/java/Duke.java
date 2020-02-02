import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import duke.task.*;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import duke.dukeException.DukeParseException;
import duke.Interpreter;
import duke.Storage;
import duke.TaskList;	
import duke.Parser;
import duke.commands.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import duke.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;	
import javafx.application.Platform;
import duke.DukeResponse;

public class Duke extends Application {
	static final String separation = "_________________________________________________";
	static final String greetingMessage = "Salue! Je suis Duke. \nWhat can I do for you?";
	static final String pathToData = "data/storage.txt";
	private Storage storage;
	private TaskList taskList;
	private Parser parser;
	private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

	public Duke() {
		storage = new Storage(pathToData);
		taskList = new TaskList(storage.getData());
		parser = new Parser();
	}

	/**
	 * Iteration 2:
	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
	 * the dialog container. Clears the user input after processing.
	 */
	private void handleUserInput() {
		String userCommand = userInput.getText();

	    Label userText = new Label(userCommand);
	    Label dukeText = new Label(getResponse(userCommand));
	    dialogContainer.getChildren().addAll(
	            DialogBox.getUserDialog(userText, new ImageView(user)),
	            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
	    );

	    if (userCommand.equals("bye")) {
	    	Platform.exit();
	    	System.exit(0);
	    }
	    userInput.clear();
	}

	private void printGreeting() {
		Interpreter.printGreeting();
		Interpreter.printMessage(greetingMessage);
		Interpreter.printUsage();
		String text = Interpreter.printGreeting().getMessage() + "\n" 
						+ Interpreter.printMessage(greetingMessage).getMessage() +  "\n" 
						+ Interpreter.printUsage().getMessage() +  "\n"; 
		Label dukeText = new Label(text);
		dialogContainer.getChildren().addAll(
	            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
	    );
	}

	/**
	 * You should have your own function to generate a response to user input.
	 * Replace this stub with your completed method.
	 */
	private String getResponse(String userCommand) {
	    try {
	    	Command current = parser.parse(userCommand);
			DukeResponse result = current.execute(storage, taskList);
			return result.getMessage();
	    } catch (DukeParseException e) {
	    	return e.getMessage();
	    } 
	}

	public void run() {
		Interpreter.printGreeting();
		Interpreter.printMessage(greetingMessage);
		Interpreter.printUsage();

		Scanner cin = new Scanner(System.in);

		while (true) {
			String commandText = cin.nextLine();
			try {
				Command current = parser.parse(commandText);
				current.execute(storage, taskList);
				if (current.isExit()) {
					return;
				}
			} catch (DukeParseException e) {
				Interpreter.printException(e);
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
        userInput = new TextField();
        sendButton = new Button("Send");
     
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
     
        stage.setScene(scene);
        stage.show();
        // more code to be added here later
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

        //Step 3. Add functionality to handle user input.

        printGreeting();

	    sendButton.setOnMouseClicked((event) -> {
	        handleUserInput();
	    });

	    userInput.setOnAction((event) -> {
	        handleUserInput();
	    });

	    stage.setOnCloseRequest(e -> {
	    	storage.saveData(taskList);
       	    Platform.exit();
            System.exit(0);
        });

	    dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
	 * Iteration 1:
	 * Creates a label with the specified text and adds it to the dialog container.
	 * @param text String containing text to add
	 * @return a label with the specified text that has word wrap enabled.
	*/
	private Label getDialogLabel(String text) {
	    Label textToAdd = new Label(text);
	    textToAdd.setWrapText(true);

	    return textToAdd;
	}

	public static void main(String[] args) {
		new Duke().run();
	}
}
