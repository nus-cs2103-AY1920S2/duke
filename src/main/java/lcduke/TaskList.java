package lcduke;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

/** Ths creates a TaskList object.
 */
public class TaskList {
    protected static Task[] totalTasks = new Task[100];
    protected static int totalTasksCount;

    /** This is the constructor to create the TaskList Object.
     */
    protected TaskList() {
        totalTasksCount = 0;
    }

    protected TaskList(String[] totalTasks, int storageNo) throws ParseException {
        String taskMark;
        String dateString;

        for (int i = 0; i < storageNo; i++) {
            taskMark = totalTasks[i].substring(0, 3);

            switch (taskMark) {
            case "[T]":
                this.toDo("todo"
                        + totalTasks[i].substring(totalTasks[i].indexOf(" ")));
                break;
            case "[D]":
                dateString = totalTasks[i].substring(totalTasks[i].indexOf("by:")
                        + 4, totalTasks[i].length() - 1);
                this.deadline("deadline" + totalTasks[i].substring(totalTasks[i].indexOf(" "),
                        totalTasks[i].indexOf("by:") - 1) + "/by " + this.standardDate(dateString));
                break;
            default:
                dateString = totalTasks[i].substring(totalTasks[i].indexOf("at:") + 4,
                        totalTasks[i].length() - 1);
                this.event("event" + totalTasks[i].substring(totalTasks[i].indexOf(" "),
                        totalTasks[i].indexOf("at:") - 1) + "/at " + this.standardDate(dateString));
            }

            if (totalTasks[i].contains(Character.toString((char)43))) {
                this.totalTasks[i].markAsDone();
            }
        }
    }

    protected void delete(String userInput) {
        String deleteSelect = userInput.substring(7);
        int deleteSelectNo = parseInt(deleteSelect) - 1;
        if (deleteSelectNo != totalTasksCount) {
            while (deleteSelectNo <= totalTasksCount) {
                totalTasks[deleteSelectNo] = totalTasks[deleteSelectNo + 1];
                deleteSelectNo++;
            }
        }
        totalTasksCount = totalTasksCount - 1;
        Task.taskNo = Task.taskNo - 1;
    }

    protected String toDo(String userInput) {
        String content = userInput.substring(5);
        Task t = new Todo(content);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
        return t.printInit();
    }

    protected String deadline(String userInput) {
        userInput = userInput.substring(9);
        String contentTasks = userInput.substring(0, userInput.indexOf("/by") - 1);
        String taskDeadline = userInput.substring(userInput.indexOf("/by") + 4);
        Task t = new Deadline(contentTasks, taskDeadline);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
        return t.printInit();
    }

    protected String event(String userInput) {
        userInput = userInput.substring(6);
        String contentTasks = userInput.substring(0, userInput.indexOf("/at") - 1);
        String taskTime = userInput.substring(userInput.indexOf("/at") + 4);
        Task t = new Event(contentTasks, taskTime);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
        return t.printInit();
    }

    protected String find(String userInput) {
        String response;
        String keyword = userInput.substring(5);
        response = "     Here are the matching tasks in your list:\n";
        int i = 1;
        for (int j = 0; j < totalTasksCount; j++) {
            if (totalTasks[j].getDescription().contains(keyword)) {
                response = response + "     " + i + "." + totalTasks[j].toString() + "\n";
                i++;
            }
        }
        return response;
    }

    /** this is to convert saved date into standard date format, e.g. Jan 15 2019 -> 2019-10-09
     *
     * @param dateString date of the task from the hard disk
     * @return the correct format of date in String format
    */
    private String standardDate(String dateString) {
        //[E][✘] asd (at: Oct 30 2123 23:10)
        String date;
        String time;
        if (dateString.substring(5, 6).contains(" ")) {
            date = dateString.substring(0, 10);
            time = dateString.substring(11, dateString.length());
        } else {
            date = dateString.substring(0, 11);
            time = dateString.substring(12, dateString.length());
        }
        LocalDateTime dt = LocalDateTime.of(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy")),
                        LocalTime.parse(time)
        );

        return String.valueOf(dt.format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:MM")));
    }
}
