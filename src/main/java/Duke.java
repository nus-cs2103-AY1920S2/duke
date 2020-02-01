import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Main class. */
public class Duke extends Application{

    private Storage storage;
    private TaskList lst;
    private Ui ui;
    private Parser parser;
    private Factory factory;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/baby.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/mother.png"));

    /**
     * Empty Constructor for Duke class. Needed to run Launcher.
     */
    public Duke() {

    }

    /**
     * Constructor for Duke class. Ui handles user interaction. Storage stores and loads Tasklist from
     * persistent storage. TaskList stores tasks and provide functions to maintain these tasks.
     * Parser parses input. Factory creates task objects.
     *
     * @param filepath path where TaskList is stored.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.lst = storage.load();
        this.parser = new Parser();
        this.factory = new Factory();
    }

    public static void main(String[] args) {
        new Duke("src/main/data/tasks.ser").run();
    }

    /**
     * Runs the duke bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();

        String getInput = null;
        getInput = sc.next();

        boolean isRunning = true;

        while (isRunning) {
            try {
                Command command = parser.parse(getInput);
                switch (command) {
                case BYE:
                    ui.showBye();
                    isRunning = false;
                    break;
                case LIST:
                    ui.showList(lst);
                    break;
                case FIND:
                    String toFind = sc.nextLine();
                    TaskList tempLst = lst.findMatchingTasks(toFind);
                    ui.showList(tempLst);
                    break;
                case ADD:
                    String line = sc.nextLine();
                    Task task = factory.buildTask(getInput, line);
                    lst.addTask(task);
                    ui.showAddTask(task, lst.getSize());
                    storage.save(lst);
                    break;
                case DONE:
                    String getDoneString = sc.next();
                    lst.doneTask(getDoneString);
                    ui.showDoneTask(lst.getTaskFromString(getDoneString));
                    storage.save(lst);
                    break;
                case DELETE:
                    String getDeleteString = sc.next();
                    ui.showDeleteTask(lst.getTaskFromString(getDeleteString),lst.getSize() - 1);
                    lst.deleteTask(getDeleteString);
                    storage.save(lst);
                    break;
                default:
                    throw new DukeException("Invalid Input");
                }
            } catch (DukeException e) {
                ui.showErrInvalidInput();
            } catch (StringIndexOutOfBoundsException e) {
                ui.showErrInvalidInput();
            } catch (IndexOutOfBoundsException e) {
                ui.showErrInvalidInput();
            } catch (NullPointerException e) {
                ui.showErrInvalidInput();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (isRunning) {
                getInput = sc.next();
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
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
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
