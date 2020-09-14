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
    private ArrayList<String> prevCommands;
    private ArrayList<Task> deletedTasks;

    /**
     * Class constructor.
     */
    public Duke() {
        ui = new GuiUi();
        storage = new Storage("duke.txt");
        prevCommands = new ArrayList<String>();
        deletedTasks = new ArrayList<Task>();
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

            File file = new File("duke.txt");

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
                prevCommands.add(input);
                break;
            case "done":
                try {
                    int completedTask = parser.getTaskIndex(tasks.getSize());
                    response = markTaskDone(completedTask);
                    prevCommands.add(input);
                } catch (DukeException e) {
                    response = ui.getExceptionMessage(e);
                }

                break;
            case "delete":
                try {
                    int removeTask = parser.getTaskIndex(tasks.getSize());
                    deletedTasks.add(tasks.getTask(removeTask - 1));

                    response = deleteTask(removeTask);
                    prevCommands.add(input);
                } catch (DukeException e) {
                    response = ui.getExceptionMessage(e);
                }

                break;
            case "find":
                try {
                    response = this.findTarget(parser);
                    prevCommands.add(input);
                } catch (DukeException exception) {
                    response = ui.getExceptionMessage(exception);
                }

                break;
            case "undo":
                try {
                    response = handleUndo();
                } catch (DukeException e) {
                    response = e.getMessage();
                }

                break;
            default:
                try {
                    int originalNumOfTasks = tasks.getSize();
                    this.addTask(parser);

                    assert tasks.getSize() == originalNumOfTasks + 1: "TaskList was not updated properly";

                    response = ui.getAddSuccess(tasks);
                    response = response.concat(ui.getStatusUpdate(tasks));

                    prevCommands.add(input);

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
     * Handle the undo command for Duke.
     *
     * @return Response message after the previous command have been reversed.
     * @throws DukeException Thrown when either the previous command cannot be reversed or does not exist.
     */
    private String handleUndo () throws DukeException {
        if(prevCommands.isEmpty()) {
            throw new DukeException("No previous commands to be undone.\n");
        }

        Parser parser = new Parser(prevCommands.remove(prevCommands.size() - 1));

        if (parser.getIdentifier().equals("undo") || parser.getIdentifier().equals("list")) {
            throw new DukeException("Previous command cannot be undone.\n");
        }

        String response = ui.getUndoIdentifier(parser.getIdentifier());

        switch (parser.getIdentifier()) {
        case "delete":
            tasks.addTask(deletedTasks.remove(deletedTasks.size() - 1));
            response = response.concat(ui.getAddSuccess(tasks));
            response = response.concat(ui.getStatusUpdate(tasks));

            break;
        case "done":
            try {
                int taskToBeReversed = parser.getTaskIndex(tasks.getSize());
                tasks.getTask(taskToBeReversed - 1).resetPrevStatus();

                response = response.concat(ui.getReverseDoneSuccess(tasks, taskToBeReversed - 1));
            } catch (DukeException e) {
                response = ui.getExceptionMessage(e);
            }

            break;
        default:
            response = response.concat(deleteTask(tasks.getSize()));
            break;
        }
        return response;
    }

    /**
     * Mark task specified by the user as done.
     *
     * @param completedTask Index of task to be marked done.
     * @return Response message after a task has been successfully marked as done.
     */
    private String markTaskDone(int completedTask) {
        tasks.getTask(completedTask - 1).setDone();

        assert tasks.getTask(completedTask - 1).getStatusIcon().equals("Y"):
                "Task was not marked done successfully";

        String response = ui.getDoneSuccess(tasks, completedTask - 1);

        return response;
    }

    /**
     * Delete task specified by the user from the TaskList.
     *
     * @param removeTask Index of task to be removed.
     * @return Response message after command is successfully carried out.
     */
    private String deleteTask(int removeTask) {
        int originalNumOfTasks = tasks.getSize();

        String response = ui.getDeleteSuccess(tasks, removeTask - 1);

        tasks.deleteTask(removeTask - 1);
        response = response.concat(ui.getStatusUpdate(tasks));

        assert tasks.getSize() == originalNumOfTasks - 1: "TaskList was not updated properly";

        return response;
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

        ArrayList<Task> targets = tasks.findTargets(keyword);

        System.out.println(targets.stream().allMatch(target -> target.getDescription().contains(keyword)));

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