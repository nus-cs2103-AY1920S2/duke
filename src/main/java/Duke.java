import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class contains the main method.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));



    String getResponse(String command) throws IOException{
        String response;
        if (!command.equals("bye")) {
            try {
                response = handle2(command);
            }
            catch (DukeException ex) {
                ui.showError(ex.getMessage());
                response = ex.getMessage();
            }

        } else {
            storage.saveData();
            response = "Bye. Hope to see you again soon!";
        }
        return response;
    }

    /**
     * The constructor for the Duke class.
     */
    public Duke() throws IOException, DukeException {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
        parser = new Parser();
        storage.loadData();
    }

    /**
     * The main method for Duke.
     * @param args For the main method.
     * @throws DukeException Throws DukeException.
     * @throws IOException Throws IOException.
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }


    /**
     * To run the duke program.
     * @throws DukeException Throws DukeException.
     * @throws IOException Throws IOException.
     */
    public void run() throws DukeException, IOException {
        storage.loadData();
        System.out.println(ui.welcomeMessage());
        System.out.println("What can I do for you");

        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                handle(command);
            }
            catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }

        }
        storage.saveData();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Handles the command provided by the user.
     * @param command The user command.
     * @throws DukeException Throws DukeException.
     */
    public void handle(String command) throws DukeException{
        if (command.equals("list")) {
            System.out.println(ui.printList());
        } else {
            String type = parser.getType(command);
            if (type.equals("done") || type.equals("delete")) {
                int taskNo = parser.getTaskIndex(command);
                if (taskNo > tasks.numOfTasks() || taskNo <= 0) {
                    throw new DukeException("\u2639 OOPS!! Not a valid number");
                } else {
                    if (type.equals("done")) {
                        tasks.getTask(taskNo - 1).markAsDone();
                        System.out.println(ui.doneMessage(tasks.getTask(taskNo - 1)));
                    } else {
                        System.out.println(ui.deleteMessage(tasks.getTask(taskNo - 1)));
                        tasks.removeTask(taskNo - 1);
                        Task.totalTasks--;
                    }
                }
            } else if (type.equals("find")) {
                ArrayList<Integer> taskFound = new ArrayList<>();
                for (int i = 0; i < tasks.numOfTasks(); i++) {
                    if (tasks.getTask(i).description.contains(command.split(" ")[1])) {
                        taskFound.add(i);
                    }
                }
                System.out.println(ui.printSelected(taskFound));
            } else {
                Task task = tasks.createAndAddTask(type, command);
                System.out.println(ui.addMessage(task));
            }
        }
    }

    public String handle2(String command) throws DukeException{
        String reply;
        if (command.equals("list")) {
            reply = ui.printList();
        } else {
            String type = parser.getType(command);
            if (type.equals("done") || type.equals("delete")) {
                int taskNo = parser.getTaskIndex(command);
                if (taskNo > tasks.numOfTasks() || taskNo <= 0) {
                    throw new DukeException("\u2639 OOPS!! Not a valid number");
                } else {
                    if (type.equals("done")) {
                        tasks.getTask(taskNo - 1).markAsDone();
                        reply = ui.doneMessage(tasks.getTask(taskNo - 1));
                    } else {
                        reply = ui.deleteMessage(tasks.getTask(taskNo - 1));
                        tasks.removeTask(taskNo - 1);
                        Task.totalTasks--;
                    }
                }
            } else if (type.equals("find")) {
                ArrayList<Integer> taskFound = new ArrayList<>();
                for (int i = 0; i < tasks.numOfTasks(); i++) {
                    if (tasks.getTask(i).description.contains(command.split(" ")[1])) {
                        taskFound.add(i);
                    }
                }
                reply = ui.printSelected(taskFound);
            } else {
                Task task = tasks.createAndAddTask(type, command);
                reply = ui.addMessage(task);
            }
        }
        return reply;
    }
}
