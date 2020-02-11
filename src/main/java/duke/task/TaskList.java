package duke.task;

import duke.other.DateValidator;
import duke.other.Ui;
import duke.other.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represented a TaskList of all the Tasks. A TaskList object corresponds with a list of all the tasks loaded from the
 * data file or a new list to store all the tasks if the data file is non-existent.
 */
public class TaskList {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/M/d");
    private static final DateValidator DATE_VALIDATOR = new DateValidator(DATE_FORMATTER);

    public ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds new task to the TaskList.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
        sort();
    }

    /**
     * Returns the size of the current TaskList.
     *
     * @return Size of TaskList
     */
    public int getSize() {
        return taskList.size();
    }

    public void sort() {
        Collections.sort(taskList);
    }

    /**
     * Prints out all the Tasks in the current TaskList.
     * @return String of all the tasks
     */
    public String listTasks() {
        String completeList = "    Task(s) in your list:";
        for (Task task : taskList) {
            completeList += "\n    " + ((taskList.indexOf(task) + 1)) + "." + task.toString();
        }
        System.out.println(completeList);
        return completeList;
    }

    /**
     * Prints out all the Tasks in the current TaskList that corresponds to the date.
     *
     * @param replyArr Array of the command + details of the command after splitting it by " "
     * @return String of all tasks on the date
     * @throws DukeException If the input date is invalid (i.e. incorrect format)
     */
    public String showTaskOnDate(String[] replyArr) {
        if (DATE_VALIDATOR.isValidDate(replyArr[1])) {
            LocalDate date = LocalDate.parse(replyArr[1], DATE_FORMATTER);
            String taskOnDate = "";
            for (Task task : taskList) {
                if (task instanceof TaskDate) {
                    if (((TaskDate) task).getDate().equals(date)) {
                        taskOnDate += "\n      " + task.toString();
                    }
                }
            }
            String tasksToday = "    The task(s) you have on " + replyArr[1] + ":" + taskOnDate;
            System.out.println(tasksToday);
            return tasksToday;
        } else {
            assert DATE_VALIDATOR.isValidDate(replyArr[1]) == false : replyArr[1];
            return Ui.dateInputError();
        }
    }

    /**
     * Marks the specified task as done.
     *
     * @param replyArr Array of the done command & task number of the element of the done command
     * @return String of bot's response
     * @throws DukeException If task number specified does not exist in the TaskList (e.g. done 5 when there is only 4
     *                       tasks)
     */
    public String markTaskAsDone(String[] replyArr) {
        int taskNum = Integer.parseInt(replyArr[1]) - 1;
        if (taskNum > taskList.size() - 1) {
            return Ui.doneInputError();
        } else {
            Task currTask = taskList.get(taskNum);
            currTask.isDone = true;
            String doneMsg = "    Nice! Task marked as done: \n    " + currTask.toString();
            System.out.println(doneMsg);
            return doneMsg;
        }
    }

    /**
     * Deletes the specified task from the TaskList.
     *
     * @param replyArr Array of the delete command & task number of the element of the delete command
     * @return String of bot's response
     * @throws DukeException If task number specified does not exist in the TaskList (e.g. delete 5 when there is only 4
     *                       tasks)
     */
    public String deleteTask(String[] replyArr) {
        try {
            int taskNum = Integer.parseInt(replyArr[1]) - 1;
            Task currTask = taskList.get(taskNum);
            String deleteMsg = "    Okay! Task removed: \n      " + currTask.toString() + "\n    Now you have "
                    + (taskList.size() - 1) + " task(s) in your list!";
            System.out.println(deleteMsg);
            taskList.remove(taskNum);
            return deleteMsg;
        } catch (IndexOutOfBoundsException ex) {
            return Ui.deleteInputError();
        }
    }

    /**
     * Finds and prints out tasks with a certain keyword.
     * @param replyArr Array of the find command & the specified keyword
     * @return String of all tasks with keyword(s)
     */
    public String findTaskByKeyword(String[] replyArr) {
        String taskWithKeyword = "";
        String keywords = "";
        for (int i = 1; i < replyArr.length; i++) {
            keywords += replyArr[i] + " ";
            for (Task task: taskList) {
                if (task.getDescription().contains(replyArr[i])) {
                    taskWithKeyword += "    " + task.toString() + "\n";
                }
            }
        }
        String msg = "    Task(s) with keyword " + keywords + ":\n" + taskWithKeyword;
        System.out.println(msg);
        return msg;
    }

}
