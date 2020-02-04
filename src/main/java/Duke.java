import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import CustomException.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



// These are not Strings; they are constant variables
/* enum Op {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    DONE,
    DELETE,
    BYE
} */

/**
 * Represents a chatbot to store list of things to do. A <code>Duke</code> object corresponds to
 * a chatbot that stores a list of things to do.
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
     * Creates a Duke object
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Needed for Application.launch() in Launcher to run
     */
    public Duke() {}


    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("./src/main/java/database.txt");
        duke.run();
    }

    /**
     * Starts running the Duke object
     * @throws IOException
     */
    public void run() throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ui.prompt(storage, tasks);
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });


        /*Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.*/
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
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

/**
 * Represents a task to be done. It stores the description of the task and whether it has been
 * done or not.
 */
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a symbol representing whether the task has been completed
     *
     * @return a tick (if done) or a cross (if not done)
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task
     *
     * @return a string representation of the task in the format "[X] Description"
     */
    @Override
    public String toString() {
        return "["
                + getStatusIcon()
                + "] "
                + description;
    }

    /**
     * Returns a string representation of the in the database format
     *
     * @return a string in the form "| 1 | description"
     */
    public String convert() {
        int done = isDone ? 1 : 0;
        return " | "
                + done
                + " | "
                + description;
    }

    public String getDescription() {
        return this.description;
    }
}

/**
 * Represents an Event Task. A <code>Event</code> object corresponds to an Event to attend
 */
class Event extends Task {
    protected LocalDate at;

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns a string representation of the in the database format
     *
     * @return a string in the form "| 1 | description (at: 20-08-09)"
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + at
                + ")";
    }

    /**
     * Returns a string representation of the in the database format
     *
     * @return a string in the form "| 1 | description | 2020-08-09"
     */
    public String convert() {
        return "E"
                + super.convert()
                + " | "
                + at;
    }
}

/**
 * Represents a wrapper for a File database. A <code>Storage</code> object corresponds to a database
 */
class Storage {
    private File database;

    public Storage(String fileName) {
        database = new File(fileName);
    }

    /**
     * Returns an array of String of all tasks read from the database
     *
     * @return an array of String
     * @throws IOException
     */
    // returns an array of String
    public ArrayList<String> load() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(database));
        ArrayList<String> contents = new ArrayList<>();
        String str;

        while ((str = br.readLine()) != null) {
            contents.add(str);
        }

        return contents;
    }

    /**
     * Writes from memory to the database
     *
     * @param list
     * @throws IOException
     */
    public void save(ArrayList<Task> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(database));

        for (Task task : list) {
            bw.write(task.convert() + "\n");
        }

        bw.flush();
    }
}

/**
 * Represents the task list in memory. A <code>TaskList</code> object corresponds to a task list
 */
class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<String> contents) throws DukeException {
        list = new ArrayList<>();
        load(contents);
    }

    /**
     * Parse all information read from the database into memory
     *
     * @param contents
     * @throws DukeException
     */
    private void load(ArrayList<String> contents) throws DukeException {
        try {
            for (String str : contents) {
                char type = str.charAt(0);
                boolean done = Boolean.parseBoolean(str.substring(4, 5));
                String description;
                String byAt;

                // To Do
                if (type == 'T') {
                    description = str.substring(8);
                    list.add(new ToDo(description, done));
                } else {
                    int lastIndex = findThirdStrike(str) - 1;
                    description = str.substring(8, lastIndex);
                    byAt = str.substring(lastIndex + 3);

                    if (type == 'D') {
                        list.add(new Deadline(description, done, LocalDate.parse(byAt)));
                    } else if (type == 'E') {
                        list.add(new Event(description, done, LocalDate.parse(byAt)));
                    }
                }
            }
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public void save(Storage storage) throws IOException {
        storage.save(list);
    }

    private int findThirdStrike(String str) {
        int count = 3;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '|') {
                count--;
            }

            if (count == 0) {
                return i;
            }
        }

        return -1;
    }

    public void display() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1)
                    + ". "
                    + list.get(i).toString());
        }
    }

    public void display(ArrayList<Integer> indices) {
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < indices.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(indices.get(i)).toString());
        }
    }

    public void add(String description, boolean isDone) {
        list.add(new ToDo(description, isDone));
    }

    public void add(char type, String description, boolean isDone, LocalDate date) {
        if (type == 'D') {
            list.add(new Deadline(description, isDone, date));
        } else if (type == 'E') {
            list.add(new Event(description, isDone, date));
        } else {
            System.out.println("Error: type must be 'D' or 'E'");
        }
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void markAsDone(int index) {
        list.get(index).markAsDone();
    }

    public Task delete(int index) {
        return list.remove(index);
    }

    public void find(String key) {
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String desc = list.get(i).getDescription();
            if (desc.contains(key)) {
                indices.add(i);
            }
        }

        display(indices);
    }
}

/**
 * Represents the main UI for communicating with user. A <code>Ui</code> object corresponds to a UI
 */
