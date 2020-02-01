import java.util.HashMap;
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
    private UiV2 uiV2;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private HashMap<Cloud, String> hm;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/baby.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/mother.png"));

    private static Command globalCommand;
    private static String tempDesc;

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
        this.uiV2 = new UiV2();
        this.globalCommand = Command.DEFAULT;
        this.hm = new HashMap<Cloud, String>();
        hm.put(Cloud.TEMP, "");
        hm.put(Cloud.TEMPDESC, "");
        hm.put(Cloud.TEMPTD1, "");
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (isRunning) {
                getInput = sc.next();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String getInput, Command currCommand) {
        String res = "";
        if (currCommand == Command.DEFAULT) {
            try {
                Command command = parser.parse(getInput);
                switch (command) {
                case BYE:
                    res = uiV2.sendBye();
                    break;
                case LIST:
                    res = uiV2.sendList(lst);
                    break;
                case ADD:
                    globalCommand = Command.ADD;
                    hm.put(Cloud.TEMP, getInput); //todo, deadline, event
                    res = "What do you want to add?";
                    break;
                case FIND:
                    globalCommand = Command.FIND;
                    res = "Enter the keyword or phrase you would like to find. :)";
                    break;
                case DONE:
                    globalCommand = Command.DONE;
                    res = "Enter the task number you want to mark as done. :D";
                    break;
                case DELETE:
                    globalCommand = Command.DELETE;
                    res = "Enter the task number you want to delete. :o";
                    break;
                default:
                    throw new DukeException("Invalid Input");
                }

            } catch (DukeException e) {
                res = uiV2.sendErrInvalidInput();
            } catch (NullPointerException e) {
                res = uiV2.sendErrInvalidInput();
            } catch (Exception e) {
                e.printStackTrace();
                res = uiV2.sendErrInvalidInput() + " unknown";
            }

        } else if (globalCommand == Command.ADD){
            if (hm.get(Cloud.TEMP).equals("todo")) {
                Task task = factory.buildTodoFromCloud(getInput);
                lst.addTask(task);
                res = uiV2.sendAddTask(task, lst.getSize());
                storage.save(lst);
                globalCommand = Command.DEFAULT;
            } else if (hm.get(Cloud.TEMP).equals("deadline")) {
                hm.put(Cloud.TEMPDESC, getInput);
                res = "When is the deadline? \nFormat: {dd/mm/yyyy hhmm}";
                globalCommand = Command.CREATEDEADLINE1;
            } else if (hm.get(Cloud.TEMP).equals("event")) {
                hm.put(Cloud.TEMPDESC, getInput);
                res = "When does the event start? \nFormat: {dd/mm/yyyy hhmm}";
                globalCommand = Command.CREATEEVENT1;
            }

        } else if (globalCommand == Command.CREATEDEADLINE1){
            Task task = factory.buildDeadlineFromCloud(hm.get(Cloud.TEMPDESC), getInput);
            lst.addTask(task);
            res = uiV2.sendAddTask(task, lst.getSize());
            storage.save(lst);
            globalCommand = Command.DEFAULT;

            hm.put(Cloud.TEMPDESC, "");

        } else if (globalCommand == Command.CREATEEVENT1){
            hm.put(Cloud.TEMPTD1, getInput);
            res = "When does the event end? \nFormat: {dd/mm/yyyy hhmm}";
            globalCommand = Command.CREATEEVENT2;

        } else if (globalCommand == Command.CREATEEVENT2){
            Task task = factory.buildEventFromCloud(hm.get(Cloud.TEMPDESC), hm.get(Cloud.TEMPTD1), getInput);
            lst.addTask(task);
            res = uiV2.sendAddTask(task, lst.getSize());
            storage.save(lst);
            globalCommand = Command.DEFAULT;

            hm.put(Cloud.TEMPDESC, "");
            hm.put(Cloud.TEMPTD1, "");

        } else if (globalCommand == Command.FIND) {
            TaskList tempLst = lst.findMatchingTasks(getInput);
            res = uiV2.sendList(tempLst);
            globalCommand = Command.DEFAULT;

        } else if (globalCommand == Command.DONE) {
            lst.doneTask(getInput);
            res = uiV2.sendDoneTask(lst.getTaskFromString(getInput));
            storage.save(lst);
            globalCommand = Command.DEFAULT;

        } else if (globalCommand == Command.DELETE) {
            res = uiV2.sendDeleteTask(lst.getTaskFromString(getInput),lst.getSize() - 1);
            lst.deleteTask(getInput);
            storage.save(lst);
            globalCommand = Command.DEFAULT;

        }else {
            res = "other command";
            globalCommand = Command.DEFAULT;
        }

        return res;

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Duke dukeO) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(dukeO.getResponse(userInput.getText(), globalCommand));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        Duke dukeO = new Duke("src/main/data/tasks.ser");

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
            handleUserInput(dukeO);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(dukeO);
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
}
