package data.task;

import commands.CommandResult;
import common.Month;
import parser.ParseTime;
import java.time.LocalDate;

public class DeadlineTask extends Task{
    protected String taskInformation;
    public DeadlineTask() {
    }

    public DeadlineTask(int taskIndex, String taskDescription, String taskDeadline) {
        super(taskIndex, taskDescription, 'D');
        this.taskTime = taskDeadline;
        taskDeadlineDate = ParseTime.parseStringToLocalTime(this.taskTime);
    }

    public String getTaskDeadline() {
        return taskTime;
    }

    public LocalDate getTaskDeadlineDate() {
        return taskDeadlineDate;
    }

    /*
     *  returns a string taskInformation contains all essential information for the deadline task
     */
    public String getTaskInformation() {
        if (getTaskDeadlineDate()!=null){
            String timeString = String.format("%s %s %s",
                    Month.valueOf(getTaskDeadlineDate().getMonthValue()),
                    getTaskDeadlineDate().getDayOfMonth(),
                    getTaskDeadlineDate().getYear());
            taskInformation = String.format("[%c][%c] %s (%s)",
                    taskType,
                    getChar(),
                    getTaskDescription(),
                    timeString);
        } else {
            taskInformation = String.format(
                    "[%c][%c] %s (%s)",
                    taskType,
                    getChar(),
                    getTaskDescription(),
                    getTaskDeadline());
        }

        return taskInformation;
    }
}
