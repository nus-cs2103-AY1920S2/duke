import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.*;
import java.time.format.DateTimeParseException;



/**
 * The Duke class contains the main method to update the TaskList and
 * execute the user's commands and input.
 */
public class Duke {
    public static final String LINE = "__________________________________________";
    private static Storage storage;
    private Ui ui;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * The empty constructor for Duke calls the Duke constructor that
     * takes in a String that contains the file path of the txt file
     * to update the TaskList in the txt file.
     */
    public Duke() throws IOException {
        this("data/duke.txt");
    }

    /**
     * Constructor for Duke takes in a String that contains the file path of the txt file.
     *
     * @param filePath The file path of the duke.txt file.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            File file = new File("data");
            if (!file.exists()) {
                new File("data").mkdir();
            }
            new File(filePath).createNewFile();
            // shown when current saved task list in the txt file is empty
            tasks = new TaskList();
            // therefore, there is a need to make a new task list.
        }
    }

    /**
     * This method is called to update the Tasks and print outputs based on different user inputs.
     */
    public void run() {
        assert tasks != null : "The list of tasks should not be null";
        assert ui != null : "The Ui object should not be null";
        assert storage != null : "The storage object should not be null";

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                if (isExit) {
                    break;
                }
            }
        }
    }

    /**
     * Duke's main method which serves to update the given txt file with the updated list of tasks.
     *
     * @param args A String[] input.
     * @throws DateTimeParseException if user's date input is not of 'yyyy-MM-dd' format.
     */
    public static void main(String[] args) throws DateTimeParseException, IOException {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream formattedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(formattedOutput));
        // Solution above adapted from
        // https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
        assert tasks != null : "The list of tasks should not be null";
        assert ui != null : "The Ui object should not be null";
        assert storage != null : "The storage object should not be null";

        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
        String reply = formattedOutput.toString();
        while (reply.contains(Duke.LINE)) {
            reply = reply.replace(Duke.LINE, "");
        }
        reply = reply.trim();
        System.setOut(System.out);
        return reply;
    }
}
