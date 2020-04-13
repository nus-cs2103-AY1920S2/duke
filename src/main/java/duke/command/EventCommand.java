package duke.command;

import duke.other.DukeException;
import duke.other.Ui;
import duke.task.Event;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventCommand extends Command {
    private String instruction;
    private String details;
    private LocalDate formattedDate;
    private LocalTime formattedTime;
    public Event event;

    public EventCommand(String instruction, String details) {
        this.instruction = instruction;
        this.details = details;
    }

    /**
     * Handles the Event command and its details. It checks if the details are valid and how many of the date and/or
     * time is/are specified. If only the Date is specified, the Event task will only have a date. If only the Time
     * is specified, the Event Task will be set to today's Date and the specified Time. If both the Date and Time are
     * specified, the Event task will be set to the specified Date and Time. Then the Event task will
     * be added into the TaskList.
     *
     * @param taskList Overall TaskList of all the Tasks
     * @throws DukeException If details of Event is invalid(i.e. insufficient arguments, argument in incorrect
     *                       format)
     */
    public String execute(TaskList taskList, Ui ui) {
        String[] taskReplyArr = details.split("/at");
        String[] taskInstrArr = taskReplyArr[0].trim().split(" ");
        try {
            String task = taskInstrArr[0].trim();
            for (int i = 1; i < taskInstrArr.length; i++) {
                task += " " + taskInstrArr[i];
            }
            String timeDate = taskReplyArr[1].trim();
            String[] timeDateArr = timeDate.split(" ");
            if (timeDateArr.length == 2) {
                if (isValidDate(timeDateArr[0]) && isValidTime(timeDateArr[1])) {
                    createEventWithTimeDate(taskList, task, timeDateArr);
                    return ui.showTaskAdded(event, taskList);
                }
            } else if (timeDateArr.length == 1) {
                if (isValidDate(timeDateArr[0])) {
                    createEventWithDate(taskList, task, timeDateArr[0]);
                    return ui.showTaskAdded(event, taskList);
                } else if (isValidTime(timeDateArr[0])) {
                    createEventWithTime(taskList, task, timeDateArr[0]);
                    return ui.showTaskAdded(event, taskList);
                }
            } else {
                return ui.eventInputError();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return ui.eventInputError();
        }
        return ui.eventInputError();
    }

    private void createEventWithTimeDate(TaskList taskList, String task, String[] timeDateArr) {
        formattedDate = parseValidDate(timeDateArr[0]);
        formattedTime = parseValidTime(timeDateArr[1]);
        event = new Event(task, formattedDate, formattedTime, false);
        taskList.addTask(event);
    }

    private void createEventWithDate(TaskList taskList, String task, String date) {
        formattedDate = parseValidDate(date);
        event = new Event(task, formattedDate, false);
        taskList.addTask(event);
    }

    private void createEventWithTime(TaskList taskList, String task, String time) {
        formattedTime = parseValidTime(time);
        event = new Event(task, LocalDate.now(), formattedTime, false);
        taskList.addTask(event);
    }
}


