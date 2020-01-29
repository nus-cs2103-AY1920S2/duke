import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


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
