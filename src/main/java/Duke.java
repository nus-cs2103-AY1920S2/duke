import exception.DukeException;
import exception.InvalidDateException;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Duke with its UI and file and load tasks into storage.
     *
     * @throws IOException if there are file exceptions.
     */
    public Duke() throws IOException {
        ui = new Ui();
        assert this.ui != null : "Ui should be instantiated";
        String filePath = "data/duke.txt";
        storage = new Storage(filePath);
        assert this.storage != null : "storage should be instantiated";
        try {
            tasks = new TaskList(storage.loadExistingFileTasks());
            assert this.tasks != null : "tasks should be instantiated";
        } catch (FileNotFoundException ex) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Performs all functionality of Duke chat bot.
     *
     * @throws IOException if there are file exceptions.
     */
    public void run() throws IOException {
        ui.print();

        outerloop:
        while (ui.hasNextInput()) {
            try {
                String userNextInput = ui.getNextInput();
                Parser parser = new Parser(userNextInput);
                String userCommand = parser.getCommand();

                switch (userCommand) {
                case "bye":
                    ui.printExit();
                    break outerloop;

                case "list":
                    ui.listAllTasks(tasks);
                    break;

                case "done":
                    ui.acknowledgeDone(tasks, parser.getTaskIndex());
                    assert tasks.getTask(parser.getTaskIndex()).getStatus() == true
                            : "Task should be marked as done.";
                    storage.saveTasksIntoFile(tasks);
                    break;

                case "todo":
                    Todo todo = new Todo(parser.getTaskAction());
                    tasks.addTask(todo);
                    ui.acknowledgeTodo(tasks, todo);
                    storage.saveTasksIntoFile(tasks);
                    break;

                case "deadline":
                    Deadline deadline = new Deadline(parser.getTaskAction(), parser.getTaskDate());
                    tasks.addTask(deadline);
                    ui.acknowledgeDeadline(tasks, deadline);
                    storage.saveTasksIntoFile(tasks);
                    break;

                case "event":
                    Task event = new Event(parser.getTaskAction(), parser.getTaskDate());
                    tasks.addTask(event);
                    ui.acknowledgeEvent(tasks, event);
                    storage.saveTasksIntoFile(tasks);
                    break;

                case "delete":
                    ui.acknowledgeDelete(tasks, parser.getTaskIndex());
                    storage.saveTasksIntoFile(tasks);
                    break;

                case "find":
                    ui.acknowledgeFound(tasks, parser.getTaskAction());
                    break;

                default:
                    ui.printUnknownCommand();
                    break;
                }
            } catch (DukeException | DateTimeException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
    }

    /**
     * Duke responds in GUI.
     *
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
