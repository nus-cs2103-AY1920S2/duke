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
    private Ui ui;

    /**
     * Class constructor.
     *
     * @param filePath Path to file where tasks are saved on hard disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException exception) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runner of Duke.
     * Triggered upon start-up.
     */
    public void run() {
        ui.printGreeting();
        String input = ui.readTask();

        while (!input.equals("bye")) {
            Parser parser = new Parser(input);

            switch (parser.getIdentifier()) {

            case "list":
                ui.printList(tasks);
                break;
            case "done":
                int completedTask = parser.getTaskIndex();
                tasks.getTask(completedTask - 1).setDone();

                ui.printDoneSuccess(tasks, completedTask - 1);

                try {
                    storage.updateFile(tasks);
                } catch (IOException exception) {
                    ui.printUpdateError(exception);
                }
                break;
            case "delete":
                int removeTask = parser.getTaskIndex();

                ui.printDeleteSuccess(tasks, removeTask - 1);

                tasks.deleteTask(removeTask - 1);
                ui.printStatusUpdate(tasks);

                try {
                    storage.updateFile(tasks);
                } catch (IOException exception) {
                    ui.printUpdateError(exception);
                }
                break;
            case "find":
                try {
                    this.findTarget(parser);
                } catch (DukeException exception) {
                    ui.printException(exception);
                }

                break;
            default:
                try {
                    this.addTask(parser);

                    ui.printAddSuccess(tasks);
                    ui.printStatusUpdate(tasks);

                    try {
                        storage.updateFile(tasks);
                    } catch (IOException exception) {
                        ui.printUpdateError(exception);
                    }
                } catch (DukeException exception) {
                    ui.printException(exception);
                }
                break;
            }

            input = ui.readTask();
        }

        ui.printExit();
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
    public void findTarget(Parser parser) throws DukeException {
        String keyword = parser.getDescription();

        ArrayList<String> targets = tasks.findTargets(keyword);

        ui.printTargets(targets);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}