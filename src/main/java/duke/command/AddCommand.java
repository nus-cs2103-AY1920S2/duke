package duke.command;

import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import duke.other.DateValidator;
import duke.other.Storage;
import duke.other.Ui;
import duke.other.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command that adds on to the TaskList(i.e.Todo, Event, Deadline) that extends the Command class. An
 * AddCommand object corresponds to a command type represented by a String followed by the necessary details of the
 * command(e.g. Event Submit Assignment /at 2020/01/30 12:00).
 */
public class AddCommand extends Command {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/M/d");
    private static final DateValidator DATE_VALIDATOR = new DateValidator(DATE_FORMATTER);

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateValidator TIME_VALIDATOR = new DateValidator(TIME_FORMATTER);
    private String instruction;
    private String details;

    /**
     * Constructs an AddCommand object.
     *
     * @param instruction Command type
     * @param details     Details of command
     */
    public AddCommand(String instruction, String details) {
        this.instruction = instruction;
        this.details = details;
    }

    /**
     * Reads the command type ("instruction") and executes the respective methods to handle the command.
     *
     * @param taskList Overall TaskList of all the Tasks
     * @param ui       Overall Ui handling the ui of Duke
     * @param storage  Storage handling the storage of the Tasks in TaskList
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        switch (instruction) {
        case "todo":
            return handleTodo(details, taskList);
        case "deadline":
            return handleDeadline(details, taskList);
        case "event":
            return handleEvent(details, taskList);
        default:
            assert false : instruction;
            break;
        }
        return null;
    }

    /**
     * Handles the Deadline command and its details. It checks if the details are valid and how many of the date and/or
     * time is/are specified. If only the Date is specified, the Deadline task will only have a date. If only the Time
     * is specified, the Deadline Task will be set to today's Date and the specified Time. If both the Date and Time are
     * specified, the Deadline task will be set to the specified Date and Time. Then the Deadline task will
     * be added into the TaskList.
     *
     * @param reply    Details of Deadline command
     * @param taskList Overall TaskList of all the Tasks
     * @return String of bot's response
     * @throws DukeException If details of Deadline is invalid(i.e. insufficient arguments, argument in incorrect
     *                       format)
     */
    public static String handleDeadline(String reply, TaskList taskList) throws DukeException {
        String[] taskReplyArr = reply.split("/by ");
        if (taskReplyArr.length < 2) {
            return Ui.deadlineInputError();
        }
        String[] taskInstrArr = taskReplyArr[0].split(" ");
        try {
            String task = taskInstrArr[1];
            for (int i = 2; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }
            String timeDate = taskReplyArr[1];
            String[] timeDateArr = timeDate.split(" ");
            if (timeDateArr.length == 2) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0]) && TIME_VALIDATOR.isValidTime(timeDateArr[1])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[1], TIME_FORMATTER);

                    Deadline deadLine = new Deadline(task, formattedDate, formattedTime, false);
                    taskList.addTask(deadLine);
                    return Ui.showTaskAdded(deadLine, taskList);
                } else {
                    assert (DATE_VALIDATOR.isValidDate(timeDateArr[0]) && TIME_VALIDATOR.isValidTime(timeDateArr[1]))
                            == false : timeDateArr[0] + timeDateArr[1];
                    return Ui.deadlineInputError();
                }
            } else if (timeDateArr.length == 1) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    Deadline deadLine = new Deadline(task, formattedDate, false);
                    taskList.addTask(deadLine);
                    return Ui.showTaskAdded(deadLine, taskList);
                } else if (TIME_VALIDATOR.isValidTime(timeDateArr[0])) {
                    assert DATE_VALIDATOR.isValidDate(timeDateArr[0]) == false : timeDateArr[0];
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[0], TIME_FORMATTER);
                    Deadline deadLine = new Deadline(task, LocalDate.now(), formattedTime, false);
                    taskList.addTask(deadLine);
                    return Ui.showTaskAdded(deadLine, taskList);
                } else {
                    assert timeDateArr.length < 1 || timeDateArr.length > 2 : timeDateArr.length;
                    return Ui.deadlineInputError();
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return Ui.deadlineInputError();
        }
        assert false;
        return null;
    }

    /**
     * Handles the Event command and its details. It checks if the details are valid and how many of the date and/or
     * time is/are specified. If only the Date is specified, the Event task will only have a date. If only the Time
     * is specified, the Event Task will be set to today's Date and the specified Time. If both the Date and Time are
     * specified, the Event task will be set to the specified Date and Time. Then the Event task will
     * be added into the TaskList.
     *
     * @param reply    Details of Event command
     * @param taskList Overall TaskList of all the Tasks
     * @return String of bot's response
     * @throws DukeException If details of Event is invalid(i.e. insufficient arguments, argument in incorrect
     *                       format)
     */
    public static String handleEvent(String reply, TaskList taskList) throws DukeException {
        System.out.println(reply);
        String[] taskReplyArr = reply.split("/at ");
        if (taskReplyArr.length < 2) {
            return Ui.eventInputError();
        }
        String[] taskInstrArr = taskReplyArr[0].split(" ");
        try {
            String task = taskInstrArr[1];
            for (int i = 2; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }
            String timeDate = taskReplyArr[1];

            String[] timeDateArr = timeDate.split(" ");
            if (timeDateArr.length == 2) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0]) && TIME_VALIDATOR.isValidTime(timeDateArr[1])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[1], TIME_FORMATTER);

                    Event event = new Event(task, formattedDate, formattedTime, false);
                    taskList.addTask(event);
                    return Ui.showTaskAdded(event, taskList);
                } else {
                    assert (DATE_VALIDATOR.isValidDate(timeDateArr[0]) && TIME_VALIDATOR.isValidTime(timeDateArr[1]))
                            == false : timeDateArr[0] + timeDateArr[1];
                    return Ui.eventInputError();
                }
            } else if (timeDateArr.length == 1) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    Event event = new Event(task, formattedDate, false);
                    taskList.addTask(event);
                    return Ui.showTaskAdded(event, taskList);
                } else if (TIME_VALIDATOR.isValidTime(timeDateArr[0])) {
                    assert DATE_VALIDATOR.isValidDate(timeDateArr[0]) == false : timeDateArr[0];
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[0], TIME_FORMATTER);
                    Event event = new Event(task, LocalDate.now(), formattedTime, false);
                    taskList.addTask(event);
                    return Ui.showTaskAdded(event, taskList);
                } else {
                    assert timeDateArr.length < 1 || timeDateArr.length > 2 : timeDateArr.length;
                    return Ui.eventInputError();
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return Ui.eventInputError();
        }
        assert false;
        return null;
    }

    /**
     * Handles the Todo command and its details. Then the Deadline task will be added into the TaskList.
     *
     * @param reply    Details of Todo command
     * @param taskList Overall TaskList of all the Tasks
     * @return String of bot's response
     * @throws DukeException If details of Deadline is invalid(i.e. insufficient arguments)
     */
    public static String handleTodo(String reply, TaskList taskList) throws DukeException {
        String[] replyArr = reply.split(" ");
        String replyWoSpace = "";
        for (int i = 1; i < replyArr.length; i++) {
            replyWoSpace += replyArr[i] + " ";
        }
        if (!replyWoSpace.equals("")) {
            Todo toDo = new Todo(replyWoSpace, false);
            taskList.addTask(toDo);
            return Ui.showTaskAdded(toDo, taskList);
        } else {
            return Ui.todoInputError();
        }
    }
}
