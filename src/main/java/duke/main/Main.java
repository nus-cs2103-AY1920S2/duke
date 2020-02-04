package duke.main;
import duke.exception.InvalidCommandException;
import duke.exception.OutOfBoundMarkingRequestException;
import duke.exception.TaskErrorException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    static final Scanner scanner = new Scanner(System.in);
    static final Duke bot = new Duke();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Main() {
    }

    /**
     * The main driver for the program
     * @param args
     * @throws OutOfBoundMarkingRequestException
     * @throws InvalidCommandException
     * @throws TaskErrorException
     */
    public static void main(String[] args) throws OutOfBoundMarkingRequestException, InvalidCommandException, TaskErrorException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        bot.greet();

        String userInput = "";

        while (!userInput.equals(Duke.BYE_COMMAND)) {
            userInput = scanner.nextLine();

            if (userInput.equals(Duke.BYE_COMMAND))
                break;
            if (userInput.equals(Duke.LIST_COMMAND)) {
                bot.listStoredItems();
                continue;
            }

            bot.processUserInput(userInput);
        }

        bot.byeBye();
        scanner.close();
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
    }
}
