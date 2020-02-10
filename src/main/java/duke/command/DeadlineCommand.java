package duke.command;

import duke.other.DukeException;
import duke.other.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeadlineCommand extends Command {
    private String instruction;
    private String details;
    private LocalDate formattedDate;
    private LocalTime formattedTime;
    public Deadline deadLine;

    public DeadlineCommand(String instruction, String details) {
        this.instruction = instruction;
        this.details = details;
    }


    /**
     * Handles the Deadline command and its details. It checks if the details are valid and how many of the date and/or
     * time is/are specified. If only the Date is specified, the Deadline task will only have a date. If only the Time
     * is specified, the Deadline Task will be set to today's Date and the specified Time. If both the Date and Time are
     * specified, the Deadline task will be set to the specified Date and Time. Then the Deadline task will
     * be added into the TaskList.
     *
     * @param taskList Overall TaskList of all the Tasks
     * @throws DukeException If details of Deadline is invalid(i.e. insufficient arguments, argument in incorrect
     *                       format)
     */
    public String execute(TaskList taskList, Ui ui) {
        String[] taskReplyArr = details.split("/by");
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
                    createDeadlineWithTimeDate(taskList, task, timeDateArr);
                    return ui.showTaskAdded(deadLine, taskList);
                }
            } else if (timeDateArr.length == 1) {
                if (isValidDate(timeDateArr[0])) {
                    createDeadlineWithDate(taskList, task, timeDateArr[0]);
                    return ui.showTaskAdded(deadLine, taskList);
                } else if (isValidTime(timeDateArr[0])) {
                    createDeadlineWithTime(taskList, task, timeDateArr[0]);
                    return ui.showTaskAdded(deadLine, taskList);
                }
            } else {
                return ui.deadlineInputError();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return ui.deadlineInputError();
        }
        return ui.deadlineInputError();
    }

    private void createDeadlineWithTimeDate(TaskList taskList, String task, String[] timeDateArr) {
        formattedDate = parseValidDate(timeDateArr[0]);
        formattedTime = parseValidTime(timeDateArr[1]);
        deadLine = new Deadline(task, formattedDate, formattedTime, false);
        taskList.addTask(deadLine);
    }

    private void createDeadlineWithDate(TaskList taskList, String task, String date) {
        formattedDate = parseValidDate(date);
        deadLine = new Deadline(task, formattedDate, false);
        taskList.addTask(deadLine);
    }

    private void createDeadlineWithTime(TaskList taskList, String task, String time) {
        formattedTime = parseValidTime(time);
        deadLine = new Deadline(task, LocalDate.now(), formattedTime, false);
        taskList.addTask(deadLine);
    }
}


