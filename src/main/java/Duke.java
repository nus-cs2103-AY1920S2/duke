import java.text.ParseException;
import java.util.Scanner;

/**
 * Main class that drives the code to run the Duke bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Duke class constructor for JavaFX.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.readFile());
        Scanner sc = new Scanner(System.in);
        parser = new Parser(sc);
    }

    /**
    * Gets the response of Duke based on the command given from the user.
     * @param command String input from the user.
     */
    protected String getResponse(String command) {
        try {
            String commandType = parser.getCommandType(command);
            switch (commandType) {
            case "list":
                return tasks.printList();
            case "find":
                String findString = parser.getFind(command);
                TaskList matchedTask = tasks.find(findString);
                return ui.printFind(matchedTask);
            case "done": {
                int taskNo = parser.getTaskNo(command);
                try {
                    tasks.setDone(taskNo);
                    return ui.printDone(tasks.getTask(taskNo));
                } catch (IndexOutOfBoundsException e) {
                    return ("There is no task " + (taskNo + 1) + " in the list.");
                }
            }
            case "delete": {
                int taskNo = parser.getTaskNo(command);
                Task task = tasks.getTask(taskNo);
                tasks.deleteTask(taskNo);
                storage.saveFile(tasks.getTaskList());
                return ui.printDelete(task, tasks.getSize());
            }
            case "deadline":
                try {
                    Deadline deadline = parser.getDeadline(command);
                    tasks.addTask(deadline);
                    storage.saveFile(tasks.getTaskList());
                    return ui.printAdd(deadline, tasks.getSize());
                } catch (DukeException | ParseException e) {
                    return (e.getMessage());
                }
            case "event":
                try {
                    Event event = parser.getEvent(command);
                    tasks.addTask(event);
                    storage.saveFile(tasks.getTaskList());
                    return ui.printAdd(event, tasks.getSize());
                } catch (DukeException e) {
                    return (e.getMessage());
                }
            case "todo":
                try {
                    ToDo toDo = parser.getToDo(command);
                    tasks.addTask(toDo);
                    storage.saveFile(tasks.getTaskList());
                    return ui.printAdd(toDo, tasks.getSize());
                } catch (DukeException e) {
                    return (e.getMessage());
                }
            case "bye":
                return ui.printBye();
            case "snooze":
                int taskNo = parser.getTaskNo(command);
                try {
                    int noDays = parser.getSnoozeNo(command);
                    assert tasks.getTask(taskNo) != null : "Tasklist contains valid tasks.";
                    if (tasks.getTask(taskNo) instanceof ToDo) {
                        throw new DukeException("ToDo cannot be snoozed.");
                    } else if (tasks.getTask(taskNo) instanceof Deadline) {
                        Deadline deadline = (Deadline) tasks.getTask(taskNo);
                        deadline.snooze(noDays);
                        return ui.printSnooze(deadline, noDays);
                    } else {
                        Event event = (Event) tasks.getTask(taskNo);
                        event.snooze(noDays);
                        return ui.printSnooze(event, noDays);
                    }
                } catch (DukeException e) {
                    return e.getMessage();
                }
            default:
                return "Invalid command, please try again.";
            }
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }
}
