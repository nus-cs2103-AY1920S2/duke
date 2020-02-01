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

	public Duke() {
		storage = new Storage(pathToData);
		taskList = new TaskList(storage.getData());
		parser = new Parser();
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
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
     
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
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
        stage.setScene(scene);
        stage.show();
    }

	public static void main(String[] args) {
		new Duke().run();
	}
}
