package duke;

import duke.exceptions.InvalidInputException;
import duke.exceptions.InsufficientInputException;
import duke.exceptions.InvalidEventException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidTodoException;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A Parser is the logic handling agent of the user's commands and database inputs when loading from it.
 */
public class Parser {

    /**
     * The powerful thing of a parser is it does not rely on any external
     * agent to perform the processing of inputs, just with pure logic.
     */
    public Parser() {
    }

    /**
     * Processes every command line entered by the user. Users are able to enter
     * bye, list, done x, find key, delete x, todo description, event description /at time,
     * deadline description /by time, and any other inputs are deemed as invalid entries.
     *
     * @param command  The string of command entered by the user.
     * @param taskList The TaskList to process CRUD operations based on user input.
     * @return An output denoting the success / failure of an operation.
     * @throws InsufficientInputException Not enough to arguments to find/delete/done.
     * @throws InvalidIndexException      Wrong indexes from TaskList's delete and done operations.
     * @throws InvalidTodoException       Invalid format of Task entered by the user.
     * @throws InvalidEventException      Invalid format of Event entered by the user.
     * @throws InvalidDeadlineException   Invalid format of Deadline entered by the user.
     * @throws InvalidInputException      Invalid commands entered by the user.
     */
    public String processCommand(String command, TaskList taskList) throws
            InsufficientInputException,
            InvalidIndexException,
            InvalidTodoException,
            InvalidEventException,
            InvalidDeadlineException,
            InvalidInputException {
        assert !command.equals("shit") || !command.equals("shut up") : "Please don't be rude to me TT";
        if (command.equals("bye")) { // exit
            return processParsedCommand(command, taskList);
        } else if (command.equals("start")) {
            return processParsedCommand(command, taskList);
        } else if (command.equals("clear")) {
            return processParsedCommand(command, taskList);
        } else if (command.equals("list")) {
            return processParsedCommand(command, taskList);
        } else if (command.split(" ")[0].equals("find")) { //find
            return processParsedCommand(command, taskList);
        } else if (command.split(" ")[0].equals("done")) { // done
            return processParsedCommand(command, taskList);
        } else if (command.split(" ")[0].equals("delete")) { // delete
            return processParsedCommand(command, taskList);
        } else if (command.split(" ")[0].equals("todo")) { // add todo
            return processParsedCommand(command, taskList);
        } else if (command.split(" ")[0].equals("deadline")) { // add deadline
            return processParsedCommand(command, taskList);
        } else if (command.split(" ")[0].equals("event")) { // add event
            return processParsedCommand(command, taskList);
        } else {
            throw new InvalidInputException();
        }
    }

    public String sendHelp() {
        String bye = "bye\n";
        String clear = "clear\n";
        String list = "list\n";
        String find = "find <keyword>\n";
        String done = "done <index>\n";
        String delete = "delete <index>\n";
        String createTask = "task <description>\n";
        String createDeadline = "deadline <description> /by <deadline>\n";
        String createEvent = "event <description> /at <time>";
        return bye + clear + list + find + done + delete + createTask + createDeadline + createEvent;
    }

    public String findTask(String command) throws InsufficientInputException {
        if (command.split(" ").length < 2) {
            throw new InsufficientInputException();
        }
        return command.split(" ")[1].trim();
    }

