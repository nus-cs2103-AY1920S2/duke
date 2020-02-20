import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private File dataFile;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/smudge.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/jersey.jpg"));

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
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        DialogBox.getDukeDialog("HELLO", (duke));
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

    public Duke() {

    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        dataFile = new File(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InstantiationError e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
        String userText = (userInput.getText());
        String dukeText = (getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, (user)),
                DialogBox.getDukeDialog(dukeText, (duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {

        /**
         * Runs the Duke program by writing and loading from data.txt.
         *
         * Load data from data.txt
         * Store data locally
         * Update local data based on userInput
         * Give response
         * Write the local data to data.txt
         */
        Duke duke = new Duke("./" + "data/duke.txt");
        Parser parser = new Parser();
        String parserOutput = parser.parse(userInput);
        ArrayList<Task> arr = duke.tasks.getArraylist(); // returns the arraylist form of the tasklist
        ArrayList<String> taskNames = duke.tasks.getNames(arr);
        ArrayList<Task> foundTasks = new ArrayList<Task>();

        switch (parserOutput) {

            case ("list"):
                return ("Here are the tasks in your list: " + duke.tasks.print());
            //break;

            case ("done"):
                int doneTask = Integer.parseInt(userInput.split("\\s")[1]);
                arr.get(doneTask - 1).setDone();
                storage.fileUpdate(dataFile, arr);
                return ("Nice! I've marked this task as done: \n" + arr.get(doneTask - 1).status + " " + arr.get(doneTask - 1).getDescription());
            //break;
            case ("todo"):
                if (userInput.split("\\s").length == 1) {
                    return ("&#x2639; OOPS!!! The description of a todo cannot be empty.");
                } else {
                    String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. from [to do, return, book] -> [return, book]
                    String taskDescription = "";
                    for (int i = 0; i < taskRequest.length; i++) {
                        if (i == 0) {
                            taskDescription += taskRequest[i];
                        } else {
                            taskDescription += " " + taskRequest[i];
                        }
                    }
                    Task curr = new Task("todo", taskDescription);
                    arr.add(curr);
                    storage.fileUpdate(dataFile, arr);

                    return ("Got it. I've added this task:\n" + curr.getIcon() + curr.status + " " + curr.getDescription() + "\n" + "Now you have " + arr.size() + " tasks in the list.");

                }
                //break;
            case ("deadline"):
                if (userInput.split("\\s").length == 1) {
                    return ("&#x2639; OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. from [deadline, return, book] -> [return, book]
                    String taskDescriptionDate = "";
                    for (int i = 0; i < taskRequest.length; i++) {
                        if (i == 0) {
                            taskDescriptionDate += taskRequest[i];
                        } else {
                            taskDescriptionDate += " " + taskRequest[i];
                        }
                    }
                    String taskDescription = taskDescriptionDate.split("/")[0];
                    Task curr = new Task("deadline", taskDescription);
                    curr.addDate(taskDescriptionDate.split("/")[1]);
                    arr.add(curr);
                    storage.fileUpdate(dataFile, arr);
                    return ("Got it. I've added this task:\n" + curr.getIcon() + curr.status + " " + curr.getDescription() + "\n" + "Now you have " + arr.size() + " tasks in the list.");

                }
                //break;
            case ("event"):
                if (userInput.split("\\s").length == 1) {
                    return ("&#x2639; OOPS!!! The description of an event cannot be empty.");
                } else {
                    String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. from [event, return, book] -> [return, book]
                    String taskDescriptionDate = "";
                    for (int i = 0; i < taskRequest.length; i++) {
                        if (i == 0) {
                            taskDescriptionDate += taskRequest[i];
                        } else {
                            taskDescriptionDate += " " + taskRequest[i];
                        }
                    }
                    String taskDescription = taskDescriptionDate.split("/")[0];
                    Task curr = new Task("event", taskDescription);
                    curr.addDate(taskDescriptionDate.split("/")[1]);
                    arr.add(curr);
                    storage.fileUpdate(dataFile, arr);

                    return ("Got it. I've added this task:\n" + curr.getIcon() + curr.status + " " + curr.getDescription() + "\n" + "Now you have " + arr.size() + " tasks in the list.");

                }
                //break;
            case ("delete"):
                int removedTask = Integer.parseInt(userInput.split("\\s")[1]);
                Task removed = arr.get(removedTask - 1);
                arr.remove(removedTask - 1);
                storage.fileUpdate(dataFile, arr);
                return ("Noted. I've removed this task:\n" + removed.getIcon() + removed.status + " " + removed.getDescription() + "\n" + "Now you have " + arr.size() + " tasks in the list.");
            //break;
            case ("find"):
                for (int i = 0; i < taskNames.size(); i++) {
                    String[] currentTaskName = taskNames.get(i).split("\\s");
                    for (int j = 0; j < currentTaskName.length; j++) {
                        if (currentTaskName[j].equals(parser.getSearchQuery())) {
                            foundTasks.add(arr.get(i));
                        }
                    }
                    assert i >= taskNames.size() : "error";
                }
                String str = "";
                for (int i = 0; i < foundTasks.size(); i++) {
                    int j = i + 1;
                    if (i == 0) {
                        str += (j + "." + foundTasks.get(i).getIcon() + foundTasks.get(i).status + " " + foundTasks.get(i).getDescription());
                    } else {
                        str += "\n" + (j + "." + foundTasks.get(i).getIcon() + foundTasks.get(i).status + " " + foundTasks.get(i).getDescription());
                    }
                }
                return str;
            case ("priority"):
                for (int i = 0; i < taskNames.size(); i++) {
                    String[] currentTaskName = taskNames.get(i).split("\\s");
                    for (int j = 0; j < currentTaskName.length; j++) {
                        if (currentTaskName[j].equals(parser.getSearchQuery())) {
                            foundTasks.add(arr.get(i));
                        }
                    }
                    assert i >= taskNames.size() : "error";
                }
                for (int i = 0; i < foundTasks.size(); i++) {
                    foundTasks.get(i).setPriority(1);
                }
                return "Priority Set!";
            case ("bye"):
                return ("Bye. Hope to see you again soon!");
            //break;
            default:
                return ("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

//        do {
//            Parser parser = new Parser();
//            String parserOutput = parser.parse(userInput);                // gives parser the user command
//            ArrayList <Task> arr = tasks.getArraylist(); // returns the arraylist form of the tasklist
//            ArrayList <String> taskNames = tasks.getNames(arr);
//            ArrayList <Task> foundTasks = new ArrayList <Task>();
//            switch (parserOutput) {
//
//                case ("list"):
//                    return ("Here are the tasks in your list: " + tasks.print());
//                    //break;
//
//                case ("done"):
//                    int doneTask = Integer.parseInt(userInput.split("\\s")[1]);
//                    arr.get(doneTask - 1).setDone();
//                    storage.fileUpdate(this.dataFile, arr);
//                    return ("Nice! I've marked this task as done: \n" + arr.get(doneTask - 1).status + " " + arr.get(doneTask - 1).getDescription());
//                    //break;
//                case ("todo"):
//                    if (userInput.split("\\s").length == 1) {
//                        return ("&#x2639; OOPS!!! The description of a todo cannot be empty.");
//                    } else {
//                        String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
//                        String taskDescription = "";
//                        for (int i = 0; i < taskRequest.length; i++) {
//                            if (i == 0) {
//                                taskDescription += taskRequest[i];
//                            } else {
//                                taskDescription += " " + taskRequest[i];
//                            }
//                        }
//                        Task curr = new Task("todo", taskDescription);
//                        arr.add(curr);
//                        storage.fileUpdate(dataFile, arr);
//
//                        return ("Got it. I've added this task:\n" + curr.getIcon() + curr.status + " " + curr.getDescription() + "\n" + "Now you have " + arr.size() + " tasks in the list.");
//
//                    }
//                    //break;
//                case ("deadline"):
//                    if (userInput.split("\\s").length == 1) {
//                        return ("&#x2639; OOPS!!! The description of a deadline cannot be empty.");
//                    }
//                    else {
//                        String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
//                        String taskDescriptionDate = "";
//                        for (int i = 0; i < taskRequest.length; i++) {
//                            if (i == 0) {
//                                taskDescriptionDate += taskRequest[i];
//                            } else {
//                                taskDescriptionDate += " " + taskRequest[i];
//                            }
//                        }
//                        String taskDescription = taskDescriptionDate.split("/")[0];
//                        Task curr = new Task("deadline", taskDescription);
//                        curr.addDate(taskDescriptionDate.split("/")[1]);
//                        arr.add(curr);
//                        storage.fileUpdate(dataFile, arr);
//                        return ("Got it. I've added this task:\n" + curr.getIcon() + curr.status + " " + curr.getDescription() + "\n" + "Now you have " + arr.size() + " tasks in the list.");
//
//                    }
//                    //break;
//                case ("event"):
//                    if (userInput.split("\\s").length == 1) {
//                        return ("&#x2639; OOPS!!! The description of an event cannot be empty.");
//                    } else {
//                        String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
//                        String taskDescriptionDate = "";
//                        for (int i = 0; i < taskRequest.length; i++) {
//                            if (i == 0) {
//                                taskDescriptionDate += taskRequest[i];
//                            } else {
//                                taskDescriptionDate += " " + taskRequest[i];
//                            }
//                        }
//                        String taskDescription = taskDescriptionDate.split("/")[0];
//                        Task curr = new Task("event", taskDescription);
//                        curr.addDate(taskDescriptionDate.split("/")[1]);
//                        arr.add(curr);
//                        storage.fileUpdate(dataFile, arr);
//
//                        return ("Got it. I've added this task:\n" + curr.getIcon() + curr.status + " " + curr.getDescription() + "\n" + "Now you have " + arr.size() + " tasks in the list.");
//
//                    }
//                    //break;
//                case ("delete"):
//                    int removedTask = Integer.parseInt(userInput.split("\\s")[1]);
//                    Task removed = arr.get(removedTask - 1);
//                    arr.remove(removedTask - 1);
//                    storage.fileUpdate(dataFile, arr);
//                    return ("Noted. I've removed this task:\n" + removed.getIcon() + removed.status + " " + removed.getDescription() + "\n" + "Now you have " + arr.size() + " tasks in the list.");
//                    //break;
//                case ("find"):
//                    for (int i = 0; i < taskNames.size(); i++) {
//                        String[] currentTaskName = taskNames.get(i).split("\\s");
//                        for (int j = 0; j < currentTaskName.length; j++) {
//                            if (currentTaskName[j].equals(parser.getSearchQuery())) {
//                                foundTasks.add(arr.get(i));
//                            }
//                        }
//                        assert i >= taskNames.size() : "error";
//                    }
//                    String str = "";
//                    for (int i = 0; i < foundTasks.size(); i++) {
//                        int j = i + 1;
//                        str += (j + "." + foundTasks.get(i).getIcon() + foundTasks.get(i).status + " " + foundTasks.get(i).getDescription());
//                    }
//                    return str;
//                case ("priority"):
//                    for (int i = 0; i < taskNames.size(); i++) {
//                        String[] currentTaskName = taskNames.get(i).split("\\s");
//                        for (int j = 0; j < currentTaskName.length; j++) {
//                            if (currentTaskName[j].equals(parser.getSearchQuery())) {
//                                foundTasks.add(arr.get(i));
//                            }
//                        }
//                        assert i >= taskNames.size() : "error";
//                    }
//                    for (int i = 0; i < foundTasks.size(); i++) {
//                        foundTasks.get(i).setPriority(1);
//                    }
//                    return "Priority Set!";
//                case ("bye"):
//                    return ("Bye. Hope to see you again soon!");
//                    //break;
//                default:
//                    return ("OOPS!!! I'm sorry, but I don't know what that means :-(");
//            }
//        } while (!userInput.equals("bye"));
    }

}



//    public void run() {
//        Scanner sc = new Scanner(System.in);
//        String userInput = "";
//        this.ui.runGreeting();
//        do {
//            userInput = sc.nextLine();
//            Parser parser = new Parser();
//            String parserOutput = parser.parse(userInput);                // gives parser the user command
//            ArrayList <Task> arr = tasks.getArraylist(); // returns the arraylist form of the tasklist
//            ArrayList <String> taskNames = tasks.getNames(arr);
//            ArrayList <Task> foundTasks = new ArrayList <Task>();
//            switch (parserOutput) {
//
//                case ("list"):
//                    System.out.println("Here are the tasks in your list: ");
//                    tasks.print();  // sysout all the items in the tasklist
//                    break;
//
//                case ("done"):
//                    int doneTask = Integer.parseInt(userInput.split("\\s")[1]);
//                    arr.get(doneTask - 1).setDone();
//                    System.out.println("Nice! I've marked this task as done:");
//                    System.out.println(arr.get(doneTask - 1).status + " " + arr.get(doneTask - 1).getDescription());
//
//                    // write the new updated arr to the duke.txt
//                    storage.fileUpdate(this.dataFile, arr);
//                    break;
//                case ("todo"):
//                    if (userInput.split("\\s").length == 1) {
//                        System.out.println("&#x2639; OOPS!!! The description of a todo cannot be empty.");
//                    } else {
//                        String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
//                        String taskDescription = "";
//                        for (int i = 0; i < taskRequest.length; i++) {
//                            if (i == 0) {
//                                taskDescription += taskRequest[i];
//                            } else {
//                                taskDescription += " " + taskRequest[i];
//                            }
//                        }
//                        Task curr = new Task("todo", taskDescription);
//                        arr.add(curr);
//                        storage.fileUpdate(dataFile, arr);
//                        if (!userInput.equals("bye")) {
//                            System.out.println("Got it. I've added this task:");
//                            System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
//                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
//                        }
//                    }
//                    break;
//                case ("deadline"):
//                    if (userInput.split("\\s").length == 1) {
//                        System.out.println("&#x2639; OOPS!!! The description of a deadline cannot be empty.");
//                    }
//                    else {
//                        String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
//                        String taskDescriptionDate = "";
//                        for (int i = 0; i < taskRequest.length; i++) {
//                            if (i == 0) {
//                                taskDescriptionDate += taskRequest[i];
//                            } else {
//                                taskDescriptionDate += " " + taskRequest[i];
//                            }
//                        }
//                        String taskDescription = taskDescriptionDate.split("/")[0];
//                        Task curr = new Task("deadline", taskDescription);
//                        curr.addDate(taskDescriptionDate.split("/")[1]);
//                        arr.add(curr);
//                        storage.fileUpdate(dataFile, arr);
//                        if (!userInput.equals("bye")) {
//                            System.out.println("Got it. I've added this task:");
//                            System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
//                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
//                        }
//                    }
//                    break;
//                case ("event"):
//                    if (userInput.split("\\s").length == 1) {
//                        System.out.println("&#x2639; OOPS!!! The description of an event cannot be empty.");
//                    } else {
//                        String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
//                        String taskDescriptionDate = "";
//                        for (int i = 0; i < taskRequest.length; i++) {
//                            if (i == 0) {
//                                taskDescriptionDate += taskRequest[i];
//                            } else {
//                                taskDescriptionDate += " " + taskRequest[i];
//                            }
//                        }
//                        String taskDescription = taskDescriptionDate.split("/")[0];
//                        Task curr = new Task("event", taskDescription);
//                        curr.addDate(taskDescriptionDate.split("/")[1]);
//                        arr.add(curr);
//                        storage.fileUpdate(dataFile, arr);
//                        if (!userInput.equals("bye")) {
//                            System.out.println("Got it. I've added this task:");
//                            System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
//                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
//                        }
//                    }
//                    break;
//                case ("delete"):
//                    int removedTask = Integer.parseInt(userInput.split("\\s")[1]);
//                    System.out.println("Noted. I've removed this task:");
//                    System.out.println(arr.get(removedTask - 1).getIcon() + arr.get(removedTask - 1).status + " " + arr.get(removedTask - 1).getDescription());
//                    arr.remove(removedTask - 1);
//                    System.out.println("Now you have " + arr.size() + " tasks in the list.");
//                    storage.fileUpdate(dataFile, arr);
//                    break;
//                case ("find"):
//                    for (int i = 0; i < taskNames.size(); i++) {
//                        String[] currentTaskName = taskNames.get(i).split("\\s");
//                        for (int j = 0; j < currentTaskName.length; j++) {
//                            if (currentTaskName[j].equals(parser.getSearchQuery())) {
//                                foundTasks.add(arr.get(i));
//                            }
//                        }
//                        assert i >= taskNames.size() : "error";
//                    }
//                    for (int i = 0; i < foundTasks.size(); i++) {
//                        int j = i + 1;
//                        System.out.println(j + "." + foundTasks.get(i).getIcon() + foundTasks.get(i).status + " " + foundTasks.get(i).getDescription());
//                    }
//                    break;
//                case ("priority"):
//                    for (int i = 0; i < taskNames.size(); i++) {
//                        String[] currentTaskName = taskNames.get(i).split("\\s");
//                        for (int j = 0; j < currentTaskName.length; j++) {
//                            if (currentTaskName[j].equals(parser.getSearchQuery())) {
//                                foundTasks.add(arr.get(i));
//                            }
//                        }
//                        assert i >= taskNames.size() : "error";
//                    }
//                    for (int i = 0; i < foundTasks.size(); i++) {
//                        foundTasks.get(i).setPriority(1);
//                    }
//                    break;
//                case ("bye"):
//                    System.out.println("Bye. Hope to see you again soon!");
//                    break;
//                default:
//                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
//            }
//        } while (!userInput.equals("bye"));
//    }
//

//    public static void main(String[] args) {
//        new Duke("./" + "data/duke.txt").run();
//    }
//}















/*
public class Duke {

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("./" + "data/duke.txt",true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void fileUpdate(File dataFile, ArrayList <Task> arr) {
        try {
            dataFile.delete();
            for (int i = 0; i < arr.size(); i++) {
                writeToFile(arr.get(i).getIcon() + " | " + arr.get(i).getStatusBinary() + " | " + arr.get(i).getDescription() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        ArrayList <Task> arr = new ArrayList<Task>();
        File dataFile = new File("./" + "data/duke.txt");

        do {
            userInput = sc.nextLine();

            // LIST
            if (userInput.equals("list")) {                                                  //Lists out the tasks
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i <= arr.size(); i++) {
                    System.out.println(i + "." + arr.get(i - 1).getIcon() + arr.get(i - 1).status + " " + arr.get(i - 1).getDescription());
                }
            }

            // DONE
            else if (userInput.split("\\s")[0].equals("done")) {
                int doneTask = Integer.parseInt(userInput.split("\\s")[1]);
                arr.get(doneTask - 1).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(arr.get(doneTask - 1).status + " " + arr.get(doneTask - 1).getDescription());
                fileUpdate(dataFile, arr);
            }

            // TO DO
            else if (userInput.split("\\s")[0].equals("todo")) {
                if (userInput.split("\\s").length == 1) {
                    System.out.println("&#x2639; OOPS!!! The description of a todo cannot be empty.");
                }
                else {
                    String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
                    String taskDescription = "";
                    for (int i = 0; i < taskRequest.length; i++) {
                        if (i == 0) {
                            taskDescription += taskRequest[i];
                        } else {
                            taskDescription += " " + taskRequest[i];
                        }
                    }
                    Task curr = new Task("todo", taskDescription);
                    arr.add(curr);
                    fileUpdate(dataFile, arr);
                    if (!userInput.equals("bye")) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    }
                }
            }

            // DEADLINE
            else if (userInput.split("\\s")[0].equals("deadline")) {
                if (userInput.split("\\s").length == 1) {
                    System.out.println("&#x2639; OOPS!!! The description of a deadline cannot be empty.");
                }
                else {
                    String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
                    String taskDescriptionDate = "";
                    for (int i = 0; i < taskRequest.length; i++) {
                        if (i == 0) {
                            taskDescriptionDate += taskRequest[i];
                        } else {
                            taskDescriptionDate += " " + taskRequest[i];
                        }
                    }
                    String taskDescription = taskDescriptionDate.split("/")[0];
                    Task curr = new Task("deadline", taskDescription);
                    curr.addDate(taskDescriptionDate.split("/")[1]);
                    arr.add(curr);
                    fileUpdate(dataFile, arr);
                    if (!userInput.equals("bye")) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    }
                }
            }

            // EVENT
            else if (userInput.split("\\s")[0].equals("event")) {
                if (userInput.split("\\s").length == 1) {
                    System.out.println("&#x2639; OOPS!!! The description of an event cannot be empty.");
                }
                else {
                    String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
                    String taskDescriptionDate = "";
                    for (int i = 0; i < taskRequest.length; i++) {
                        if (i == 0) {
                            taskDescriptionDate += taskRequest[i];
                        } else {
                            taskDescriptionDate += " " + taskRequest[i];
                        }
                    }
                    String taskDescription = taskDescriptionDate.split("/")[0];
                    Task curr = new Task("event", taskDescription);
                    curr.addDate(taskDescriptionDate.split("/")[1]);
                    arr.add(curr);
                    fileUpdate(dataFile, arr);
                    if (!userInput.equals("bye")) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    }
                }
            }

            //DELETE
            else if (userInput.split("\\s")[0].equals("delete")) {
                int removedTask = Integer.parseInt(userInput.split("\\s")[1]);
                System.out.println("Noted. I've removed this task:");
                System.out.println(arr.get(removedTask - 1).getIcon() + arr.get(removedTask - 1).status + " " + arr.get(removedTask - 1).getDescription());
                arr.remove(removedTask - 1);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                fileUpdate(dataFile, arr);
            }
            else {
                System.out.println("&#x2639; OOPS!!! I'm sorry, but I don't know what that means :-(");
            }


        } while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}

*/