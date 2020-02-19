import java.io.*;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public  class Duke{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image charImg = new Image(this.getClass().getResourceAsStream("/images/charmander.jpg"));
    private Image redImg = new Image(this.getClass().getResourceAsStream("/images/red.jpg"));

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke object to run the main program
     */
    public Duke() {
        storage = new Storage("save.txt");
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            Ui.showError(e);
            tasks = new TaskList();
        }


    }

    /**
     * Runs the main program in cmd
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        Ui.showStart();

        boolean isLooping = true;
        while (isLooping) {
            try {
                String input = sc.nextLine();
                Command comm = Parser.parseCommand(input);

                int index;

                switch (comm) {
                case BYE:
                    isLooping = false;

                    Ui.showExit();
                    break;
                case LIST:

                    Ui.showList(tasks);
                    break;
                case DONE:
                    index = Parser.parseIndex(input);
                    tasks.done(index);
                    storage.save(tasks);

                    Ui.showDone(tasks.get(index));
                    break;
                case DELETE:
                    index = Parser.parseIndex(input);
                    Task deleted = tasks.delete(index);
                    storage.save(tasks);

                    Ui.showDelete(deleted);
                    break;
                case FIND:
                    String keyword = Parser.parseWord(input);
                    TaskList found = tasks.find(keyword);

                    Ui.showFound(found);
                    break;
                default:
                    Task newTask = Parser.parseTask(input);
                    tasks.add(newTask);
                    storage.save(tasks);

                    Ui.showAdd(newTask);
                    break;
                }
            } catch (DukeException e) {
                Ui.showError(e);
            }
        }
    }
/*
    public static void main(String[] args) throws FileNotFoundException{
        new Duke().run();
    }
*/

    @FXML
    protected String getResponse(String input) {
        try {
            Command comm = Parser.parseCommand(input);

            int index;

            switch (comm) {
            case BYE:
                return "BYE";
            case LIST:
                return "Charmander presents the list to you:\n" +
                        tasks +
                        "Charmander hopes you liked it!";
            case DONE:
                index = Parser.parseIndex(input);
                tasks.done(index);
                storage.save(tasks);

                return "Charmander ticks out the task.\n" + tasks.get(index);
            case DELETE:
                index = Parser.parseIndex(input);
                Task deleted = tasks.delete(index);
                storage.save(tasks);
                return "Charmander used delete on the task\n" +
                        deleted + "\n" +
                        "it's super effective!";
            case FIND:
                String keyword = Parser.parseWord(input);
                TaskList found = tasks.find(keyword);
                return "Charmander found the following tasks:\n" + tasks;
            case UNDO:
                tasks = storage.undo();
                return "Charmander undoes your last command.";
            default:
                Task newTask = Parser.parseTask(input);
                tasks.add(newTask);
                storage.save(tasks);
                return "Charmander writes a task. You peek over and it says:\n" + newTask;
            }
        } catch (DukeException e) {
            Ui.showError(e);
            return e.toString();
        }
    }
}
