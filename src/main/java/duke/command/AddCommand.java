package duke.command;

import duke.task.*;
import duke.other.*;
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

    public AddCommand(String instruction, String details) {
        this.instruction = instruction;
        this.details = details;
    }

    /**
     * Reads the command type ("instruction") and executes the respective methods to handle the command.
     * @param taskList Overall TaskList of all the Tasks
     * @param ui Overall Ui handling the ui of Duke
     * @param storage Storage handling the storage of the Tasks in TaskList
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (instruction) {
            case "todo":
                handleTodo(details, taskList);
                break;
            case "deadline":
                handleDeadline(details, taskList);
                break;
            case "event":
                handleEvent(details, taskList);
                break;
        }
    }

    /**
     * Handles the Deadline command and its details. It checks if the details are valid and how many of the date and/or
     * time is/are specified. If only the Date is specified, the Deadline task will only have a date. If only the Time
     * is specified, the Deadline Task will be set to today's Date and the specified Time. If both the Date and Time are
     * specified, the Deadline task will be set to the specified Date and Time. Then the Deadline task will
     * be added into the TaskList.
     * @param reply Details of Deadline command
     * @param taskList Overall TaskList of all the Tasks
     * @throws DukeException If details of Deadline is invalid(i.e. insufficient arguments, argument in incorrect
     * format)
     */
    public static void handleDeadline(String reply, TaskList taskList) throws DukeException {
        String[] taskReplyArr = reply.split("/by ");
        if (taskReplyArr.length < 2) {
            Ui.deadlineInputError();
        }
        String[] taskInstrArr = taskReplyArr[0].split(" ");
        try {
            String task = taskInstrArr[1];
            for (int i = 2; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }
            String timeDate = taskReplyArr[1];
            String[] timeDateArr = timeDate.split(" ");
            if(timeDateArr.length == 2) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0]) && TIME_VALIDATOR.isValidTime(timeDateArr[1])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[1], TIME_FORMATTER);

                    Deadline deadLine = new Deadline(task, formattedDate, formattedTime, false);
                    taskList.add(deadLine);
                    Ui.taskAdded(deadLine, taskList);
                } else {
                    Ui.deadlineInputError();
                }
            } else if (timeDateArr.length == 1) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    Deadline deadLine = new Deadline(task, formattedDate, false);
                    taskList.add(deadLine);
                    Ui.taskAdded(deadLine, taskList);
                } else if (TIME_VALIDATOR.isValidTime(timeDateArr[0])) {
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[0], TIME_FORMATTER);
                    Deadline deadLine = new Deadline(task, LocalDate.now(), formattedTime, false);
                    taskList.add(deadLine);
                    Ui.taskAdded(deadLine, taskList);
                } else {
                    Ui.deadlineInputError();
                }

            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            Ui.deadlineInputError();
        }
    }

    /**
     * Handles the Event command and its details. It checks if the details are valid and how many of the date and/or
     * time is/are specified. If only the Date is specified, the Event task will only have a date. If only the Time
     * is specified, the Event Task will be set to today's Date and the specified Time. If both the Date and Time are
     * specified, the Event task will be set to the specified Date and Time. Then the Event task will
     * be added into the TaskList.
     * @param reply Details of Event command
     * @param taskList Overall TaskList of all the Tasks
     * @throws DukeException If details of Event is invalid(i.e. insufficient arguments, argument in incorrect
     * format)
     */
    public static void handleEvent(String reply, TaskList taskList) throws DukeException {
        String[] taskReplyArr = reply.split("/at ");
        if (taskReplyArr.length < 2) {
            Ui.eventInputError();
        }
        String[] taskInstrArr = taskReplyArr[0].split(" ");
        try {
            String task = taskInstrArr[1];
            for (int i = 2; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }
            String timeDate = taskReplyArr[1];

            String[] timeDateArr = timeDate.split(" ");
            if(timeDateArr.length == 2) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0]) && TIME_VALIDATOR.isValidTime(timeDateArr[1])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[1], TIME_FORMATTER);

                    Event event = new Event(task, formattedDate, formattedTime, false);
                    taskList.add(event);
                    Ui.taskAdded(event, taskList);
                } else {
                    Ui.eventInputError();
                }
            } else if (timeDateArr.length == 1) {
                if (DATE_VALIDATOR.isValidDate(timeDateArr[0])) {
                    LocalDate formattedDate = LocalDate.parse(timeDateArr[0], DATE_FORMATTER);
                    Event event = new Event(task, formattedDate, false);
                    taskList.add(event);
                    Ui.taskAdded(event, taskList);
                } else if (TIME_VALIDATOR.isValidTime(timeDateArr[0])) {
                    LocalTime formattedTime = LocalTime.parse(timeDateArr[0], TIME_FORMATTER);
                    Event event = new Event(task, LocalDate.now(), formattedTime, false);
                    taskList.add(event);
                    Ui.taskAdded(event, taskList);
                } else {
                    Ui.eventInputError();
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            Ui.eventInputError();

        }
    }

    /**
     * Handles the Todo command and its details. Then the Deadline task will be added into the TaskList.
     * @param reply Details of Todo command
     * @param taskList Overall TaskList of all the Tasks
     * @throws DukeException If details of Deadline is invalid(i.e. insufficient arguments)
     */
    public static void handleTodo(String reply, TaskList taskList) throws DukeException {
            if(!reply.equals("")) {
                Todo toDo = new Todo(reply, false);
                taskList.add(toDo);
                Ui.taskAdded(toDo, taskList);
            } else {
                Ui.todoInputError();
        }
    }





}