class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints when an exception is caught during loading
     */
    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    /**
     * Prints the task added when a task has been successfully added to the task list
     * @param tasks
     */
    private void printAdded(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have "
                + tasks.size()
                + "tasks in the list.");
    }

    /**
     * Main loop of the chatbot, continuously loops to ask for input until user inputs "bye"
     *
     * @param storage
     * @param tasks
     * @throws IOException
     */
    public void prompt(Storage storage, TaskList tasks) throws IOException {
        while (true) {
            boolean isBye = false;
            String input = sc.nextLine();
            String firstWord;

            // Guarantees 1st word is legitimate
            try {
                firstWord = Parser.getFirstWord(input);
            } catch (DukeException e) {
                System.out.println("Oops!! I'm sorry, but I don't know what that means :(");
                continue;
            }

            switch (firstWord) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isBye = true;
                break;
            case "list":
                tasks.display();
                break;
            case "todo":
                if (input.length() > 5) {
                    tasks.add(input.substring(5), false);

                    printAdded(tasks);
                    tasks.save(storage);
                } else {
                    System.out.println("No todo task specified!");
                }
                break;
            case "deadline":    // deadline description /yyyy-mm-dd
                if (input.length() > 9) {
                    try {
                        String description = Parser.getDescription(input, 9);
                        LocalDate date = Parser.getDate(input);

                        tasks.add('D', description, false, date);
                        printAdded(tasks);
                        tasks.save(storage);
                    } catch (Exception e) {
                        System.out.println("Error: incorrect format to add deadline task");
                    }
                } else {
                    System.out.println("No deadline task specified!");
                }
                break;
            case "event":   // event description /yyyy-mm-dd
                if (input.length() > 6) {
                    try {
                        String description = Parser.getDescription(input, 6);
                        LocalDate date = Parser.getDate(input);

                        tasks.add('E', description, false, date);
                        printAdded(tasks);
                        tasks.save(storage);
                    } catch (Exception e) {
                        System.out.println("Error: incorrect format to add deadline task");
                    }
                } else {
                    System.out.println("Error: no deadline task specified");
                }
                break;
            case "done":
                try {
                    int index = Parser.getIndex(input, "done");
                    tasks.markAsDone(index);

                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("\t" + tasks.get(index).toString());
                } catch (Exception e) {
                    System.out.println("Error: invalid (out of bounds) or non-integer entered");
                }
                break;
            case "delete":
                try {
                    int index = Parser.getIndex(input, "delete");
                    Task rm = tasks.delete(index);

                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("\t" + rm.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    tasks.save(storage);
                } catch (Exception e) {
                    System.out.println("Error: invalid (out of bounds) or non-integer entered");
                }
                break;
            case "find":
                if (input.length() > 5) {
                    String key = input.substring(5);
                    tasks.find(key);
                } else {
                    System.out.println("No search string specified!");
                }
                break;
            default:
                System.out.println("Oops!! I'm sorry, but I don't know what that means :(");
                break;
            }

            if (isBye) {
                break;
            }
        }
    }
}

/**
 * All static methods for parsing data are contained in this class
 */
class Parser {
    public Parser() {
    }

    /**
     * Returns a String of the first token input by the user
     *
     * @param input
     * @return String of first token input by user
     * @throws DukeException if firstWord is not recognised
     */
    public static String getFirstWord(String input) throws DukeException{
        int firstSpaceIndex = -1;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                firstSpaceIndex = i;
                break;
            }
        }

        String firstWord;
        if (firstSpaceIndex == -1) {
            firstWord = input;
        } else {
            firstWord = input.substring(0, firstSpaceIndex);
        }

        if (!firstWord.equals("todo") &&
                !firstWord.equals("deadline") &&
                !firstWord.equals("event") &&
                !firstWord.equals("list") &&
                !firstWord.equals("done") &&
                !firstWord.equals("delete") &&
                !firstWord.equals("bye") &&
                !firstWord.equals("find")) {
            throw new DukeException();
        }
        else {
            return firstWord;
        }
    }

    /**
     * Returns description of the task given an input and the starting index of the description
     *
     * @param input
     * @param start
     * @return description of the task
     * @throws DukeException
     */
    public static String getDescription(String input, int start) throws DukeException {
        int slashIndex = Parser.getSlash(input);
        return input.substring(start, slashIndex - 1);
    }

    /**
     * Returns the date in LocalDate format given an input
     *
     * @param input
     * @return date in LocalDate format
     * @throws DukeException
     */
    public static LocalDate getDate(String input) throws DukeException {
        int slashIndex = Parser.getSlash(input);
        return LocalDate.parse(input.substring(slashIndex + 1));
    }

    /**
     * Returns the index of the slash in the input given an input
     *
     * @param input
     * @return index of the slash which indicates the start of the date
     * @throws DukeException if there is no slash in input
     */
    private static int getSlash(String input) throws DukeException {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                return i;
            }
        }

        throw new DukeException();
    }

    /**
     * Returns the index of the task in the list
     *
     * @param input
     * @param type
     * @return int index of the task in the list
     * @throws DukeException
     */
    public static int getIndex(String input, String type) throws DukeException {
        int startPos;

        if (type.equals("done")) {
            startPos = 5;
        } else {
            startPos = 7;
        }

        try {
            String num = input.substring(startPos);
            return Integer.parseInt(num) - 1;
        } catch (Exception e) {
            throw new DukeException();
        }
    }
}
