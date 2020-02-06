import javafx.application.Application;
import javafx.fxml.FXML;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person keep track of various types of tasks.
 * It can add/delete/list tasks, as well as, mark tasks as done when completed. Upon exit, the tasks
 * will be saved to a data file.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private GuiUi ui;

    /**
     * Class constructor.
     */
    public Duke() {
        ui = new GuiUi();
        storage = new Storage("data/duke.txt");
    }

    /**
     * Loads the tasks saved during the previous session into a new TaskList upon start-up.
     *
     * @return Error message.
     */
    public String prepareList() {
        String response = "";

        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException exception) {
            tasks = new TaskList();

            File file = new File("data/duke.txt");

            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                response = response.concat(e.getMessage());
            }
        }
        return response;
    }

    /**
     * Executes user commands and returns a response required depending on user command.
     *
     * @param input User command.
     * @return Response to be printed on GUI depending on user command.
     */
    public String run(String input) {

        if (!input.equals("bye")) {
            Parser parser = new Parser(input);
            String response = "";

            switch (parser.getIdentifier()) {

            case "list":
                response = ui.getList(tasks);
                break;
            case "done":
                try {
                    int completedTask = parser.getTaskIndex(tasks.getSize());
                    tasks.getTask(completedTask - 1).setDone();

                    assert tasks.getTask(completedTask - 1).getStatusIcon().equals("Y"):
                            "Task was not marked done successfully";

                    response = ui.getDoneSuccess(tasks, completedTask - 1);
                } catch (DukeException e) {
                    response = ui.getExceptionMessage(e);
                }

                break;
            case "delete":
                try {
                    int removeTask = parser.getTaskIndex(tasks.getSize());
                    int originalNumOfTasks = tasks.getSize();

                    response = ui.getDeleteSuccess(tasks, removeTask - 1);

                    assert tasks.getSize() == originalNumOfTasks - 1: "TaskList was not updated properly";

                    tasks.deleteTask(removeTask - 1);
                    response = response.concat(ui.getStatusUpdate(tasks));
                } catch (DukeException e) {
                    response = ui.getExceptionMessage(e);
                }

                break;
            case "find":
                try {
                    response = this.findTarget(parser);
                } catch (DukeException exception) {
                    response = ui.getExceptionMessage(exception);
                }

                break;
            default:
                try {
                    this.addTask(parser);
                    int originalNumOfTasks = tasks.getSize();

                    assert tasks.getSize() == originalNumOfTasks + 1: "TaskList was not updated properly";

                    response = ui.getAddSuccess(tasks);
                    response = response.concat(ui.getStatusUpdate(tasks));

                } catch (DukeException exception) {
                    response = ui.getExceptionMessage(exception);
                }
                break;
            }

            try {
                storage.updateFile(tasks);
            } catch (IOException exception) {
                response = ui.getUpdateError(exception);
            }

            return response;
        }

        return ui.getExitGreeting();
    }

    /**
     * Attempts to add a task to the list of saved tasks.
     *
     * @param parser Parser to interpret user input command.
     * @throws DukeException Exception thrown when empty description is found,
     * empty date/timing for event/deadline, incomprehensible command.
     */
    public void addTask(Parser parser) throws DukeException {
        if (parser.getIdentifier().equals("todo")) {
            String toDo = parser.getDescription();
            tasks.addTask(new ToDo(toDo, "T", false));
        } else if (parser.getIdentifier().equals("event") || parser.getIdentifier().equals("deadline")) {
            String description = parser.getDescription();

            LocalDate date = parser.getDate();
            LocalTime timing = parser.getTime();

            if (parser.getIdentifier().equals("event")) {
                tasks.addTask(new Event(description, "E", date, timing, false));
            } else {
                tasks.addTask(new Deadline(description, "D", date, timing, false));
            }
        } else {
            throw new DukeException("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Returns list of tasks that contains keyword.
     *
     * @param parser Parser to interpret user input command.
     * @return List of tasks that contains keyword.
     * @throws DukeException Thrown when description and hence keyword is empty.
     */
    public String findTarget(Parser parser) throws DukeException {
        String keyword = parser.getDescription();

        ArrayList<String> targets = tasks.findTargets(keyword);

        System.out.println(targets.stream().allMatch(target -> target.contains(keyword)));

        return ui.getTargets(targets);
    }

    /**
     * Returns Duke's response to a given user command.
     *
     * @param input User command.
     * @return Duke's response to the command.
     */
    String getResponse(String input) {
        return this.run(input);
    }
}