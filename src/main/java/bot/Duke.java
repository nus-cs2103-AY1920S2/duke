package bot;

import bot.command.instruction.ParsedInstruction;

import bot.gui.DialogueBox;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    private Image user =
            new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke =
            new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
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

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scroll, input, enterButton);

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

        enterButton.setOnMouseClicked(event -> {
            dialog.getChildren().add(getDialogLabel(input.getText()));
            input.clear();
        });

        input.setOnAction(event -> {
            handleUserInput();
        });

        // Scroll down every time height changes
        dialog.heightProperty().addListener(observable -> scroll.setVvalue(1.0));
    }


    /**
     * A method to format a Label for 4LC3N-BOT GUI.
     *
     * @param text The text to create a Label of
     * @return Returns a Label that contains that text,
     *     with text wrap enabled
     */
    private Label getDialogLabel(String text) {
        Label dialogLabel = new Label(text);
        dialogLabel.setWrapText(true);
        dialogLabel.fontProperty().set(Font.font("Monospaced", 50));
        return dialogLabel;
    }

    /**
     * A method to be called to generate the
     * DialogueBoxes in the GUI for the user
     * and the bot
     */
    private void handleUserInput() {
        Label userText = getDialogLabel(input.getText());
        Label dukeText = getDialogLabel(getResponse(input.getText()));
        HBox space1 = new HBox();
        space1.setMinHeight(10.0);
        HBox space2 = new HBox();
        space2.setMinHeight(10.0);
        dialog.getChildren().addAll(
                DialogueBox.getUserBox(userText, new ImageView(user)),
                space1,
                DialogueBox.getBotBox(dukeText, new ImageView(duke)),
                space2
        );
        input.clear();
    }

    /**
     * Formats the response given by the bot
     *
     * @param input The raw text input by the user
     * @return Formatted response
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
