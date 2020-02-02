package bot;

import bot.command.instruction.ParsedInstruction;

import bot.loadsave.DummyLoader;
import bot.loadsave.LoadAndSave;
import bot.loadsave.TasksToDisk;

import bot.task.Task;

import bot.command.CommandParser;
import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.exception.UnknownInstructionException;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javafx.stage.Stage;

import java.io.FileNotFoundException;

import java.util.List;
import java.util.Scanner;

/**
 * Main driver class for 4LC3N-BOT, containing the
 * primary loop that awaits input from the user
 */
public class Duke extends Application {
    private ScrollPane scroll;
    private VBox dialog;
    private TextField input;
    private Button enterButton;
    private Scene scene;

    /**
     * Main program of 4LC3N-BOT
     *
     * @param args Command line arguments, can be
     *             safely ignored
     */
    public static void main(String[] args) {
        // initialise UI
        Ui botUi = new Ui();
        botUi.showVersion();
        botUi.showGreetings();
        botUi.showLoading();

        // initialise CommandParser
        CommandParser parser = new CommandParser();

        // initialise TaskStorage
        String fileDirectory = "../user/data";
        String fileName = "tasks.botstore";
        TaskStorage store = new TaskStorage();

        LoadAndSave<Task> botStore;
        try {
            botStore = new TasksToDisk(fileDirectory, fileName);
        } catch (FileNotFoundException e) {
            botUi.showError(e);
            botStore = new DummyLoader<Task>();
        }

        store.importTasks(botStore.loadFromDisk());

        // initialise instruction Executor
        Executor executor = new Executor(store, botUi, botStore);

        Scanner input = new Scanner(System.in);

        botUi.showInitial();
        botUi.showAwaiting();

        // main bot system loop
        while (input.hasNext()) {
            String command = input.nextLine();
            // parse the command
            ParsedInstruction next;
            try {
                next = parser.parse(command);
            } catch (InadequateArgumentsException | TooManyArgumentsException
                    | UnknownInstructionException e
            ) {
                botUi.showError(e);
                continue;
            }

            if (!executor.execute(next.getInstruction(), next.getArguments())) {
                break;
            }
            botUi.showAwaiting();
        }
        input.close();
    }

    static {
        System.out.println(System.getProperty("os.name"));
        List<String> fonts = javafx.scene.text.Font.getFamilies();
        for (String font : fonts) {
            System.out.println(font);
        }
    }

    @Override
    public void start(Stage stage) {
        scroll = new ScrollPane();
        dialog = new VBox();
        scroll.setContent(dialog);

        input = new TextField();
        enterButton = new Button("Enter");

        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.fontProperty().set(Font.font("Monospaced", 50));

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(helloWorld, scroll, input, enterButton);

        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        stage.setTitle("4LC3N-BOT");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
        scroll.setPrefSize(385, 535);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scroll.setVvalue(1.0);
        scroll.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialog.setPrefHeight(Region.USE_COMPUTED_SIZE);

        input.setPrefWidth(325.0);

        enterButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scroll, 1.0);

        AnchorPane.setBottomAnchor(enterButton, 1.0);
        AnchorPane.setRightAnchor(enterButton, 1.0);

        AnchorPane.setLeftAnchor(input, 1.0);
        AnchorPane.setBottomAnchor(input, 1.0);
    }
}
