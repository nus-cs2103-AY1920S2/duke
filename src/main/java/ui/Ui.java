package ui;

import task.TaskList;
import task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ui {

    public Ui() {

    }

    public void showLine() {
        System.out.println("______________________________________________________________");
    }

    public String displayIntro() {
        String hi = "Hello! I'm Dwayne\n" + "What can I do for you?";
        return hi;
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void displayError(String error) {
        System.out.println(error);
    }

    public void displaySaveError() {
        System.out.println("File not saved.");
    }

    public void displayLoadError() {
        System.out.println("File not found.");
    }

    public void displayDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Displays the task to the GUI when task is added.
     * @param task task to be added
     * @param taskList task list where task is added
     */
    public void displayAddedTask(Task task, TaskList taskList) {
        int size = taskList.getSize();
        assert size > 0 : "Size cannot be less than 0!";
        System.out.println("Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays the deleted task and task list after.
     * @param task task to be deleted
     * @param taskList task list after deletion
     */
    public void displayDeletedTask(Task task, TaskList taskList) {
        int size = taskList.getSize();
        assert size > 0 : "Size cannot be less than 0!";
        System.out.println("Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    public void displayDeleteAllTask() {
        System.out.println("Noted. I've deleted all tasks. Don't come crying later!");
    }

    /**
     * Displays the tasks that are to be deleted and task list after.
     * @param taskList list of tasks after deletion
     * @param idOfTaskListToBeDeleted list of tasks to be deleted
     */
    public void displayDeleteSomeTask(TaskList taskList, ArrayList<Integer> idOfTaskListToBeDeleted) {
        int size = taskList.getSize();
        String string = "";
        for (int i : idOfTaskListToBeDeleted) {
            string += (i + ", ");
        }
        System.out.println("Noted. I've removed these tasks:\n" + string + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays the current tasks available.
     * @param taskList current tasklist
     */
    public void displayTasks(TaskList taskList) {
        System.out.print("Here are your remaining tasks:\n");
        for (Task t: taskList.tasks) {
            int index = taskList.tasks.indexOf(t) + 1;
            assert index < 0 : "Index cannot be less than 0!";
            System.out.println("" + index + "." + t.toString());
        }
    }

    public void displayBye() {
        System.out.print("Bye nerd. Hope to see you again soon!\n");
    }


}