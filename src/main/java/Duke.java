import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Duke is the main class of the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean isGoodbye = false;

    /*private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));*/

    public Duke() {

    }

    public String testing() {
        return "test";
    }

    /*@Override
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }*/

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    /*private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }*/

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }*/

    /*private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }*/


    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            //Load from storage
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /*public static void main(String[] args) throws IOException, DukeException {
        new Duke("data/tasks.txt").run();
    }*/


    /*public void run() throws IOException, DukeException {
        ui.welcome();
        String fullCommand = ui.readCommand();
        String newString = "";
        String[] splitBySpace;
        int num;
        Task t;
        String desc;
        String by;

        while (!fullCommand.equals("bye")) {
            try {
                Command cmd = parser.parseCommand(fullCommand);
                newString = "";
                desc = "";
                by = "";

                if (cmd != null) {
                    switch (cmd) {
                    case LIST:
                        if (tasks.getSize() == 0) {
                            System.out.println("The list is empty.");
                        } else {
                            for (int i = 0; i < tasks.getDukeList().size(); i++) {
                                System.out.println((i + 1) + "." + tasks.getDukeList().get(i));
                            }
                        }
                        break;
                    case DONE:
                        num = parser.parseNum(fullCommand, tasks);

                        if (num != -1) {
                            tasks.markDone(num);
                            ui.printMarkDone(tasks, num);

                        }
                        break;
                    case DELETE:
                        num = parser.parseNum(fullCommand, tasks);

                        if (num != -1) {
                            ui.printTaskRemoved(tasks, num);
                            tasks.removeTask(num);
                        }

                        break;
                    case TODO:
                        String tmp = parser.parseDescription(fullCommand);
                        t = new ToDo(tmp);
                        tasks.addTask(t);
                        ui.printTaskAdded(tasks, t);

                        break;
                    case EVENT:
                        desc = parser.parseDescOfEventDeadline(fullCommand);
                        by = parser.parseBy(fullCommand);

                        if (desc != null && by != null) {
                            t = new Event(desc, by);
                            tasks.addTask(t);
                            ui.printTaskAdded(tasks, t);
                        }

                        break;
                    case DEADLINE:
                        desc = parser.parseDescOfEventDeadline(fullCommand);
                        by = parser.parseBy(fullCommand);

                        if (desc != null && by != null) {
                            t = new Deadline(desc, by);
                            tasks.addTask(t);
                            ui.printTaskAdded(tasks, t);
                        }

                        break;
                    case FIND:
                        String find = parser.parseDescription(fullCommand);
                        String taskL = "";

                        for (int i = 0; i < tasks.getSize(); i++) {
                            Task cur = tasks.getDukeList().get(i);

                            if (cur.getDescription().contains(find)) {
                                taskL = taskL + (i+1) + "." + tasks.getDukeList().get(i) + "\n";
                            }
                        }

                        ui.printMatchingTask(taskL.trim(), find);

                        break;
                    default:
                        break;
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
            }

            //Get next input
            fullCommand = ui.readCommand();
        }

        storage.writeData(tasks);
        ui.goodbye();
        //getResponse("byeee");
        //return "baibai";
    } */

    public String run(String input) throws IOException, DukeException {
        String fullCommand = input;
        String newString = "";
        String[] splitBySpace;
        int num;
        Task t;
        String desc;
        String by;

        if (fullCommand.equals("bye")) {
            storage.writeData(tasks);
            isGoodbye = true;
            return ui.goodbye();
        }

        try {
            Command cmd = parser.parseCommand(fullCommand);
            newString = "";
            desc = "";
            by = "";

            switch (cmd) {
            case LIST:
                if (tasks.getSize() == 0) {
                    return "The list is empty.";
                } else {
                    String tmpList = "";
                    for (int i = 0; i < tasks.getDukeList().size(); i++) {
                        tmpList = tmpList + (i + 1) + "." + tasks.getDukeList().get(i) + "\n";
                    }
                    return tmpList;
                }
            case DONE:
                num = parser.parseNum(fullCommand, tasks);

                if (num > 0) {
                    if (num > tasks.getSize()) {
                        throw new DukeException("unable to mark done", num);
                    }

                    tasks.markDone(num);
                    return ui.printMarkDone(tasks, num);
                } else {
                    if (num == 0) {
                        throw new DukeException("unable to mark done", num);
                    } else if (num == -1) {
                        throw new DukeException("done");
                    } else {
                        throw new DukeException("done argument too much");
                    }
                }
            case DELETE:
                num = parser.parseNum(fullCommand, tasks);

                if (num > 0) {
                    if (num > tasks.getSize()) {
                        throw new DukeException("unable to delete from list", num);
                    }

                    Task taskToRemove = tasks.getDukeList().get(num - 1);
                    tasks.removeTask(num);
                    return ui.printTaskRemoved(taskToRemove, num, tasks);
                } else {
                    if (num == 0) {
                        throw new DukeException("unable to delete from list", num);
                    } else if (num == -1) {
                        throw new DukeException("delete");
                    } else {
                        throw new DukeException("delete argument not found");
                    }
                }
            case TODO:
                String tmp = parser.parseDescription(fullCommand);

                if (tmp.equals("-1Error:0b9d4e")) {
                    throw new DukeException("todo");
                }

                t = new ToDo(tmp);
                tasks.addTask(t);
                return ui.printTaskAdded(tasks, t);
            case EVENT:
                desc = parser.parseDescOfEventDeadline(fullCommand);
                if (desc.equals("-1Error:21006a")) {
                    throw new DukeException("event");
                }

                by = parser.parseBy(fullCommand);
                if (by.equals("-2error:21f3ad")) {
                    throw new DukeException("event", "", "no slash");
                }

                t = new Event(desc, by);
                tasks.addTask(t);
                return ui.printTaskAdded(tasks, t);

            case DEADLINE:
                desc = parser.parseDescOfEventDeadline(fullCommand);
                if (desc.equals("-1Error:21006a")) {
                    throw new DukeException("deadline");
                }
                by = parser.parseBy(fullCommand);
                if (by.equals("-2error:21f3ad")) {
                    throw new DukeException("deadline", "", "no slash");
                }

                t = new Deadline(desc, by);
                tasks.addTask(t);
                return ui.printTaskAdded(tasks, t);
            case FIND:
                String find = parser.parseDescription(fullCommand);
                if (find.equals("-1Error:0b9d4e")) {
                    throw new DukeException("find");
                }
                String taskL = "";

                for (int i = 0; i < tasks.getSize(); i++) {
                    Task cur = tasks.getDukeList().get(i);

                    if (cur.getDescription().contains(find)) {
                        taskL = taskL + (i+1) + "." + tasks.getDukeList().get(i) + "\n";
                    }
                }

                return ui.printMatchingTask(taskL.trim(), find);
            case ENTERCOMMAND:
                throw new DukeException("enter command");
            default:
                throw new DukeException("Don't understand");
            }

        } catch (DukeException e) {
            return e.toString();
        }

    }

    public Ui getUi() {
        return ui;
    }

    public boolean toClose() {
        if (isGoodbye) {
            return true;
        } else {
            return false;
        }
    }
}

