package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Duke has run method that runs the duke programme and is initalised by the filePath. */
public class Duke {

  private Storage storage;
  private TaskList tasks;
  private Ui ui;
  private ScrollPane scrollPane;
  private VBox dialogContainer;
  private TextField userInput;
  private Button sendButton;
  private Scene scene;
  private Image user = new Image(this.getClass().getResourceAsStream("/images/Me.jpg"));
  private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpeg"));

  public Duke(String filePath) {
    ui = new Ui();
    try {
      storage = new Storage(filePath);
      tasks = new TaskList(storage.loadData());
    } catch (DukeException e) {
      ui.showError(e.getMessage());
      tasks = new TaskList();
    }
  }

  public Duke() {
    super();
  }

  /**
   * You should have your own function to generate a response to user input. ideas: instead of just
   * printing the response
   */
  public String getResponse(String input) {
    try {
      Command c = Parser.parse(input);
      return c.executeAndGetResponse(tasks, ui, storage);
    } catch (DukeException e) {
      return e.getMessage();
    }
  }

  public void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        ui.showLine();
        Command c = Parser.parse(fullCommand);
        c.execute(tasks, ui, storage);
        isExit = c.isExit();
      } catch (DukeException e) {
        ui.showError(e.getMessage());
      } finally {
        ui.showLine();
      }
    }
  }

  public static void main(String[] args) {
    new Duke("../../../data/duke.txt").run();
  }
}
