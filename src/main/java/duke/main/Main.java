package duke.main;
import duke.exception.InvalidCommandException;
import duke.exception.OutOfBoundMarkingRequestException;
import duke.exception.TaskErrorException;
import duke.util.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    static final Scanner scanner = new Scanner(System.in);
    static final Duke bot = new Duke();

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
