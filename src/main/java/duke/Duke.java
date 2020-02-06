package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.exception.InvalidIndexException;
import duke.exception.MissingParameterException;
import duke.exception.MissingTimeException;
import duke.exception.TimeFormatException;
import duke.exception.UnknownCommandException;

/** Class Duke, the driver class of the program. */
public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    /** Duke object creation, initiate Ui, Parser, TaskList, and Storage. */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage(".//saved-tasks.txt");
    }

    /**
     * Driver function of the whole program.
     *
     * <p>Initiates greeting. Enter a while loop waiting for commands and call respective handlers.
     * Exits when a "bye" command is given.
     *
     * @throws IOException If Exception is raised during loading and saving.
     */
    public void run() throws IOException {
        storage.loadBaby(taskList, parser);
        ui.greeting();
        ui.showList(taskList.getTaskList(), "Tasks loaded from disk:");
        ui.initPrompt();

        boolean isExit = false;

        while (!isExit) {
            try {
                String longCommand = ui.getCommand();
                String[] keywords = parser.parseCommand(longCommand);
                switch (keywords[0]) {
                case "bye":
                    isExit = true;
                    break;
                case "list":
                    ui.showList(taskList.getTaskList(), "Here are the tasks in your list:");
                    break;
                case "search":
                    if (keywords.length == 1) {
                        throw new MissingParameterException("Search");
                    }
                    handleSearch(keywords[1]);
                    break;
                case "done":
                    if (keywords.length == 1) {
                        throw new MissingParameterException("Done");
                    }
                    handleDone(keywords[1]);
                    break;
                case "delete":
                    if (keywords.length == 1) {
                        throw new MissingParameterException("Delete");
                    }
                    handleDelete(keywords[1]);
                    break;
                case "todo":
                    if (keywords.length == 1) {
                        throw new MissingParameterException("Todo");
                    }
                    handleTodo(keywords[1]);
                    break;
                case "event":
                    if (keywords.length == 1) {
                        throw new MissingParameterException("Event");
                    }
                    handleEvent(keywords[1]);
                    break;
                case "deadline":
                    if (keywords.length == 1) {
                        throw new MissingParameterException("Deadline");
                    }
                    handleDeadline(keywords[1]);
                    break;
                default:
                    throw new UnknownCommandException(keywords[0]);
                }

                storage.saveBaby(taskList.getTaskList());

            } catch (MissingParameterException
                    | MissingTimeException
                    | UnknownCommandException
                    | TimeFormatException
                    | InvalidIndexException e) {
                ui.showException(e);
            } catch (Exception e) {
                ui.showUnknownException(e);
            }
        }
        ui.bye();
    }

    /**
     * Handles the searching for key in the task list.
     *
     * @param key String that we want to look for in the task.
     */
    public void handleSearch(String key) {
        ArrayList<Task> tasksWithKey = new ArrayList<>();
        for (Task t : taskList.getTaskList()) {
            if (t.toString().contains(key)) {
                tasksWithKey.add(t);
            }
        }
        ui.showList(tasksWithKey, "Here are the tasks that contains '" + key + "' in your list:");
    }

    /**
     * Marks task as done and call Ui to display task.
     *
     * @param indexStr A string representing the index of the task from list we want to mark as
     *     done.
     */
    public void handleDone(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            Task task = taskList.getTaskList().get(index);
            task.done();
            ui.showDone(task);
        } catch (Exception e) {
            throw new InvalidIndexException(indexStr);
        }
    }

    /**
     * Deletes tasks from task list and call Ui to display deleted task.
     *
     * @param indexStr A string representing the index of the task from list we want to delete.
     */
    public void handleDelete(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            Task task = taskList.getTaskList().get(index);
            taskList.getTaskList().remove(task);
            ui.showDelete(task, taskList.getTaskList());
        } catch (Exception e) {
            throw new InvalidIndexException(indexStr);
        }
    }

    /**
     * Handles the event command.
     *
     * @param description String containing both the name and time of the task.
     */
    public void handleEvent(String description) {
        String[] strArr = description.split(" /at ", 2);
        if (strArr.length == 1) {
            throw new MissingTimeException("Event");
        }
        String todo = strArr[0];
        String time = strArr[1];
        Event task = new Event(todo, parser.stringToTime(time));
        taskList.addTask(task);
        ui.showAdd(task, taskList.getTaskList());
    }

    /**
     * Handles the todo command.
     *
     * @param description String containing the task name.
     */
    public void handleTodo(String description) {
        Todo task = new Todo(description);
        taskList.addTask(task);
        ui.showAdd(task, taskList.getTaskList());
    }

    /**
     * Handles the deadline command.
     *
     * @param description String containing both the name and time of the task.
     */
    public void handleDeadline(String description) {
        String[] strArr = description.split(" /by ", 2);
        if (strArr.length == 1) {
            throw new MissingTimeException("Deadline");
        }
        String todo = strArr[0];
        String time = strArr[1];
        Deadline task = new Deadline(todo, parser.stringToTime(time));
        taskList.addTask(task);
        ui.showAdd(task, taskList.getTaskList());
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
