package duke.main;
import duke.exception.InvalidCommandException;
import duke.exception.OutOfBoundMarkingRequestException;
import duke.exception.TaskErrorException;
import duke.util.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    static final Scanner scanner = new Scanner(System.in);
    static final Duke bot = new Duke();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/botIcon.png"));

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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(bot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
