import commands.Command;
import commands.CommandResult;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * The class representation of the AI bot that helps keep track of the user's todo list.
 */
public class Duke {
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


    public Duke() {
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getWelcomeMessage() {
//		return ui.createWelcomeMessage(tasks);
        return "jarvis here!";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
//        boolean isExit = false;
//        while(!isExit) {
//
//        }
        Command c = Parser.parse(input);
        boolean isExit = c.isExit();
        CommandResult output = c.execute(tasks, storage);
        if(isExit){
            System.out.println("Bye");
            System.exit(0);
        }

        return output.feedbackToUser;
    }

    /**
     * The main program execution that will take in the user's input and act on it.
     */
    public void run() {
        //  ui.showWelcomeMessage(tasks);
        System.out.println(ui.createWelcomeMessage(tasks));
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } /*catch (Exception e) {
                ui.showError(e.getMessage());
            } */ finally {
                ui.showLine();
            }
        }
        ui.showGoodbyeMessage();
    }

    /**
     * Main program that runs the Duke program.
     *
     * @param args Relevant command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


