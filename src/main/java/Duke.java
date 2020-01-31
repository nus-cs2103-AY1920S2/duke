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
 * Duke is a Personal Assistant Chatbot that helps a person keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private GuiUi ui;

    /**
     * Class constructor.
     *
     */
    public Duke() {
        ui = new GuiUi();
        storage = new Storage("data/duke.txt");
    }

    public String prepareList() {
        String response = "";

        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException exception) {
            tasks = new TaskList();

            File file = new File("data/duke.txt");

            try {
                file.createNewFile();
                response.concat("File created\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * Runner of Duke.
     * Triggered upon start-up.
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
                int completedTask = parser.getTaskIndex();
                tasks.getTask(completedTask - 1).setDone();

                response = ui.getDoneSuccess(tasks, completedTask - 1);

                try {
                    storage.updateFile(tasks);
                } catch (IOException exception) {
                    response = ui.getUpdateError(exception);
                }
                break;
            case "delete":
                int removeTask = parser.getTaskIndex();

                response = ui.getDeleteSuccess(tasks, removeTask - 1);

                tasks.deleteTask(removeTask - 1);
                response = response.concat(ui.getStatusUpdate(tasks));

                try {
                    storage.updateFile(tasks);
                } catch (IOException exception) {
                    response = ui.getUpdateError(exception);
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

                    response = ui.getAddSuccess(tasks);
                    response = response.concat(ui.getStatusUpdate(tasks));

                    try {
                        storage.updateFile(tasks);
                    } catch (IOException exception) {
                        response = ui.getUpdateError(exception);
                    }
                } catch (DukeException exception) {
                    response = ui.getExceptionMessage(exception);
                }
                break;
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
            tasks.addToDo(toDo);
        } else if (parser.getIdentifier().equals("event") || parser.getIdentifier().equals("deadline")) {
            String description = parser.getDescription();

            LocalDate date = parser.getDate();
            LocalTime timing = parser.getTime();

            if (parser.getIdentifier().equals("event")) {
                tasks.addEvent(description, date, timing);
            } else {
                tasks.addDeadline(description, date, timing);
            }
        } else {
            throw new DukeException("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Prints list of tasks that contains keyword.
     *
     * @param parser Parser to interpret user input command.
     * @throws DukeException Thrown when description and hence keyword is empty.
     */
    public String findTarget(Parser parser) throws DukeException {
        String keyword = parser.getDescription();

        ArrayList<String> targets = tasks.findTargets(keyword);

        return ui.getTargets(targets);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return this.run(input);
    }
}