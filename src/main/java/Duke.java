import command.Command;
import exception.DukeException;
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
        assert this.storage != null : "Storage should be instantiated";
        try {
            tasks = new TaskList(storage.loadExistingFileTasks());
            assert this.tasks != null : "Tasks should be instantiated";
            assert this.tasks.getTaskListSize() != 0 : "Tasks should should have been loaded";
        } catch (FileNotFoundException | AssertionError ex) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Performs all functionality of Duke chat bot.
     *
     * @throws IOException if there are file exceptions.
     */
    public String run(String userInput) throws IOException {
//        ui.printWelcomeMessage();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }

        try {
            Parser parser = new Parser(userInput);
            String userCommand = parser.getCommand();

            switch (userCommand) {
            case "bye":
                return ui.getExitMessage();

            case "list":
                return ui.listAllTasks(tasks);

            case "done":
                String doneMessage = ui.acknowledgeDone(tasks, parser.getTaskIndexArray());
                storage.saveTasksIntoFile(tasks);
                return doneMessage;

            case "todo":
                Todo todo = new Todo(parser.getTaskAction());
                String todoMessage = ui.acknowledgeTodo(tasks, todo);
                storage.saveTasksIntoFile(tasks);
                return todoMessage;

            case "deadline":
                Deadline deadline = new Deadline(parser.getTaskAction(), parser.getTaskDate());
                String deadlineMessage = ui.acknowledgeDeadline(tasks, deadline);
                storage.saveTasksIntoFile(tasks);
                return deadlineMessage;

            case "event":
                Event event = new Event(parser.getTaskAction(), parser.getTaskDate());
                String eventMessage = ui.acknowledgeEvent(tasks, event);
                storage.saveTasksIntoFile(tasks);
                return eventMessage;

            case "delete":
                String deleteMessage = ui.acknowledgeDelete(tasks, parser.getTaskIndexArray());
                storage.saveTasksIntoFile(tasks);
                return deleteMessage;

            case "find":
                return ui.acknowledgeFound(tasks, parser.getTaskAction());

            default:
                return ui.printUnknownCommand();
            }
        } catch (DukeException | DateTimeException | AssertionError | IndexOutOfBoundsException ex) {
            return ex.getMessage();
        }
    }


    /**
     * Duke responds in GUI.
     *
     */
    public String getResponse(String input) {
        try {
            return new Duke().run(input);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

//    public static void main(String[] args) throws IOException {
//        new Duke().run();
//    }
}
