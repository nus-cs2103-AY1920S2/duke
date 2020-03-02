package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Duke is a task-management system. It accept user commands to add/delete tasks, mark tasks as done and list all tasks.
 * Upon exit, tasks will be saved and kept in a file for the next run.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }

    /**
     * Constructs a Duke object. An attempt will be made to load in tasks from the file specified. If the file
     * does not exist or cannot be accessed, an empty task list will be loaded.
     */
    public Duke() {
        Path filePath = Paths.get("data", "duke.txt");
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Starts Duke. User will be able to input commands to perform actions.
     * CLI-Mode, this is outdated.
     */
    public void run() {
        ui.showGreeting();

        loop:
        while (true) {
            try {
                String input = ui.getInput();
                Parser instruction = new Parser(input);
                Command command = instruction.getCommand();

                switch (command) {
                case BYE:
                    break loop;
                case DONE:
                    int doneTaskNum = Integer.parseInt(instruction.getParameter());
                    tasks.setAsDone(doneTaskNum);
                    ui.showDoneTask(tasks.getTask(doneTaskNum));
                    break;
                case TODO:
                    Task newToDo = new ToDo(instruction.getDescription());
                    tasks.addToTasks(newToDo);
                    ui.showAddedTask(tasks, newToDo);
                    break;
                case DEADLINE:
                    Task newDeadline = new Deadline(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newDeadline);
                    ui.showAddedTask(tasks, newDeadline);
                    break;
                case EVENT:
                    Task newEvent = new Event(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newEvent);
                    ui.showAddedTask(tasks, newEvent);
                    break;
                case LIST:
                    ui.showTaskList(tasks);
                    break;
                case DELETE:
                    int delTaskNum = Integer.parseInt(instruction.getParameter());
                    Task toBeDeleted = tasks.getTask(delTaskNum);
                    tasks.deleteFromTasks(delTaskNum);
                    ui.showDeletedTask(tasks, toBeDeleted);
                    break;
                case FIND:
                    String searchTerm = instruction.getParameter();
                    ui.showFoundTasks(tasks.findTasks(searchTerm));
                    break;
                case ARCHIVE:
                    Path archiveFilePath = Paths.get("data", "dukeArchive.txt");
                    storage.archive(tasks, archiveFilePath);
                    ui.showArchiveMessage(tasks);
                    tasks.clean();
                    break;
                default:
                    ;
                }

            } catch (DukeException e) {
                ui.showError(e);
                System.out.print("> ");
            }
        }
        storage.update(tasks);
        ui.showExitMessage();
    }

    /**
     * Responds to input given by user. Used in GUI version of Duke.
     *
     * @param input Input given by user.
     * @return Response or Error Message according to input given.
     */
    public String getResponse(String input) {
        try {
            Parser instruction = new Parser(input);
            Command command = instruction.getCommand();

            switch (command) {
                case BYE:
                    break;
                case DONE:
                    int doneTaskNum = Integer.parseInt(instruction.getParameter());
                    tasks.setAsDone(doneTaskNum);
                    return ui.getDoneTask(tasks.getTask(doneTaskNum));
                case TODO:
                    Task newToDo = new ToDo(instruction.getDescription());
                    tasks.addToTasks(newToDo);
                    return ui.getAddedTask(tasks, newToDo);
                case DEADLINE:
                    Task newDeadline = new Deadline(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newDeadline);
                    return ui.getAddedTask(tasks, newDeadline);
                case EVENT:
                    Task newEvent = new Event(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newEvent);
                    return ui.getAddedTask(tasks, newEvent);
                case LIST:
                    return ui.getTaskList(tasks);
                case DELETE:
                    int delTaskNum = Integer.parseInt(instruction.getParameter());
                    Task toBeDeleted = tasks.getTask(delTaskNum);
                    tasks.deleteFromTasks(delTaskNum);
                    return ui.getDeletedTask(tasks, toBeDeleted);
                case FIND:
                    String searchTerm = instruction.getParameter();
                    return ui.getFoundTasks(tasks.findTasks(searchTerm));
                case ARCHIVE:
                    Path archiveFilePath = Paths.get("data", "dukeArchive.txt");
                    storage.archive(tasks, archiveFilePath);
                    String message = ui.getArchiveMessage(tasks);
                    tasks.clean();
                    return message;
                default:
                    ;
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
        storage.update(tasks);
        return ui.getExitMessage();
    }

    public Ui getUi() {
        return ui;
    }
}