    public int setTaskDone(String command) throws InsufficientInputException {
        if (command.split(" ").length < 2) {
            throw new InsufficientInputException();
        }
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    public Task createNewTask(String command) throws InvalidTodoException {
        if (command.split(" ").length < 2) {
            throw new InvalidTodoException();
        }
        command = command.substring(command.split(" ")[0].length() + 1, command.length());
        return new Task(command);
    }

    public int getDeleteIndex(String command) throws InsufficientInputException {
        if (command.split(" ").length < 2) {
            throw new InsufficientInputException();
        }
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    public Deadline createNewDeadline(String command) throws InvalidDeadlineException {
        Deadline deadline;
        command = command.substring(command.split(" ")[0].length() + 1, command.length()).trim();
        if (command.split("/by").length == 0) {
            throw new InvalidDeadlineException("OOPS! The description and time is missing. " +
                    "Format: description /by time");
        } else if (command.split("/by").length == 1) {
            if (command.split("/by")[0].equals("")) {
                throw new InvalidDeadlineException("OOPS! The description and time is missing. " +
                        "Format: description /by time");
            } else {
                throw new InvalidDeadlineException("OOPS! The time is missing. " +
                        "Format: description /by time");
            }
        } else if (command.split("/by").length == 2) {
            if (command.split("/by")[0].equals("")) {
                throw new InvalidDeadlineException("OOPS! The description is missing. " +
                        "Format: description /by time");
            }
        }
        String name = command.split(" /by")[0].trim();
        String time = command.split(" /by")[1].trim();
        if (isValidDate(time)) {
            LocalDate todoDate = LocalDate.parse(time);
            deadline = new Deadline(name, todoDate);
        } else {
            deadline = new Deadline(name, time);
        }
        return deadline;
    }

    public Event createNewEvent(String command) throws InvalidEventException {
        Event event;
        command = command.substring(command.split(" ")[0].length() + 1, command.length()).trim();
        if (command.split("/at").length == 0) {
            throw new InvalidEventException("OOPS! The description and time is missing. " +
                    "Format: description /at time");
        } else if (command.split("/at").length == 1) {
            if (command.split("/at")[0].equals("")) {
                throw new InvalidEventException("OOPS! The description and time is missing. " +
                        "Format: description /at time");
            } else {
                throw new InvalidEventException("OOPS! The time is missing. " +
                        "Format: description /at time");
            }
        } else if (command.split("/at").length == 2) {
            if (command.split("/at")[0].equals("")) {
                throw new InvalidEventException("OOPS! The description is missing.");
            }
        }
        String name = command.split(" /at")[0].trim();
        String time = command.split(" /at")[1].trim();
        if (isValidDate(time)) {
            LocalDate todoDate = LocalDate.parse(time);
            event = new Event(name, todoDate);
        } else {
            event = new Event(name, time);
        }
        return event;
    }

    public String processParsedCommand(String command, TaskList taskList) throws
            InsufficientInputException,
            InvalidIndexException,
            InvalidTodoException,
            InvalidEventException,
            InvalidDeadlineException,
            InvalidInputException {
        assert command != null || !command.equals(" ") : "Command should not be an empty string";
        int idx;
        String front = command.split(" ")[0];
        switch (front) {
            case "bye":
                return "Bye! See you again!";
            case "clear":
                return taskList.clear();
            case "start":
                return sendHelp();
            case "list":
                return taskList.list();
            case "find":
                String key = findTask(command);
                return taskList.find(key);
            case "done":
                idx = setTaskDone(command);
                return taskList.done(idx);
            case "delete":
                idx = getDeleteIndex(command);
                return taskList.delete(idx);
            case "todo":
                Task task = createNewTask(command);
                return taskList.addTask(task);
            case "deadline":
                Deadline deadline = createNewDeadline(command);
                return taskList.addDeadline(deadline);
            case "event":
                Event event = createNewEvent(command);
                return taskList.addEvent(event);
            default:
                throw new InvalidInputException();
        }
    }


    /**
     * Process tasks from the database to a list.
     *
     * @param line        Every line of task from the database.
     * @param storedTasks The list of task to load with.
     */
    public void processInputFromFile(String line, List<Task> storedTasks) {
        String[] split = line.split("\\ \\|\\ ");
        String type = split[0];
        switch (type) {
            case "T":
                Task task = new Task(split[2]);
                if (Integer.parseInt(split[1]) == 1) {
                    task.setDone();
                }
                storedTasks.add(task);
                break;
            case "E":
                String eventTime = split[3];
                String eventDescription = split[2];
                int eventStatus = Integer.parseInt(split[1]);
                if (isValidDate(eventTime)) {
                    LocalDate eld = LocalDate.parse(eventTime);
                    Event event = new Event(eventDescription, eld);
                    if (eventStatus == 1) {
                        event.setDone();
                    }
                    storedTasks.add(event);
                } else {
                    Event event = new Event(eventDescription, eventTime);
                    if (eventStatus == 1) {
                        event.setDone();
                    }
                    storedTasks.add(event);
                }
                break;
            case "D":
                String deadlineTime = split[3];
                String deadlineDescription = split[2];
                int deadlineStatus = Integer.parseInt(split[1]);
                if (isValidDate(deadlineTime)) {
                    LocalDate dld = LocalDate.parse(deadlineTime);
                    Deadline deadline = new Deadline(deadlineDescription, dld);
                    if (deadlineStatus == 1) {
                        deadline.setDone();
                    }
                    storedTasks.add(deadline);
                } else {
                    Deadline deadline = new Deadline(deadlineDescription, deadlineTime);
                    if (deadlineStatus == 1) {
                        deadline.setDone();
                    }
                    storedTasks.add(deadline);
                }
                break;
            default:
                break;
        }
    }

    /**
     * A logic function to determine the validity of a date entered.
     * A date is a string in special format entered by the user to create deadlines/events.\
     * with the format YYYY-MM-DD
     *
     * @param str The string of date to be tested with.
     * @return A boolean to denote this string is a date.
     */
    static boolean isValidDate(String str) {
        try {
            LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}