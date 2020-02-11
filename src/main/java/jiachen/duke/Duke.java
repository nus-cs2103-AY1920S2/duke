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
  private Scene scene;

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

  /**
   * the Main entry point into the Duke program
   */
  public String run(String input) {

    String desc = "";
    String timestamp = "";


    String[] separateLine = input.split(" ", 2);
    String commandStr = separateLine[0];
    String parameters = separateLine.length > 1 ? separateLine[1] : "";

    Task task;
    String[] taskInfo;
    String[] splitted;
    int taskId;

    String response = "Hello!";

    try {

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
        default:
          throw new InvalidDukeCommandException();
      }
      switch (command) {
        case EXIT_COMMAND:
          Platform.exit();
          System.exit(0);
        case LIST_COMMAND:
          response = ui.formatTasks(tasks);
          break;
        case FIND_COMMAND:
          ArrayList<Task> copy = new ArrayList<>(tasks.getList());
          copy.removeIf((t -> !t.description.contains(parameters)));
          response = ui.formatFilteredTasks(new TaskList(copy));
          break;
        case DONE_COMMAND:
          splitted = input.split(" ");
          if (splitted.length < 2) {
            throw new InvalidDukeFormatException("The index of a done cannot be empty.");
          }
          taskId = Integer.parseInt(splitted[1]);
          if (taskId <= 0 || taskId > tasks.size()) {
            throw new InvalidDukeFormatException("Invalid task index provided!");
          }

          task = tasks.get(taskId - 1);
          task.markAsDone();
          response = ui.formatDoneTask(task);
          storage.save(tasks);
          break;
        case DELETE_COMMAND: {
          splitted = input.split(" ");
          if (splitted.length < 2) {
            throw new InvalidDukeFormatException("The index of a delete cannot be empty.");
          }
          taskId = Integer.parseInt(splitted[1]);
          if (taskId <= 0 || taskId > tasks.size()) {
            throw new InvalidDukeFormatException("Invalid task index provided!");
          }
        }
        response = ui.formatRemoveTask(tasks.remove(taskId - 1));
        storage.save(tasks);
        break;
        case TODO_COMMAND:
          task = new TodoTask(parameters);
          tasks.add(task);
          response = ui.formatNewTask(task, tasks.size());
          storage.save(tasks);
          break;
        case DEADLINE_COMMAND:
          taskInfo = parameters.split("/by");
          desc = "";
          timestamp = "";
          if (taskInfo.length > 0) {
            desc = taskInfo[0].trim();
          }
          if (taskInfo.length > 1) {
            timestamp = taskInfo[1].trim();
          }

          task = new DeadlineTask(desc, timestamp);

          tasks.add(task);
          response = ui.formatNewTask(task, tasks.size());
          storage.save(tasks);
          break;

        case EVENT_COMMAND:
          taskInfo = parameters.split("/at");

          if (taskInfo.length > 0) {
            desc = taskInfo[0].trim();
          }
          if (taskInfo.length > 1) {
            timestamp = taskInfo[1].trim();
          }

          task = new EventTask(desc, timestamp);

          tasks.add(task);
          response = ui.formatNewTask(task, tasks.size());
          storage.save(tasks);
          break;
      }
    } catch (DukeException e) {
      response = ui.formatError(e.toString());
    }

    return response;
  }

  /**
   * Iteration 1:
   * Creates a label with the specified text and adds it to the dialog container.
   *
   * @param text String containing text to add
   * @return a label with the specified text that has word wrap enabled.
   */
  private Label getDialogLabel(String text) {
    // You will need to import `javafx.scene.control.Label`.
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
            DialogBox.getUserDialog(userText.toString(), user),
            DialogBox.getDukeDialog(dukeText.toString(), duke)
    );
    userInput.clear();
  }

  /**
   * You should have your own function to generate a response to user input.
   * Replace this stub with your completed method.
   */
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

    userInput.setPrefWidth(325.0);

    sendButton.setPrefWidth(55.0);

    AnchorPane.setTopAnchor(scrollPane, 1.0);

    AnchorPane.setBottomAnchor(sendButton, 1.0);
    AnchorPane.setRightAnchor(sendButton, 1.0);

    AnchorPane.setLeftAnchor(userInput, 1.0);
    AnchorPane.setBottomAnchor(userInput, 1.0);

    scene = new Scene(mainLayout);

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
