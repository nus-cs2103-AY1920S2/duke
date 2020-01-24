/*
 * @author leslieharland
 */

package duke;


import duke.command.Operation;
import duke.storage.Storage;
import duke.task.SearchTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;
import java.util.Scanner;

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


/**
 * The Class Duke.
 */
public class Duke {
    /**
     * The i.
     */
    static int i;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/avatar.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * The storage.
     */
    private Storage storage;

    /**
     * The tasks.
     */
    private TaskList tasks;
    private TaskList temp;
    /**
     * The ui.
     */
    private Ui ui;

    /**
     * Instantiates a new duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        temp = new TaskList();

        storage = new Storage(filePath);
        try {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String command = "";

        String[] current = input.split(" ");
        command = current[0];
        if ((command).equalsIgnoreCase(Operation.BYE.toString())) {
            return ui.clean();
        } else if (command.equalsIgnoreCase(Operation.LIST.toString())) {
            temp.getTasks().clear();
            tasks = storage.loadTasks();
            return ui.showTasks(tasks, false);

        } else if (command.equalsIgnoreCase(Operation.TODO.toString())
                || command.equalsIgnoreCase(Operation.DEADLINE.toString())
                || command.equalsIgnoreCase(Operation.EVENT.toString())) {
            try {
                Task t = tasks.addTask(current, storage);
                return ui.taskAddSuccess(t, tasks.getSize());
            } catch (DukeException ex) {
                return ui.showMessage(Arrays.asList(ex.getMessage()));
            }

        } else if (command.equalsIgnoreCase(Operation.FIND.toString())) {
            temp = storage.findTasks(Arrays.stream(current).skip(1).toArray(String[]::new));
            return ui.showTasks(temp, true);
        } else if (command.equalsIgnoreCase(Operation.DONE.toString())) {
            int value = Integer.parseInt(current[1]);
            try {
                Task cur = tasks.get(value - 1);
                StringBuilder sb = new StringBuilder();
                if (!temp.getTasks().isEmpty()) {
                    cur = tasks.get(((SearchTask) temp.get(value - 1)).getKey() - 1);
                    value = ((SearchTask) temp.get(value - 1)).getKey();

                }
                cur.markAsDone();
                tasks.deleteTask(value, storage);
                tasks.addTask(value - 1, cur);
                for (Task t : tasks.getTasks()) {
                    sb.append(t.print() + "\n");
                }
                storage.writeToFile(sb.toString());
                return ui.taskMarkDone(cur);
            } catch (IndexOutOfBoundsException ex) {
                return ui.taskNumberError();
            }

        } else if (command.equalsIgnoreCase(Operation.DELETE.toString())) {
            try {
                int keyToDelete = Integer.parseInt(current[1]);
                if (!temp.getTasks().isEmpty()) {
                    keyToDelete = ((SearchTask) temp.get(keyToDelete - 1)).getKey();
                }
                temp.getTasks().clear();
                Task cur = tasks.deleteTask(keyToDelete, storage);
                return ui.taskRemoveSuccess(cur, tasks.getSize());
            } catch (IndexOutOfBoundsException ex) {
                return ui.taskNumberError();
            }

        } else {
            try {
                throw new DukeException(
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception ex) {
                return ui.showMessage(Arrays.asList(ex.getMessage()));
            }
        }

    }

}
