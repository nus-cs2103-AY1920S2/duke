package duke.task;

import duke.other.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    public void add(Task task) {
        taskList.add(task);
    }

    public int getSize() {
        return taskList.size();
    }


    public void listInstruction() {
        String completeList = "    Task(s) in your list:";
        for (Task task : taskList) {
            completeList += "\n    " + ((taskList.indexOf(task) + 1) + "." + task.toString());
        }
        System.out.println(completeList);
    }

    public void dateInstruction(String[] replyArr) throws DukeException {
        if(DATE_VALIDATOR.isValidDate(replyArr[1])) {
            LocalDate date = LocalDate.parse(replyArr[1], DATE_FORMATTER);
            String taskOnDate = "";
            for(Task task : taskList) {
                if(task instanceof TaskDate) {
                    if(((TaskDate) task).getDate().equals(date)) {
                        taskOnDate += "\n      " + task.toString();
                    }
                }
            }
            String tasksToday = "    The task(s) you have on " + replyArr[1] + ":" + taskOnDate;
            System.out.println(tasksToday);
        } else {
            Ui.dateInputError();
        }

    }

    public void doneInstruction(String[] replyArr) throws DukeException {
        int taskNum = Integer.parseInt(replyArr[1]) - 1;
        if (taskNum > taskList.size() - 1) {
            Ui.doneInputError();
        } else {
            Task currTask = taskList.get(taskNum);
            currTask.isDone = true;
            String doneMsg = "    Nice! Task marked as done: \n    " + currTask.toString();
            System.out.println(doneMsg);
        }
    }

    public void deleteInstruction(String[] replyArr) throws DukeException {
        try {
            int taskNum = Integer.parseInt(replyArr[1]) - 1;
            Task currTask = taskList.get(taskNum);
            String deleteMsg = "    Okay! Task removed: \n      " + currTask.toString() + "\n    Now you have "
                    + (taskList.size() - 1) + " task(s) in your list!";
            System.out.println(deleteMsg);
            taskList.remove(taskNum);
        } catch (IndexOutOfBoundsException ex) {
            Ui.deleteInputError();
        }
    }

}
