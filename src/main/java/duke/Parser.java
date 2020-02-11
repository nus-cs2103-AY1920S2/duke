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
     * @param command The string of command entered by the user.
     * @param taskList The TaskList to process CRUD operations based on user input.
     * @return An output denoting the success / failure of an operation.
     * @throws InsufficientInputException Not enough to arguments to find/delete/done.
     * @throws InvalidIndexException Wrong indexes from TaskList's delete and done operations.
     * @throws InvalidTodoException Invalid format of Task entered by the user.
     * @throws InvalidEventException Invalid format of Event entered by the user.
     * @throws InvalidDeadlineException Invalid format of Deadline entered by the user.
     * @throws InvalidInputException Invalid commands entered by the user.
     */
    public String processCommand(String command, TaskList taskList) throws
            InsufficientInputException,
            InvalidIndexException,
            InvalidTodoException,
            InvalidEventException,
            InvalidDeadlineException,
            InvalidInputException {
            if (command.equals("bye")) { // exit
                // refactor this
                return processParsedCommand(command, taskList);
            } else if (command.equals("list")) {
                // refactor list
                return processParsedCommand(command, taskList);
            } else if (command.split(" ")[0].equals("find")) { //find
                // refactor this
                command = command.split(" ")[0];
                return processParsedCommand(command, taskList);
            } else if (command.split(" ")[0].equals("done")) { // done
                //refactor this
                command = command.split(" ")[0];
                return processParsedCommand(command, taskList);
            } else if (command.split(" ")[0].equals("delete")) { // delete
                //refactor this
                command = command.split(" ")[0];
                return processParsedCommand(command, taskList);
            } else if (command.split(" ")[0].equals("todo")) { // add todo
                command = command.split(" ")[0];
                return processParsedCommand(command, taskList);
            } else if (command.split(" ")[0].equals("deadline")) { // add deadline
                command = command.split(" ")[0];
                return processParsedCommand(command, taskList);
            } else if (command.split(" ")[0].equals("event")) { // add event
                command = command.split(" ")[0];
                return processParsedCommand(command, taskList);
            } else {
                throw new InvalidInputException();
            }
    }

    public String processParsedCommand(String command, TaskList taskList) throws
            InsufficientInputException,
            InvalidIndexException,
            InvalidTodoException,
            InvalidEventException,
            InvalidDeadlineException,
            InvalidInputException  {
            int idx;
            String name;
            String time;
            switch (command) {
                case "bye":
                    return "Bye! See you again!";
                case "list":
                    return taskList.list();
                case "find":
                    if (command.split(" ").length < 2) {
                        throw new InsufficientInputException();
                    }
                    String key = command.split(" ")[1].trim();
                    return taskList.find(key);
                case "done":
                    if (command.split(" ").length < 2) {
                        throw new InsufficientInputException();
                    }
                    idx = Integer.parseInt(command.split(" ")[1]) - 1;
                    return taskList.done(idx);
                case "delete":
                    if (command.split(" ").length < 2) {
                        throw new InsufficientInputException();
                    }
                    idx = Integer.parseInt(command.split(" ")[1]) - 1;
                    return taskList.delete(idx);
                case "todo":
                    if (command.split(" ").length < 2) {
                        throw new InvalidTodoException();
                    }
                    command = command.substring(command.split(" ")[0].length() + 1, command.length());
                    Task task = new Task(command);
                    return taskList.addTask(task);
                case "deadline":
                    command = command.substring(command.split(" ")[0].length() + 1, command.length()).trim();
                    if (command.split("/by").length == 0) {
                        throw new InvalidDeadlineException("OOPS! The description and time is missing. " +
                                "Format: description /by time");
                    } else if (command.split("/by").length == 1) {
                        if (command.split("/by")[0].equals("")) {
                            throw new InvalidEventException("OOPS! The description and time is missing. " +
                                    "Format: description /by time");
                        } else {
                            throw new InvalidEventException("OOPS! The time is missing. " +
                                    "Format: description /by time");
                        }
                    } else if (command.split("/by").length == 2) {
                        if (command.split("/by")[0].equals("")) {
                            throw new InvalidDeadlineException("OOPS! The description is missing. " +
                                    "Format: description /by time");
                        }
                    }
                    name = command.split(" /by")[0].trim();
                    time = command.split(" /by")[1].trim();
                    if (isValidDate(time)) {
                        LocalDate todoDate = LocalDate.parse(time);
                        Deadline deadline = new Deadline(name, todoDate);
                        return taskList.addDeadline(deadline);
                    } else {
                        Deadline deadline = new Deadline(name, time);
                        return taskList.addDeadline(deadline);
                    }
                case "event":
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
                    name = command.split(" /at")[0].trim();
                    time = command.split(" /at")[1].trim();
                    if (isValidDate(time)) {
                        LocalDate todoDate = LocalDate.parse(time);
                        Event event = new Event(name, todoDate);
                        return taskList.addEvent(event);
                    } else {
                        Event event = new Event(name, time);
                        return taskList.addEvent(event);
                    }
                default:
                    throw new InvalidInputException();
            }

    }


    /**
     * Process tasks from the database to a list.
     * @param line Every line of task from the database.
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