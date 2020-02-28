package jiachen.duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * The type Duke.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Instantiates a base Duke with base constructor.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.formatLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            System.out.println(ui.formatLoadingError());
            tasks = new TaskList();
        }
    }

    private Command parseCommand(String commandStr) throws InvalidDukeCommandException {

        Command command;
        switch (commandStr) {
        case "bye":
            command = Command.EXIT_COMMAND;
            break;
        case "list":
            command = Command.LIST_COMMAND;
            break;
        case "done":
            command = Command.DONE_COMMAND;
            break;
        case "delete":
            command = Command.DELETE_COMMAND;
            break;
        case "todo":
            command = Command.TODO_COMMAND;
            break;
        case "deadline":
            command = Command.DEADLINE_COMMAND;
            break;
        case "event":
            command = Command.EVENT_COMMAND;
            break;
        case "find":
            command = Command.FIND_COMMAND;
            break;
        case "help":
            command = Command.HELP_COMMAND;
            break;
        case "hello":
            command = Command.HELLO_COMMAND;
            break;
        default:
            throw new InvalidDukeCommandException();
        }
        return command;
    }

    /**
     * The Main entry point into the Duke program.
     */
    public String run(String input) {
        String[] separateLine = input.split(" ", 2);
        String commandStr = separateLine[0];
        String parameters = separateLine.length > 1 ? separateLine[1] : "";
        String response = "Hello!";

        try {
            switch (parseCommand(commandStr)) {
            case EXIT_COMMAND:
                handleExit();
                break;
            case LIST_COMMAND:
                response = handleList();
                break;
            case FIND_COMMAND:
                response = handleFind(parameters);
                break;
            case DONE_COMMAND:
                response = handleDone(input);
                break;
            case DELETE_COMMAND:
                response = handleDelete(input);
                break;
            case TODO_COMMAND:
                response = handleTodo(parameters);
                break;
            case DEADLINE_COMMAND:
                response = handleDeadline(parameters);
                break;
            case EVENT_COMMAND:
                response = handleEvent(parameters);
                break;
            case HELP_COMMAND:
                response = handleHelp();
                break;
            case HELLO_COMMAND:
                response = handleHello();
                break;
            default:
                throw new InvalidDukeCommandException();
            }
        } catch (DukeException e) {
            response = ui.formatError(e.toString());
        }

        return response;
    }

    private String handleHelp() {
        return ui.formatHelp();
    }

    private String handleHello() {
        return Ui.formatWelcomeMessage();
    }

    private String handleEvent(String parameters) throws InvalidDukeFormatException {
        String[] taskInfo = parameters.split("/at");

        String desc = "";
        String timestamp = "";
        if (taskInfo.length > 0) {
            desc = taskInfo[0].trim();
        }
        if (taskInfo.length > 1) {
            timestamp = taskInfo[1].trim();
        }

        if (DateTimeUtil.getInstance().isNatualDate(timestamp)) {
            timestamp = DateTimeUtil.getInstance().convertFromNatualDate(timestamp);
        }

        Task task = new EventTask(desc, timestamp);

        tasks.add(task);
        storage.save(tasks);
        return ui.formatNewTask(task, tasks.size());
    }

    private String handleDeadline(String parameters) throws InvalidDukeFormatException {
        String[] taskInfo = parameters.split("/by");
        String desc = "";
        String timestamp = "";
        if (taskInfo.length > 0) {
            desc = taskInfo[0].trim();
        }
        if (taskInfo.length > 1) {
            timestamp = taskInfo[1].trim();
        }

        if (DateTimeUtil.getInstance().isNatualDate(timestamp)) {
            timestamp = DateTimeUtil.getInstance().convertFromNatualDate(timestamp);
        }

        Task task = new DeadlineTask(desc, timestamp);
        tasks.add(task);
        storage.save(tasks);
        return ui.formatNewTask(task, tasks.size());

    }

    private String handleTodo(String param) throws InvalidDukeFormatException {
        Task task = new TodoTask(param);
        tasks.add(task);
        storage.save(tasks);
        return ui.formatNewTask(task, tasks.size());
    }

    private String handleDelete(String input) throws InvalidDukeFormatException {
        String[] splitted = input.split(" ");
        if (splitted.length < 2) {
            throw new InvalidDukeFormatException("The index of a delete cannot be empty.");
        }
        int taskId = Integer.parseInt(splitted[1]);
        if (taskId <= 0 || taskId > tasks.size()) {
            throw new InvalidDukeFormatException("Invalid task index provided!");
        }
        storage.save(tasks);
        return ui.formatRemoveTask(tasks.remove(taskId - 1));
    }

    private String handleDone(String input) throws InvalidDukeFormatException {
        String[] splitted = input.split(" ");
        if (splitted.length < 2) {
            throw new InvalidDukeFormatException("The index of a done cannot be empty.");
        }
        int taskId = Integer.parseInt(splitted[1]);
        if (taskId <= 0 || taskId > tasks.size()) {
            throw new InvalidDukeFormatException("Invalid task index provided!");
        }

        Task task = tasks.get(taskId - 1);
        task.markAsDone();
        storage.save(tasks);
        return ui.formatDoneTask(task);
    }

    private String handleFind(String parameters) {
        ArrayList<Task> copy = new ArrayList<>(tasks.getList());
        copy.removeIf((t -> !t.description.contains(parameters)));
        return ui.formatFilteredTasks(new TaskList(copy));
    }

    private void handleExit() {
        Platform.exit();
        System.exit(0);
    }

    private String handleList() {
        return ui.formatTasks(tasks);
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    private void handleUserInput() {

    }

    protected String getResponse(String input) {
        assert !input.isEmpty() : "Input cannot be empty!";
        return this.run(input);
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

        stage.setTitle("DUKE");
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

}
