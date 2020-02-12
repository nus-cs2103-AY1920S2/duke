import e0148811.duke.Deadline;
import e0148811.duke.DukeException;
import e0148811.duke.Event;
import e0148811.duke.Parser;
import e0148811.duke.Storage;
import e0148811.duke.Task;
import e0148811.duke.TaskList;
import e0148811.duke.Todo;
import e0148811.duke.Ui;

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

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(ui);
        try {
            ui.showLogo();
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } finally {
            ui.greet();
        }
    }

    public void run() {
        while (!parser.readInputLine().equals("bye")) {
            String[] instructionByWord = parser.breakIntoWords();
            try {
                int lengthOfArray = instructionByWord.length;
                String actionWord = instructionByWord[0].toLowerCase();
                switch (actionWord) {
                case "list":
                    printList(lengthOfArray);
                    break;
                case "todo":
                    Task task = createATodoTask(instructionByWord, lengthOfArray);
                    addAndStoreTask(task);
                    break;
                case "deadline":
                    task = createADeadlineTask(instructionByWord, lengthOfArray);
                    addAndStoreTask(task);
                    break;
                case "event":
                    task = createAnEventTask(instructionByWord, lengthOfArray);
                    addAndStoreTask(task);
                    break;
                case "done":
                    doneOrDeleteATask("done", instructionByWord, lengthOfArray);
                    break;
                case "delete":
                    doneOrDeleteATask("delete", instructionByWord, lengthOfArray);
                    break;
                case "find":
                    findTasks(instructionByWord[1], lengthOfArray);
                    break;
                case "clear":
                    clearList(instructionByWord, lengthOfArray);
                    break;
                case "":
                    ui.throwEmptyLineException();
                    break;
                default:
                    ui.throwUnknownCommandException();
                }
            } catch (DukeException | IOException e) {
                ui.printErrorMessage(e);
            }
        }
        ui.sayGoodbye();
    }

    void clearList(String[] instructionByWord, int lengthOfArray) throws DukeException, IOException {
        if (lengthOfArray != 2
                || !((instructionByWord[1].equals("all")) || (instructionByWord[1].equals("done")))) {
            ui.throwWrongFormatException("\"clear all/done\"");
        } else {
            String word = instructionByWord[1];
            if (word.equals("all")) {
                System.out.println("Are sure you want to clear all the tasks in the list?");
            } else {
                System.out.println("Are sure you want to clear all completed tasks in the list?");
            }
            System.out.println("Type \"yes\" or \"y\" to proceed. Type any other input to cancel.");
            String input = parser.readInputLine();
            if (input.equals("yes") || input.equals("y")) {
                if (word.equals("all")) {
                    tasks.clearTheList();
                    System.out.println("List is now empty.");
                } else {
                    tasks.cleanTheList();
                }
                storage.writeToHardDisk(tasks.getList());
            } else {
                System.out.println("Canceled.");
            }
        }
    }

    private void printList(int lengthOfArray) throws DukeException {
        if (lengthOfArray != 1) {
            ui.throwWrongFormatException("\"list\"");
        }
        tasks.printList();
    }

    void findTasks(String keyword, int lengthOfArray) throws DukeException {
        if (lengthOfArray != 2) {
            ui.throwWrongFormatException("\"find a_single_word_without_empty_space\"");
        }
        HashMap<Integer, Task> selectedList = new HashMap<>();
        int count = 0;
        for (int j = 0; j < tasks.getList().size(); j++) {
            Task task = tasks.getTask(j);
            String taskDescription = task.getDescription();
            String[] descriptionByWord = taskDescription.split(" ");
            for (String s : descriptionByWord) {
                if (s.equals(keyword)) {
                    selectedList.put(j + 1, task);
                    count++;
                    break;
                }
            }
        }
        System.out.println(count + " task(s) were found containing keyword " + keyword + " :");
        for (Integer index : selectedList.keySet()) {
            System.out.println(index + ". " + selectedList.get(index));
        }
    }

    private void doneOrDeleteATask(String command, String[] instructionByWord, int lengthOfArray) throws DukeException {
        if (lengthOfArray != 2) {
            ui.throwWrongFormatException(
                    "\"done a_positive_integer_indicating_the_index_of_the_task_done\"");
        }
        try {
            int index = Integer.parseInt(instructionByWord[1]) - 1;
            if (index >= tasks.getList().size() || index < 0) {
                throw new DukeException("Invalid index.\n" + tasks.getNumOfTasks()
                        + " Please note that the index is one-based (begins with 1 instead of 0).");
            } else {
                if (command.equals("done")) {
                    tasks.markATaskDone(index);
                } else {
                    tasks.deleteATask(index);
                }
            }
            storage.writeToHardDisk(tasks.getList());
        } catch (NumberFormatException e) {
            ui.throwWrongFormatException("\"done a_positive_integer\"");
        } catch (IOException e) {
            ui.throwIOException();
        }
    }

    void addAndStoreTask(Task t) throws IOException {
        tasks.addTaskToList(t);
        storage.writeToHardDisk(tasks.getList());
    }

    private Task createATodoTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        if (lengthOfArray == 1) {
            ui.throwWrongFormatException("\"todo a_string_describing_the_task\"");
        }
        String description = String.join(" ",
                Arrays.copyOfRange(instructionByWord, 1, lengthOfArray));
        return new Todo(description);
    }

    private Task createADeadlineTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        try {
            int indexOfBy = -1;
            for (int i = 1; i < lengthOfArray; i++) {
                if (instructionByWord[i].equals("/by")) {
                    indexOfBy = i;
                    break;
                }
            }
            if (indexOfBy == -1 || indexOfBy == 1 || indexOfBy == (lengthOfArray - 1)) {
                ui.throwWrongFormatException("\"deadline a_string_describing_the_task /by YYYY-MM-DD\"");
            }
            String description = String.join(" ",
                    Arrays.copyOfRange(instructionByWord, 1, indexOfBy));
            String deadline = String.join(" ",
                    Arrays.copyOfRange(instructionByWord, indexOfBy + 1, lengthOfArray));
            return new Deadline(description, LocalDate.parse(deadline));
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect format of date.\n"
                    + "The correct format should be YYYY-MM-DD.");
        } catch (DateTimeException e) {
            throw new DukeException("Invalid value for year/month/date");
        }
    }

    private Task createAnEventTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        try {
            int indexOfAt = -1;
            for (int i = 1; i < lengthOfArray; i++) {
                if (instructionByWord[i].equals("/at")) {
                    indexOfAt = i;
                }
            }
            if (indexOfAt == -1 || indexOfAt == 1 || indexOfAt == lengthOfArray) {
                ui.throwWrongFormatException("\"event a_string_describing_the_task /at YYYY-MM-DD\"");
            }
            String description = String.join(" ",
                    Arrays.copyOfRange(instructionByWord, 1, indexOfAt));
            String time = String.join(" ",
                    Arrays.copyOfRange(instructionByWord, indexOfAt + 1, lengthOfArray));
            return new Event(description, LocalDate.parse(time));
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect format of date.\n"
                    + "The correct format should be YYYY-MM-DD.");
        } catch (DateTimeException e) {
            throw new DukeException("Invalid value for year/month/date");
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // more code to be added here later
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
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}